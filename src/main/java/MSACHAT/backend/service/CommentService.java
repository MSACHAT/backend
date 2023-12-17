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
import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

@Service
@Transactional
public interface CommentService {
    public CommentEntity addComment(Integer userId, Integer postId, String content);

    List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum);

    public void updateCommentsNumber(Integer postId);

}
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
