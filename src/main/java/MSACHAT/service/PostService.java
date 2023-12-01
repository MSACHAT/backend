package MSACHAT.service;

<<<<<<< HEAD
import MSACHAT.entities.Post;

public interface PostService {

    List<Comment> getCommentsForPost(Post post);
=======
import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;

import java.util.List;

public interface PostService {
    List<PostEntity> findAll();
>>>>>>> 6f61a24794b4524f4013465234f0c03f1d89810d
}
