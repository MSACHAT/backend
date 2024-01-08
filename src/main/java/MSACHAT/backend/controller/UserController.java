package MSACHAT.backend.controller;

import MSACHAT.backend.dto.UserInfoDto;
import MSACHAT.backend.service.AuthService;
import MSACHAT.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/getinfo")
    public UserInfoDto getUserInfo(@RequestParam Integer userId){
         return userService.getUserInfo(userId);
    }
}
