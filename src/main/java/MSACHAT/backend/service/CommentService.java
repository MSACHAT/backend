package MSACHAT.backend.service;

import MSACHAT.backend.entity.CommentEntity;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentEntity addComment(Integer userId, Integer postId, String content);

    List<CommentEntity> findCommentsByPostId(Integer postId);

    @Transactional
    public void updateCommentsNumber(Integer postId);

}
