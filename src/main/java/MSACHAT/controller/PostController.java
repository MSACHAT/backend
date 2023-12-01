package MSACHAT.controller;
import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    PostController(
            PostService postService
    ){
        this.postService=postService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        return null;
    }

    @PostMapping("/all/get")
    public List<PostEntity> getPosts(){
        return postService.findAll();
    }
}
