package MSACHAT.service;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;

import java.util.List;

public interface PostService {
    List<PostEntity> findAll();
}
