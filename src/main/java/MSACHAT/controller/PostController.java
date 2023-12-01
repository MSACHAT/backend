package MSACHAT.controller;
import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.mapper.Mapper;
import MSACHAT.service.PostService;
import org.springframework.http.HttpStatus;
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
    private Mapper<PostEntity, PostDto> postMapper;

    PostController(
            PostService postService
    ){
        this.postService=postService;
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

    @PostMapping("/all/get")
    public List<PostEntity> getPosts(){
        return postService.findAll();
    }
}
