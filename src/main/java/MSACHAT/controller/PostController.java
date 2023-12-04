package MSACHAT.controller;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.service.PostService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        return null;
    }

    @GetMapping("/all/get")
    public ArrayList<PostEntity> getPosts(@RequestBody Object tokenInfo) {
        return postService.findAll(tokenInfo.token,tokenInfo.secret);
    }

    @PatchMapping("/like/{id}")
    public ResponseEntity<String> likePost(
            @PathVariable("id") Integer id,
            @RequestBody String type
    ) {
        if(type.equals("like")){

        }
    }

    @GetMapping("/test")
    public String Test() {
        return "Connection made";
    }
}
