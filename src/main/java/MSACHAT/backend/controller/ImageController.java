package MSACHAT.backend.controller;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/images")
public class ImageController {

    ImageService imageService;
    AuthService authService;
    public ImageController(ImageService imageService,AuthService authService){
        this.imageService=imageService;
        this.authService=authService;
    }
    private final String uploadRootPath="http://localhost:8085/uploads/";//TODO:改成自己的
    private final String uploadDirForAvatar ="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/UserAvatar";//TODO:改成自己的,注意uploaddir的路径结尾没有"/"
    private final String uploadDirForImages="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/PostImages";

    @PostMapping("/uploadavatar/test")
    public ResponseEntity<String> uploadAvatar(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            Integer userId=2;
            String fileName = file.getOriginalFilename();
            // 构建本地文件路径
            Path filePath = Path.of(uploadDirForAvatar, fileName);

            file.transferTo(new File(String.valueOf(filePath)));

            // 返回存储的本地地址
            String serverFilePath = uploadRootPath+fileName;
            imageService.uploadAvatar(serverFilePath,userId);
            return ResponseEntity.ok(serverFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
    @GetMapping("/getavatar")
    public ResponseEntity<String> getAvatar(
            @RequestHeader("Authorization") String bearerToken
    ){
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId= authService.getUserIdFromToken(token);
        String userAvatar=imageService.getAvatar(userId);
        return new ResponseEntity<>(userAvatar,HttpStatus.OK);
    }

    @GetMapping("/getavatar/test")
    public ResponseEntity<String> getAvatarTest(
    ){
        Integer userId=2;
        String userAvatar=imageService.getAvatar(userId);
        return new ResponseEntity<>(userAvatar,HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Integer> testConnection(
            @RequestHeader("Authorization") String bearerToken
    ){
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId=authService.getUserIdFromToken(token);
        return new ResponseEntity<>(userId,HttpStatus.OK);
    }
}
