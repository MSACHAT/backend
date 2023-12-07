package MSACHAT.backend.service;

import MSACHAT.backend.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}