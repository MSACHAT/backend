package MSACHAT.service;

import MSACHAT.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}