package MSACHAT.backend.controller;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.JWTAuthResponse;
import MSACHAT.backend.dto.LoginDto;
import lombok.AllArgsConstructor;

import MSACHAT.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDto loginDto) {
        System.out.println(authService.IsUserExist(loginDto.getEmail()));
        if (!authService.IsUserExist(loginDto.getEmail())) {
            return new ResponseEntity<>(new ErrorDto("User Do Not Exist",10001),HttpStatus.UNAUTHORIZED);
        };
        String token = authService.login(loginDto);
        System.out.println("token"+token);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<Integer> Test( @RequestHeader("Authorization") String bearerToken){
        String token = authService.getTokenFromHeader(bearerToken);
        Integer userId = authService.getUserIdFromToken(token);
        return new ResponseEntity<>(userId, HttpStatus.ACCEPTED);
    }
}