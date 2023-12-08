package MSACHAT.backend.controller;

import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;
    private AuthService authService;
    private Mapper<PostEntity, PostDto> postMapper;

    PostController(
            PostService postService,
            AuthService authService
    ) {
        this.postService = postService;
        this.authService = authService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        if (postDto.getTitle() != null && postDto.getContent() != null && postDto.getImage() != null) {
            PostEntity postEntity = postMapper.mapFrom(postDto);
            PostEntity savedPostEntity = postService.addPost(postEntity);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all/get")
    public ArrayList<PostEntity> getPosts(@RequestHeader String token) {
        return postService.findAll(authService.getUserIdFromToken(token));
    }

    @PatchMapping("/{id}/like")
    public void likePost(
            @PathVariable("id") Integer postId,
            @RequestBody Boolean isLiked,
            @RequestHeader String token
    ) {
        if (isLiked) {

        } else {
            postService.likePost(postId, authService.getUserIdFromToken(token));
        }
    }

    @DeleteMapping("/{id}/delete")
    public void deletePost(
            @PathVariable("id") Integer postId
    ) {
        postService.deletePost(postId);
    }

    @GetMapping("/test")
    public String Test() {
        return "Connection made";
    }
}
