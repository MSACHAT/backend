package MSACHAT.backend.service.impl;

import java.sql.Date;
import java.util.List;

import MSACHAT.backend.repository.PostRepository;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.service.CommentService;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("timeStamp").descending());
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByPostId(postId, pageRequest);
        return commentEntityPage.getContent();
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
    public List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum) {
        return null;
    }

    @Override
    public void updateCommentsNumber(Integer postId) {
        postRepository.addLikesCount(postId);
    }

    @Override
    public Integer countTotalPagesByPageSize(Integer pageSize){
        double pageCount=postRepository.count()/(pageSize*1.0);
        return (Integer) (int)Math.ceil(pageCount)-1;
    }
}
