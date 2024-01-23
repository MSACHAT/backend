package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostReturnDto;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.mapper.Mapper;
import MSACHAT.backend.repository.PostRepository;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.ImageService;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private PostService postService;
    private AuthService authService;
    private UserService userService;
    private ImageService imageService;
    private Mapper<PostEntity, PostDto> postMapper;

    public ProfileController(
            ImageService imageService,
            PostService postService,
            AuthService authService,
             UserService userService) {
        this.imageService = imageService;
        this.postService = postService;
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getPostsByUserId(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        if (pageSize == null || pageNum == null) {
            return new ResponseEntity<>(new ErrorDto(
                    "Request body incomplete. Required fields missing.",
                    10001),
                    HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> postResponse = postService.getPostsByUserId(userId, pageNum, pageSize);

        // bug: @data and @entity cannot use together
        // temporary use dto to avoid exceptions
        // todo: fix PostRepository
        List<PostReturnDto> postsReturnDtos = new ArrayList<>();

        for (PostEntity post : (Page<PostEntity>) postResponse.get("posts")) {
            postsReturnDtos.add(new PostReturnDto(
                    post.getId(),
                    post.getUserName(),
                    post.getContent(),
                    post.getImages().stream().map(ImageEntity::getImageUrl).toList(),
                    post.getTimeStamp(),
                    post.getLikeCount(),
                    post.getCommentCount(),
                    post.isLiked(),
                    post.getUserId(),
                    imageService.getAvatar(userId)));
            System.out.println(post.getContent());
        }

        postResponse.put("posts", postsReturnDtos);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getPostsByUserId(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {

        if (pageSize == null || pageNum == null) {
            return new ResponseEntity<>(new ErrorDto(
                    "Request body incomplete. Required fields missing.",
                    10001),
                    HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> postResponse = postService.getPostsByUserId(userId, pageNum, pageSize);

        // bug: @data and @entity cannot use together
        // temporary use dto to avoid exceptions
        // todo: fix PostRepository
        List<PostReturnDto> postsReturnDtos = new ArrayList<>();

        for (PostEntity post : (Page<PostEntity>) postResponse.get("posts")) {
            postsReturnDtos.add(new PostReturnDto(
                    post.getId(),
                    post.getUserName(),
                    post.getContent(),
                    post.getImages().stream().map(ImageEntity::getImageUrl).toList(),
                    post.getTimeStamp(),
                    post.getLikeCount(),
                    post.getCommentCount(),
                    post.isLiked(),
                    post.getUserId(),
                    imageService.getAvatar(userId)));

            System.out.println(post.getContent());
        }
        String avatar=userService.getUserInfo(userId).getAvatar();
        postResponse.put("posts", postsReturnDtos);
        postResponse.put("avatar",avatar);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

}
