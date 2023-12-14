package MSACHAT.backend.controller;

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
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

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