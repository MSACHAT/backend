package MSACHAT.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import MSACHAT.backend.service.ImageService;
import MSACHAT.backend.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.ImageDto;
import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.PostEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("image")
public class ImageController {

    private ImageService imageService;
    private PostService postService;

    // 构造函数注入依赖
    @Autowired
    public ImageController(ImageService imageService, PostService postService) {
        this.imageService = imageService;
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addImage(@RequestBody ImageDto imageDto) {
        // 检查请求体是否完整
        if (imageDto.getImageUrl() == null || imageDto.getPostId() == null) {
            ErrorDto err = new ErrorDto("Request body incomplete. Required fields missing.", 10001);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        // 通过postService查找PostEntity
        PostEntity postEntity = postService.findPostById(imageDto.getPostId());

        // 检查是否找到PostEntity
        if (postEntity == null) {
            ErrorDto err = new ErrorDto("Post not found with id: " + imageDto.getPostId(), 10002);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }

        // 调用imageService添加ImageEntity
        imageService.addImage(imageDto.getId(), imageDto.getImageUrl(), postEntity);

        return new ResponseEntity<>("success: created", HttpStatus.CREATED);
    }
}
