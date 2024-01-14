package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.repository.ImageRepository;
import MSACHAT.backend.repository.UserRepository;
import MSACHAT.backend.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    UserRepository userRepository;

    ImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void uploadAvatar(String avatarPath, Integer userId) {
        UserEntity user = userRepository.findUserEntityById(userId);
        user.setAvatar(avatarPath);
        userRepository.save(user);
    }

    @Override
    public String getAvatar(Integer userId) {
        UserEntity userEntity = userRepository.findUserEntityById(userId);

        if (userEntity != null) {
            System.out.println(userEntity.getAvatar());
            return userEntity.getAvatar();
        } else {

            System.out.println("User not found for userId: " + userId);
            return null;
        }
    }

    @Override
    public String uploadImage(String imagePath, Integer postTd) {
        return null;
    }

}
