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

    List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum, Integer pageSize);

    public void updateCommentsNumber(Integer postId);

    public Integer countTotalPagesByPageSize(Integer pageSize);

    CommentEntity findCommentById(Integer commentId);

    String deleteComment(Integer commentId);
}
