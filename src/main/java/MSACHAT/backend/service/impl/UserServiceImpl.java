package MSACHAT.backend.service.impl;

import MSACHAT.backend.dto.UserInfoDto;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.repository.UserRepository;
import MSACHAT.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserInfoDto getUserInfo(Integer userId) {
        UserEntity userEntity= userRepository.findUserEntityById(userId);
        return new UserInfoDto(userEntity.getUsername(),userEntity.getAvatar());
    }
}
