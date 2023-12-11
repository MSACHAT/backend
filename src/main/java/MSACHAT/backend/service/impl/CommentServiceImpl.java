package MSACHAT.backend.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.service.CommentService;

@Service
public class CommentServiceimpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceimpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> findCommentsByPostId(Integer postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public CommentEntity addComment(Integer userId, Integer postId, String content) {
        Date dateTime = new Date(System.currentTimeMillis());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(userId);
        commentEntity.setPostId(postId);
        commentEntity.setContent(content);
        commentEntity.setTimeStamp(dateTime);

        return commentRepository.save(commentEntity);
    }
}
