package MSACHAT.backend.service;

import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface PostService {
    public PostEntity addPost(Integer userId,String title, String content);

    public List<PostEntity> findPostsByPageNum(Integer userId, Integer pageNum,Integer pageSize);

    public String likePost(Integer postId, Integer userId);

    public String unlikePost(Integer postId, Integer userId);

    public String deletePost(Integer postId);


    public Boolean IsLiked(Integer postId, Integer userId);


    public PostEntity findPostByIdAndUserId(Integer postId, Integer userId);

    public ImageEntity addImage(PostEntity postEntity, String imagePath);

    public PostEntity findPostById(Integer postId);
}
