package MSACHAT.backend.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum, 10);
        Page<CommentEntity> commentEntityPage = commentRepository.findAll(pageRequest);
        List<CommentEntity> posts = commentEntityPage.
        getContent();
        return posts;
    }

    @Override
    public CommentEntity addComment(Integer userId, Integer postId, String content) {
        Date dateTime = new Date(System.currentTimeMillis());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(userId);
        commentEntity.setPostId(postId);
        commentEntity.setContent(content);
        commentEntity.setTimeStamp(dateTime);

        // 将 commentEntity 保存到数据库
        return commentRepository.save(commentEntity);
    }

    @Override
    public void updateCommentsNumber(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCommentsNumber'");
    }

}
