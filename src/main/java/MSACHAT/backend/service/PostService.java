package MSACHAT.backend.service;

import MSACHAT.backend.entity.PostEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PostService {
    public PostEntity addPost(PostEntity postEntity);

    public ArrayList<PostEntity> findAll(Integer userId);

    public String likePost(Integer postId,Integer userId);

    public String unlikePost(Integer postId,Integer userId);
    public String deletePost(Integer postId);

    public PostEntity findPostById(Integer postId);
}
