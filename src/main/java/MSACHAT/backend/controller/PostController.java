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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    //测试代码
    @PostMapping("/add/test")
    public ResponseEntity<Object> addPost(@RequestBody PostDto postDto
    ) {
            Integer userId = 1;

        if ( postDto.getContent() != null ) {

            PostEntity savedPostEntity = postService.addPost(userId, postDto.getContent());
            if (postDto.getImage() !=null){
                for (String image : postDto.getImage()) {
                    postService.addImage(savedPostEntity, image);
                }
            }

            return new ResponseEntity<>("success: true", HttpStatus.CREATED);
        }
        return new ResponseEntity<>((new ErrorDto("error: Missing Parameters",10001)), HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/getbypagenumandpagesize")
    public ResponseEntity<Object> getPosts(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        if (pageSize == null || pageNum == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostEntity> posts = postService.findPostsByPageNum(authService.getUserIdFromToken(bearerToken), pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("posts", posts);
        returnResult.put("totalPages", postService.countTotalPagesByPageSize(pageSize));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    //For API Test
    @GetMapping("/getbypagenumandpagesize/test")
    public ResponseEntity<Object> getPostsTest(

            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize
    ) {
        System.out.println("PageNum Param: " + pageNum);
        System.out.println("PageSize Param: " + pageSize);
        if (pageSize == null || pageNum == null) {//RequestBody Info Insufficient 10001 Error
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostEntity> posts = postService.findPostsByPageNum(1, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("posts", posts);
        returnResult.put("totalPages", postService.countTotalPagesByPageSize(pageSize));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    @PatchMapping("/like")
    public ResponseEntity<Object> likePost(
            @RequestBody Object posts,
            @RequestHeader String token
    ) {
        Map<String, Boolean> postsMap = (Map<String, Boolean>) posts;
        List<Object> postIds = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : postsMap.entrySet()) {
            int postId = Integer.parseInt(entry.getKey());
            Boolean isLiked = entry.getValue();
            if (postService.findPostById(postId) == null) {
                ErrorDto err = new ErrorDto("Post No Longer Exists.", 10002);
                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
            }
            if (isLiked) {
                postService.likePost(postId, authService.getUserIdFromToken(token));
            } else {
                postService.unlikePost(postId, authService.getUserIdFromToken(token));
            }
        }
        return new ResponseEntity<>(postIds, HttpStatus.OK);
    }

    //For API Test
    @PatchMapping("/like/test")
    public ResponseEntity<Object> likePostTest(
            @RequestBody Object posts
    ) {
        Map<String, Boolean> postsMap = (Map<String, Boolean>) posts;
        List<Object> postIds = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : postsMap.entrySet()) {
            int postId = Integer.parseInt(entry.getKey());
            Boolean isLiked = entry.getValue();
            if (isLiked) {
                postService.likePost(postId, 1);//这里1是userId
            } else {
                postService.unlikePost(postId, 1);
            }
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

            return new ResponseEntity<>(new ErrorDto("Post not found", 1001), HttpStatus.NOT_FOUND);
        }

        List<String> imageList = post.getImages().stream().map(ImageEntity::getImageUrl).toList();
        PostReturnDto postReturn = new PostReturnDto(post.getId(),post.getUserName(),  post.getContent(), imageList,post.getTimeStamp(),post.getLikeCount(),post.getCommentCount(),post.isLiked());

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
        PostReturnDto postReturn = new PostReturnDto(post.getId(),post.getUserName(), post.getContent(), imageList,post.getTimeStamp(),post.getLikeCount(),post.getCommentCount(),post.isLiked());

        return new ResponseEntity<>(postReturn, HttpStatus.OK);
    }


    @PutMapping("/{id}/comment")
    public ResponseEntity<Object> addComment(
            @RequestBody CommentInfoDto commentInfo,
            @PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken
    ) {
        if (postService.IsPostExist(postId)){
            return new ResponseEntity<>(new ErrorDto("Post not found", 1001), HttpStatus.NOT_FOUND);
        }
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        String content = commentInfo.getContent();
        CommentEntity comment = commentService.addComment(userId, postId, content);
        commentService.updateCommentsNumber(postId);
        return new ResponseEntity<>("success: true", HttpStatus.CREATED);
    }
}
