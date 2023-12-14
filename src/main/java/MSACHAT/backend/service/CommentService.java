<<<<<<< HEAD
package MSACHAT.service;

import java.util.List;

import MSACHAT.entity.CommentEntity;

public interface CommentService {
    
    List<CommentEntity> findCommentsByPostId(Integer postId);

}
=======
package MSACHAT.backend.service;

import MSACHAT.backend.entity.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentEntity addComment(Integer userId, Integer postId, String content);
}
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
