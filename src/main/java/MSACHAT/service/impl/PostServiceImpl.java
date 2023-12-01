package MSACHAT.service.impl;

<<<<<<< HEAD
import MSACHAT.dto.PostWithCommentsDTO;
=======
import MSACHAT.dto.PostDto;
>>>>>>> 038cd2814504761c8bd1a2f888cd9051a1523d21
import MSACHAT.entity.PostEntity;
import MSACHAT.repository.CommentRepository;
import MSACHAT.repository.PostRepository;
import MSACHAT.service.PostService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<PostEntity> findAll() {
        return StreamSupport.stream(postRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public PostWithCommentsDTO getPostWithComments(int postId) {
        // 获取帖子信息
        PostEntity postEntity = postRepository.findById(postId).orElse(null);

        if (postEntity == null) {
            // 处理帖子不存在的情况，根据实际需求返回空或者抛出异常
            return null;
        }

        // 获取帖子的评论信息
        // 假设commentRepository有一个类似于findCommentsByPostId的方法
        List<CommentEntity> comments = commentRepository.findCommentsByPostId(postId);

        // 构建PostWithCommentsDTO对象
        return new PostWithCommentsDTO(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.getTime(), mapToCommentDTOs(comments));
    }

    private List<PostWithCommentsDTO.CommentDTO> mapToCommentDTOs(List<CommentEntity> comments) {
        // 将CommentEntity转换为CommentDTO
        return comments.stream()
                .map(comment -> new PostWithCommentsDTO.CommentDTO(comment.getId(), comment.getUserId(), comment.getComment(), comment.getTime()))
                .collect(Collectors.toList());
    }

    @Override
    public PostEntity addPost(PostEntity postEntity) {
        postEntity.setTime(new Date(System.currentTimeMillis()));
        return postRepository.save(postEntity);
    }


}
