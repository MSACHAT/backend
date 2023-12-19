package MSACHAT.backend.service.impl;

import org.springframework.stereotype.Service;

import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.repository.ImageRepository;
import MSACHAT.backend.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity addImage(Integer id, String imageUrl, PostEntity postEntity) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(id);
        imageEntity.setImageUrl(imageUrl);
        imageEntity.setPostId(postEntity);
        return imageRepository.save(imageEntity);
    }

}
