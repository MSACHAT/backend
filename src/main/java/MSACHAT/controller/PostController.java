package MSACHAT.controller;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.mapper.Mapper;
import MSACHAT.service.PostService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;
    private Mapper<PostEntity, PostDto> postMapper;

    PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        if(postDto.getTitle()!=null && postDto.getContent()!=null && postDto.getImage()!=null){
            PostEntity postEntity= postMapper.mapFrom(postDto);
            PostEntity savedPostEntity = postService.addPost(postEntity);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all/get")
    public ArrayList<PostEntity> getPosts(@RequestBody Object tokenInfo) {
        return postService.findAll(tokenInfo.token,tokenInfo.secret);
    }

    @PatchMapping("/like/{id}")
    public void likePost(
            @PathVariable("id") Integer postId,
            @RequestBody Object likeInfo
    ) {
        if(likeInfo.isLiked){

        }
        else{
            postService.likePost(postId, postService.getUserIdFromToken(likeInfo.token,likeInfo.secret));
        }
    }

    @GetMapping("/test")
    public String Test() {
        return "Connection made";
    }
}
