package MSACHAT.backend.service;

import MSACHAT.backend.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface ImageService {
    public void uploadAvatar(String avatarPath, Integer userId);

    public UserEntity getAvatar(Integer userId);
}
