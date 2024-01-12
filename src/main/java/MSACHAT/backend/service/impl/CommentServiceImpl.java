package MSACHAT.backend.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.repository.PostRepository;
import MSACHAT.backend.repository.UserRepository;
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
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentEntity> findAllCommentsByPostId(Integer postId, Integer pageNum, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByPostIdOrderByTimeStampDesc(postId,
                pageRequest);
        List<CommentEntity> comments = commentEntityPage.get().collect(Collectors.toList());
        for (int i = 0; i < comments.size(); i++) {
            CommentEntity tmpEntity = comments.get(i);
            UserEntity user = userRepository.findUserEntityById(tmpEntity.getUserId());
            tmpEntity.setUserAvatar(user.getAvatar());
            tmpEntity.setUserName(user.getUsername());
            comments.set(i, tmpEntity);
        }
        return comments;
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
        PostEntity post=postRepository.findPostEntityById(postId);
        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);
    }

    @Override
    public Integer countTotalPagesByPageSize(Integer pageSize) {
        double pageCount = commentRepository.count() / (pageSize * 1.0);
        return (int) Math.ceil(pageCount) - 1;
    }

//    @Override
//    public CommentEntity findCommentById(Integer commentId) {
//        return commentRepository.findCommentEntityById(commentId);
//
//    }
//
//    @Override
//    public String deleteComment(Integer commentId) {
//
//        commentRepository.deleteById(commentId);
//        return "comment Deleted successfully";
//    }
}
