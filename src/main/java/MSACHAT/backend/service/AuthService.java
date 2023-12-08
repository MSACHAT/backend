package MSACHAT.backend.service;

import MSACHAT.backend.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);

    public Integer getUserIdFromToken(String token);

    String getTokenFromHeader(String BearerToken);
}