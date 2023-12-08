package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentEntity addComment(Integer userId, Integer postId, String content) {
        Date dateTime= new Date(System.currentTimeMillis());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(userId);
        commentEntity.setPostId(postId);
        commentEntity.setContent(content);
        commentEntity.setDatetime(dateTime);
        return commentRepository.save(commentEntity);


    }
}
