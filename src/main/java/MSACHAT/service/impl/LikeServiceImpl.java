package MSACHAT.service.impl;

import MSACHAT.entity.LikeEntity;
import MSACHAT.repository.LikeRepository;
import MSACHAT.service.LikeService;

import java.util.Optional;

public class LikeServiceImpl implements LikeService {
    LikeRepository likeRepository;

    LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public String likePost(String type,Integer id) {
        Optional<LikeEntity> likeInf = likeRepository.findById(id);
        return null;
    }
}
