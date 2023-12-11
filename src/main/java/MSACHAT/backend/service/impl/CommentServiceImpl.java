package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.repository.PostRepository;
import MSACHAT.backend.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;


    @Override
    public CommentEntity addComment(Integer userId, Integer postId, String content) {
        Date dateTime= new Date(System.currentTimeMillis());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(userId);
        commentEntity.setPostId(postId);
        commentEntity.setContent(content);
        commentEntity.setTimeStamp(dateTime);

        return commentRepository.save(commentEntity);


    }

    @Override
    @Transactional
    public void updateCommentsNumber(Integer postId) {
        postRepository.addLikecount(postId);
    }
}
