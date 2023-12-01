package MSACHAT.service;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;

import java.util.List;

public interface PostService {
    public List<PostEntity> findAll();

   public PostEntity addPost(PostEntity postEntity);

}
