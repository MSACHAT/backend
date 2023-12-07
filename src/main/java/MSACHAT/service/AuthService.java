package MSACHAT.service;

import MSACHAT.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);

    public Integer getUserIdFromToken(String token, String secret);
}