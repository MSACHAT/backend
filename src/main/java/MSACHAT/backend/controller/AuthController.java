package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.JWTAuthResponse;
import MSACHAT.backend.dto.LoginDto;
import MSACHAT.backend.dto.UserDto;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.service.UserService;
import lombok.AllArgsConstructor;

import MSACHAT.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AuthController {

    private AuthService authService;

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDto loginDto) {

        if (!authService.IsUserExist(loginDto.getEmail())) {
            return new ResponseEntity<>(new ErrorDto("User Does Not Exist", 10001), HttpStatus.UNAUTHORIZED);
        }
        ;
        String token = authService.login(loginDto);
        System.out.println("token" + token);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<String> Test(Authentication authentication) {

        Object UserName = authentication.getName();
        System.out.println(UserName);

        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getPassword() == null) {
            return new ResponseEntity<>("Username and password are required", HttpStatus.BAD_REQUEST);
        }

        try {
            UserEntity registeredUser = userService.registerNewUserAccount(userDto);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}