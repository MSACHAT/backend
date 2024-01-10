package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.mapper.Mapper;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.CommentService;
import MSACHAT.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private PostService postService;
    private AuthService authService;
    private CommentService commentService;

    private Mapper<PostEntity, PostDto> postMapper;

    public ProfileController(
            PostService postService,
            AuthService authService,
            CommentService commentService) {
        this.postService = postService;
        this.authService = authService;
        this.commentService = commentService;
    }

    @GetMapping("/getbypagenumandpagesize")
    public ResponseEntity<Object> getPosts(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        if (pageSize == null || pageNum == null) {
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostEntity> posts = postService.findPostsByPageNum(authService.getUserIdFromToken(bearerToken), pageNum,
                pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("posts", posts);
        returnResult.put("totalPages", postService.countTotalPagesByPageSize(pageSize));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }

    // For API Test
    @GetMapping("/getbypagenumandpagesize/test")
    public ResponseEntity<Object> getPostsTest(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        System.out.println("PageNum Param: " + pageNum);
        System.out.println("PageSize Param: " + pageSize);
        if (pageSize == null || pageNum == null) {
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        List<PostEntity> posts = postService.findPostsByPageNum(1, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("posts", posts);
        returnResult.put("totalPages", postService.countTotalPagesByPageSize(pageSize));
        return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }
}
