package MSACHAT.backend.controller;

import MSACHAT.backend.dto.*;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.service.*;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.mapper.Mapper;
import MSACHAT.backend.repository.PostRepository.PostResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CommentService commentService;
    private final PostService postService;
    private final AuthService authService;
    private final ImageService imageService;
    private final NotifService notifService;

    public PostController(
            NotifService notifService,
            PostService postService,
            AuthService authService,
            CommentService commentService,
            ImageService imageService) {
        this.notifService = notifService;
        this.postService = postService;
        this.authService = authService;
        this.commentService = commentService;
        this.imageService = imageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto,
            @RequestHeader("Authorization") String bearerToken) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);

        if (postDto.getContent() != null) {
            PostEntity savedPostEntity = postService.addPost(userId, postDto.getContent());
            if (postDto.getImage() != null) {
                for (String image : postDto.getImage()) {
                    postService.addImage(savedPostEntity, image);
                }
            }
            return new ResponseEntity<>("success: true", HttpStatus.CREATED);
        }
        return new ResponseEntity<>((new ErrorDto("error: Missing Parameters", 10001)), HttpStatus.BAD_REQUEST);
    }

    // 测试代码
    @PostMapping("/add/test")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
        Integer userId = 1;

        if (postDto.getContent() != null) {

            PostEntity savedPostEntity = postService.addPost(userId, postDto.getContent());
            if (postDto.getImage() != null) {
                for (String image : postDto.getImage()) {
                    postService.addImage(savedPostEntity, image);
                }
            }

            return new ResponseEntity<>("success: true", HttpStatus.CREATED);
        }
        return new ResponseEntity<>((new ErrorDto("error: Missing Parameters", 10001)), HttpStatus.BAD_REQUEST);
    }

    private PostReturnDto convertToDto(PostEntity post) {
        return new PostReturnDto(
                post.getId(),
                post.getUserName(),
                post.getContent(),
                post.getImages().stream().map(ImageEntity::getImageUrl).collect(Collectors.toList()),
                post.getTimeStamp(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.isLiked(),
                post.getUserId(),
                imageService.getAvatar(post.getUserId()));
    }

    @GetMapping("")
    public ResponseEntity<Object> getPosts(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        if (pageSize == null || pageNum == null) {// RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostReturnDto> postsReturn = postService.findPostsByPageNum(userId, pageNum, pageSize)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("posts", postsReturn);
        returnResult.put("totalPages", postService.countTotalPagesByPageSize(pageSize));
        System.out.println(postsReturn);
        return ResponseEntity.ok(returnResult);
    }

    @PatchMapping("/{postId}/like")
    public ResponseEntity<Object> likePost(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer postId,
            @RequestBody IsLikedDto isLiked) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        if (postService.findPostById(postId) == null) {
            ErrorDto err = new ErrorDto("Post No Longer Exists.", 10002);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        String returnMessage = isLiked.getIsLiked() ? postService.likePost(postId, userId)
                : postService.unlikePost(postId, userId);
        return new ResponseEntity<>(returnMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable("id") Integer postId) {
        if (postService.findPostById(postId) == null) {
            ErrorDto err = new ErrorDto("Post No Longer Exists.", 10001);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public String Test() {
        return "Connection";
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<Object> getPostById(
            @PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);

        PostEntity post = postService.findPostByIdAndUserId(postId, userId);
        if (post == null) {

            return new ResponseEntity<>(new ErrorDto("Post does not exist", 1001), HttpStatus.NOT_FOUND);
        }
        String Avatar = imageService.getAvatar(userId);

        List<String> imageList = post.getImages().stream().map(ImageEntity::getImageUrl).toList();
        PostReturnDto postReturn = new PostReturnDto(post.getId(), post.getUserName(), post.getContent(), imageList,
                post.getTimeStamp(), post.getLikeCount(), post.getCommentCount(), post.isLiked(), post.getUserId(),
                Avatar);

        return new ResponseEntity<>(postReturn, HttpStatus.OK);
    }

    // 测试使用
    @GetMapping("/{id}/get/test")
    public ResponseEntity<Object> getPostByIdTest(@PathVariable("id") Integer postId) {

        Integer userId = 1;

        PostEntity post = postService.findPostByIdAndUserId(postId, userId);
        if (post == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        String Avatar = imageService.getAvatar(userId);
        List<String> imageList = post.getImages().stream().map(ImageEntity::getImageUrl).toList();
        PostReturnDto postReturn = new PostReturnDto(post.getId(), post.getUserName(), post.getContent(), imageList,
                post.getTimeStamp(), post.getLikeCount(), post.getCommentCount(), post.isLiked(), post.getUserId(),
                Avatar);

        return new ResponseEntity<>(postReturn, HttpStatus.OK);
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<Object> addComment(
            @RequestBody CommentInfoDto commentInfo,
            @PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken) {
        if (!postService.IsPostExist(postId)) {
            return new ResponseEntity<>(new ErrorDto("Post not found", 1001), HttpStatus.NOT_FOUND);
        }
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        String content = commentInfo.getContent();
        PostEntity post = postService.findPostById(postId);
        commentService.addComment(userId, postId, content);
        commentService.updateCommentsNumber(postId);
        notifService.addNewNotif(userId,post.getUserId(),postId,content);
        System.out.println(userId);
        System.out.println(post.getUserId());
        if (!Objects.equals(userId, post.getUserId())) {
            notifService.addNewNotif(userId, post.getUserId(), postId, content);
        }
        return new ResponseEntity<>("success: true", HttpStatus.CREATED);
    }

    @PutMapping("/{id}/comment/test")
    public ResponseEntity<Object> addCommentTest(
            @RequestBody CommentInfoDto commentInfo,
            @PathVariable("id") Integer postId) {
        // if (postService.IsPostExist(postId)){
        // return new ResponseEntity<>(new ErrorDto("Post not found", 1001),
        // HttpStatus.NOT_FOUND);
        // }
        Integer userId = 1;
        String content = commentInfo.getContent();
        CommentEntity comment = commentService.addComment(userId, postId, content);
        commentService.updateCommentsNumber(postId);
        return new ResponseEntity<>("success: true", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable("id") Integer postId) {

        return new ResponseEntity<>(postService.IsPostExist(postId), HttpStatus.OK);
    }

    @PostMapping("/test/post")
    public String postTesting(
            @RequestHeader("Authorization") String token) {
        return "connnectehfbsdf";
    }
}
