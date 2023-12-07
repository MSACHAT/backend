package MSACHAT.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import MSACHAT.entity.CommentEntity;

@Service
public class CommentServiceimpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> findCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }


}
