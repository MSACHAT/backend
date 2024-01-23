package MSACHAT.backend.service.impl;

import MSACHAT.backend.dto.UserDto;
import MSACHAT.backend.dto.UserInfoDto;
import MSACHAT.backend.entity.RoleEntity;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.repository.RoleRepository;
import MSACHAT.backend.repository.UserRepository;
import MSACHAT.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;
    public RoleRepository roleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository,RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserInfoDto getUserInfo(Integer userId) {
        UserEntity userEntity= userRepository.findUserEntityById(userId);
        return new UserInfoDto(userEntity.getUsername(),userEntity.getAvatar());
    }

    @Override
    public UserEntity registerNewUserAccount(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setEmail(userDto.getEmail());
        newUser.setUsername(userDto.getUsername());
        newUser.setName(userDto.getUsername());
        newUser.setPassword(encodedPassword);
        Set<RoleEntity> role = roleRepository.findRoleEntitiesByName("ROLE_USER");
        newUser.setRoles(role);

        return userRepository.save(newUser);
    }
}
