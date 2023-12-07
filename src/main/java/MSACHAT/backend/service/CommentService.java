package MSACHAT.service;

import java.util.List;

import MSACHAT.entity.CommentEntity;

public interface CommentService {
    
    List<CommentEntity> findCommentsByPostId(Integer postId);

}