package MSACHAT.backend.service;

import MSACHAT.backend.entity.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentEntity addComment(Integer userId, Integer postId, String content);
}
