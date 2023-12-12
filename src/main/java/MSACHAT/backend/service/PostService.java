package MSACHAT.backend.service;

import MSACHAT.backend.entity.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public interface PostService {
    public PostEntity addPost(PostEntity postEntity);

    public List<PostEntity> findPostsByPageNum(Integer userId, Integer pageNum);

    public String likePost(Integer postId, Integer userId);

    public String unlikePost(Integer postId, Integer userId);

    public String deletePost(Integer postId);


    Boolean IsLiked(Integer postId, Integer userId);


    PostEntity findPostById(Integer postId, Integer userId);
}
