package MSACHAT.backend.service;

import MSACHAT.backend.entity.CommentEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentEntity addComment(Integer userId, Integer postId, String content);

    List<CommentEntity> findCommentsByPostId(Integer postId);
}
