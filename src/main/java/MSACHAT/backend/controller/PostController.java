package MSACHAT.backend.controller;

import MSACHAT.backend.dto.*;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.CommentService;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/post")
public class PostController {
    private final CommentService commentService;
    private final PostService postService;
    private final AuthService authService;


    private Mapper<PostEntity, PostDto> postMapper;

    public PostController(
            PostService postService,
            AuthService authService,
            CommentService commentService
    ) {
        this.postService = postService;
        this.authService = authService;
        this.commentService = commentService;

    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto, @RequestHeader("Authorization") String bearerToken
    ) {
        if (postDto.getTitle() == null && postDto.getContent() == null && postDto.getImage() == null) {
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        Integer userId = authService.getUserIdFromToken(bearerToken);
        PostEntity savedPostEntity = postService.addPost(userId, postDto.getTitle(), postDto.getContent());
        for (String image : postDto.getImage()) {
            postService.addImage(savedPostEntity, image);
        }

        return new ResponseEntity<>("success: created", HttpStatus.CREATED);
    }


    //测试代码
    @PostMapping("/add/test")
    public ResponseEntity<String> addPostTest(@RequestBody PostDto postDto) {
        if (postDto.getTitle() != null && postDto.getContent() != null && postDto.getImage() != null) {
            Integer userId = 1;


            PostEntity savedPostEntity = postService.addPost(userId, postDto.getTitle(), postDto.getContent());

            List<String> images = postDto.getImage();
            List<Future<Void>> imageUploadTasks = new ArrayList<>();


            ExecutorService executorService = Executors.newFixedThreadPool(images.size());

            for (String image : images) {

                Future<Void> imageUploadTask = executorService.submit(() -> {
                    postService.addImage(savedPostEntity, image);
                    return null;
                });
                imageUploadTasks.add(imageUploadTask);
            }

            for (Future<Void> task : imageUploadTasks) {
                try {
                    task.get();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }


            executorService.shutdown();

            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/getbypagenumandpagesize")
    public ResponseEntity<Object> getPosts(
//            @RequestHeader("Authorization") String bearerToken,
            @RequestBody PageNumDto pageNumDto) {
        if (pageNumDto.getPageSize() == null || pageNumDto.getPageNum() == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostEntity> posts = postService.findPostsByPageNum(1, pageNumDto.getPageNum(), pageNumDto.getPageSize());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<Object> likePost(
            @PathVariable("id") Integer postId,
            @RequestBody IsLikedDto isLikedDto,
            @RequestHeader String token) {
        if (isLikedDto.getIsLiked() == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        if (postService.findPostById(postId) == null) {
            ErrorDto err = new ErrorDto("Post No Longer Exists.", 10002);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        boolean isLiked = isLikedDto.getIsLiked();
        if (isLiked) {
            postService.unlikePost(postId, authService.getUserIdFromToken(token));
        } else {
            postService.likePost(postId, authService.getUserIdFromToken(token));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
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
            ErrorDto err = new ErrorDto("Post No Longer Exists.", 10001);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }

        List<String> imageList = post.getImages().stream().map(ImageEntity::getImageUrl).toList();
        PostReturnDto postReturn = new PostReturnDto(post.getId(), post.getUserName(), post.getTitle(), post.getContent(), imageList, post.getTimeStamp(), post.getLikeCount(), post.getCommentCount(), post.isLiked());

        return new ResponseEntity<>(postReturn, HttpStatus.OK);
    }

    //测试使用
    @GetMapping("/{id}/get/test")
    public ResponseEntity<Object> getPostByIdTest(@PathVariable("id") Integer postId) {

        Integer userId = 1;


        PostEntity post = postService.findPostByIdAndUserId(postId, userId);
        if (post == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<String> imageList = post.getImages().stream().map(ImageEntity::getImageUrl).toList();
        PostReturnDto postReturn = new PostReturnDto(post.getId(), post.getUserName(), post.getTitle(), post.getContent(), imageList, post.getTimeStamp(), post.getLikeCount(), post.getCommentCount(), post.isLiked());

        return new ResponseEntity<>(postReturn, HttpStatus.OK);
    }


    @PutMapping("/{id}/comment")
    public ResponseEntity<Object> addComment(
            @RequestBody CommentInfoDto commentInfo,
            @PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken
    ) {
        if (commentInfo.getContent() == null) {
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        if (postService.findPostById(postId) == null) {
            ErrorDto err = new ErrorDto("Post No Longer Exists.", 10002);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        String content = commentInfo.getContent();
        CommentEntity comment = commentService.addComment(userId, postId, content);
        commentService.updateCommentsNumber(postId);
        return new ResponseEntity<>("success: true", HttpStatus.CREATED);
    }
}
