package MSACHAT.backend.controller;

import MSACHAT.backend.dto.CommentDto;
import MSACHAT.backend.dto.IsLikedDto;
import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.CommentService;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

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
            CommentService commentService) {
        this.postService = postService;
        this.authService = authService;
        this.commentService = commentService;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto,@RequestHeader("Authorization") String bearerToken) {
        if (postDto.getTitle() != null && postDto.getContent() != null && postDto.getImage() != null) {
            Integer userId = authService.getUserIdFromToken(bearerToken);


            PostEntity savedPostEntity = postService.addPost(userId,postDto.getTitle(), postDto.getContent());
            for (String image : postDto.getImage()) {
                postService.addImage(savedPostEntity, image);
            }

            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getbypagenum")
    public List<PostEntity> getPosts(@RequestHeader("Authorization") String bearerToken,
            @RequestBody PageNumDto pageNumDto) {
        return postService.findPostsByPageNum(authService.getUserIdFromToken(bearerToken), pageNumDto.getPageNum());
    }

    @PatchMapping("/{id}/like")
    public void likePost(
            @PathVariable("id") Integer postId,
            @RequestBody IsLikedDto isLikedDto,
            @RequestHeader String token) {
        boolean isLiked = isLikedDto.getIsLiked();
        if (isLiked) {
            postService.unlikePost(postId, authService.getUserIdFromToken(token));
        } else {
            postService.likePost(postId, authService.getUserIdFromToken(token));
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deletePost(
            @PathVariable("id") Integer postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/test")
    public String Test() {
        return "Connection";
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<PostEntity> getPostById(@PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);

        PostEntity post = postService.findPostById(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentInfo, @PathVariable("id") Integer postId,
            @RequestHeader("Authorization") String bearerToken) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        String content = commentInfo.getContent();
        CommentEntity comment = commentService.addComment(userId, postId, content);
        commentService.updateCommentsNumber(postId);
        return new ResponseEntity<>("success: true", HttpStatus.CREATED);
    }
}
