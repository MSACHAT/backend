package MSACHAT.backend.service;

import MSACHAT.backend.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(LoginDto loginDto);

    public Integer getUserIdFromToken(String token);

    String getTokenFromHeader(String BearerToken);
}