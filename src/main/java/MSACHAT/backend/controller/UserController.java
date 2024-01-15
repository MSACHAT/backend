package MSACHAT.backend.controller;

import MSACHAT.backend.dto.UserInfoDto;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.ImageService;
import MSACHAT.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    AuthService authService;
    ImageService imageService;
    private final String uploadRootPath="http://localhost:8085/uploads/";//TODO:改成自己的
    private final String uploadDirForAvatar ="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/UserAvatar";//TODO:改成自己的,注意uploaddir的路径结尾没有"/"
    private final String uploadDirForImages="C:/Users/17354/Desktop/MSACHAT-V2/frontend_MSACHAT/src/assets/PostImages";
    UserController(UserService userService,AuthService authService,ImageService imageService){
        this.userService=userService;
        this.authService=authService;
        this.imageService=imageService;
    }

    @GetMapping("/getinfo")
    public UserInfoDto getUserInfo(@RequestParam Integer userId){
         return userService.getUserInfo(userId);
    }

    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestPart("file") MultipartFile file,
            @RequestHeader("Authorization") String bearerToken
    ) {
        try {
            String token=authService.getTokenFromHeader(bearerToken);
            Integer userId= authService.getUserIdFromToken(token);
            String fileName = System.currentTimeMillis()+userId.toString()+file.getOriginalFilename()+".png";
            Path filePath = Path.of(uploadDirForAvatar, fileName);
            file.transferTo(new File(String.valueOf(filePath)));
            String serverFilePath = uploadRootPath+fileName;
            imageService.uploadAvatar(serverFilePath,userId);
            return ResponseEntity.ok(serverFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }

    @GetMapping("/avatar")
    public ResponseEntity<String> getAvatar(
            @RequestHeader("Authorization") String bearerToken
    ){
        String token=authService.getTokenFromHeader(bearerToken);
        Integer userId= authService.getUserIdFromToken(token);
        String userAvatar=imageService.getAvatar(userId);
        return new ResponseEntity<>(userAvatar, HttpStatus.OK);
    }
}
