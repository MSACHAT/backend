package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.JWTAuthResponse;
import MSACHAT.backend.dto.LoginDto;
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

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);
        System.out.println("token"+token);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<String> Test(Authentication authentication){
        System.out.println(authentication.getName());
        return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
    }
}