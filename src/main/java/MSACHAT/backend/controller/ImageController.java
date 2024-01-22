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
import java.util.Random;

@RestController
@RequestMapping("/images")
public class ImageController {

    ImageService imageService;
    AuthService authService;
    public ImageController(ImageService imageService,AuthService authService){
        this.imageService=imageService;
        this.authService=authService;
    }
    private final String uploadRootPathForImages="http://localhost:8085/uploads/postimages/";//TODO:改成自己的
    private final String uploadDirForAvatar ="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/UserAvatar";//TODO:改成自己的,注意uploaddir的路径结尾没有"/"
    private final String uploadDirForImages="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/PostImages";
    @PostMapping("/uploadimage")
    public ResponseEntity<String> uploadImage(
            @RequestHeader("Authorization") String bearerToken,
            @RequestPart("file") MultipartFile file
    ) {
        try {
            Random rand=new Random();
            String token=authService.getTokenFromHeader(bearerToken);
            Integer userId=authService.getUserIdFromToken(token);
            String fileName = System.currentTimeMillis()+userId.toString()+file.getOriginalFilename()+rand.nextInt(100);
            Path filePath = Path.of(uploadDirForImages, fileName);
            file.transferTo(new File(String.valueOf(filePath)));
            String serverFilePath = uploadRootPathForImages+fileName;
            return ResponseEntity.ok(serverFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
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
