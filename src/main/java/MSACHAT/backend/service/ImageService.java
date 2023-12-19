package MSACHAT.backend.service;

import org.springframework.stereotype.Service;

import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.PostEntity;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface ImageService {
    public ImageEntity addImage(Integer id, String imageUrl, PostEntity postEntity);

}