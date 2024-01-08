package MSACHAT.backend.controller;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@RestController
@RequestMapping("/images")
public class ImageController {

    ImageService imageService;
    AuthService authService;
    public ImageController(ImageService imageService,AuthService authService){
        this.imageService=imageService;
        this.authService=authService;
    }

    private String uploadDir="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/UserAvatar";
    @PostMapping("/uploadimage")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 构建本地文件路径
            Path filePath = Path.of(uploadDir, fileName);

            // 复制文件到本地
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 返回存储的本地地址
            String localFilePath = filePath.toAbsolutePath().toString();
            return ResponseEntity.ok("File uploaded successfully. Local file path: " + localFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }

    @PostMapping("/uploadavatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestPart("file") MultipartFile file,
            @RequestHeader String token
    ) {
        try {
            Integer userId=authService.getUserIdFromToken(token);
            String fileName = file.getOriginalFilename();
            // 构建本地文件路径
            Path filePath = Path.of(uploadDir, fileName);

            // 复制文件到本地
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 返回存储的本地地址
            String localFilePath = filePath.toAbsolutePath().toString();
            imageService.uploadAvatar(localFilePath,userId);
            return ResponseEntity.ok("File uploaded successfully. Local file path: " + localFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
    @PostMapping("/uploadavatar/test")
    public ResponseEntity<String> uploadAvatar(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            Integer userId=2;
            String fileName = file.getOriginalFilename();
            // 构建本地文件路径
            Path filePath = Path.of(uploadDir, fileName);

            // 复制文件到本地
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 返回存储的本地地址
            String localFilePath = filePath.toAbsolutePath().toString();
            imageService.uploadAvatar(localFilePath,userId);
            return ResponseEntity.ok("File uploaded successfully. Local file path: " + localFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
    @GetMapping("/getavatar")
    public ResponseEntity<String> getAvatar(
            @RequestHeader String token
    ){
        Integer userId= authService.getUserIdFromToken(token);
        UserEntity user=imageService.getAvatar(userId);
        return new ResponseEntity<>(user.getAvatar(),HttpStatus.OK);
    }

    @GetMapping("/getavatar/test")
    public ResponseEntity<String> getAvatarTest(
    ){
        Integer userId=2;
        UserEntity user=imageService.getAvatar(userId);
        return new ResponseEntity<>(user.getAvatar(),HttpStatus.OK);
    }
}
