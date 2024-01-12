package MSACHAT.backend.service;

import MSACHAT.backend.dto.UserDto;
import MSACHAT.backend.dto.UserInfoDto;
import MSACHAT.backend.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface UserService {
    public UserInfoDto getUserInfo(Integer userId);

    UserEntity registerNewUserAccount(UserDto userDto);
}
