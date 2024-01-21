package MSACHAT.backend.service.impl;

import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostUserIsLikeDto;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.repository.*;
import MSACHAT.backend.repository.PostRepository.PostResponse;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.entity.LikeEntity;
import MSACHAT.backend.entity.PostEntity;
import jakarta.transaction.Transactional;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.data.domain.*;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository,
            CommentRepository commentRepository,
            ImageRepository imageRepository,

            UserRepository userRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostEntity> findPostsByPageNum(Integer userId, Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "timeStamp");
        Pageable pageRequest = PageRequest.of(pageNum, pageSize, sort);
        Page<PostEntity> posts = postRepository.findAll(pageRequest);
        for (PostEntity post : posts) {
            post.setLiked(likeRepository.existsByUserIdAndPostId(userId, post.getId()));
        }
        return posts.getContent();
    }

    @Override
    public PostEntity addPost(Integer userId, String content) {
        String userName = userRepository.findNameById(userId);

        PostEntity postEntity = new PostEntity();
        postEntity.setUserName(userName);

        postEntity.setContent(content);
        postEntity.setUserId(userId);
        postEntity.setLiked(false);
        postEntity.setCommentCount(0);
        postEntity.setLikeCount(0);
        postEntity.setTimeStamp(new Date(System.currentTimeMillis()));
        return postRepository.save(postEntity);
    }

    @Override
    public String likePost(Integer postId, Integer userId) {
        LikeEntity newLike = new LikeEntity();
        PostEntity post = postRepository.findPostEntityById(postId);
        newLike.setUserId(userId);
        newLike.setPostId(postId);
        Date date = new Date(System.currentTimeMillis());
        newLike.setTimeStamp(date);
        likeRepository.save(newLike);
        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
        return "new like saved";
    }

    @Override
    public String unlikePost(Integer postId, Integer userId) {
        PostEntity post = postRepository.findPostEntityById(postId);
        likeRepository.deleteByUserIdAndPostId(userId, postId);
        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);
        return "successfully unliked";
    }

    @Override
    public String deletePost(Integer postId) {
        postRepository.deleteById(postId);
        likeRepository.deleteAllByPostId(postId);
        commentRepository.deleteAllByPostId(postId);
        return "post Deleted successfully";
    }

    @Override
    public Boolean IsLiked(Integer postId, Integer userId) {

        return likeRepository.existsByUserIdAndPostId(postId, userId);
    }

    @Override
    public PostEntity findPostByIdAndUserId(Integer postId, Integer userId) {
        PostEntity post = postRepository.findPostEntityById(postId);

        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            post.setLiked(true);
        } else {
            post.setLiked(false);
        }

        return post;
    }

    @Override
    public ImageEntity addImage(PostEntity postEntity, String imagePath) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPostId(postEntity);
        imageEntity.setImageUrl(imagePath);
        return imageRepository.save(imageEntity);
    }

    @Override
    public Boolean IsPostExist(Integer postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public PostEntity findPostById(Integer postId) {
        return postRepository.findPostEntityById(postId);
    }

    @Override
    public Integer countTotalPagesByPageSize(Integer pageSize) {
        return ((int) postRepository.count() + pageSize - 1) / pageSize;
    }

    // @Override
    // public Page<PostUserIsLikeDto> findAllByUserId(Integer userId, Integer
    // pageNum, Integer pageSize) {

    // PageRequest pageable = PageRequest.of(pageNum, pageSize);

    // Page<PostUserIsLikeDto> postDtoPage = postRepository.findAllByUserId(userId,
    // pageable)
    // .map(postEntity -> new PostUserIsLikeDto(postEntity, isPostLiked(userId,
    // postEntity.getId())));
    // return postDtoPage;

    // }

    // ______________________________________________________________________________
    @Override
    public Page<PostDto> getAllByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllByUserId'");
    }

    // @Override
    // public Page<PostEntity> getPostsByUserId(Integer userId, Pageable pageable) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getPostsByUserId'");
    // }@Override
    public Map<String, Object> getPostsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        Page<PostEntity> posts = postRepository.findAllByUserIdOrderByTimeStampDesc(userId,
                PageRequest.of(pageNum, pageSize));
        for (PostEntity post : posts) {
            post.setLiked(likeRepository.existsByUserIdAndPostId(userId, post.getId()));
        }

        Integer totalPages = ((int) postRepository.countByUserId(userId) + pageSize - 1) / pageSize;

        Map<String, Object> postsResponse = new HashMap<>();
        postsResponse.put("posts", posts);
        postsResponse.put("totalPages", totalPages);

        return postsResponse;
    }
    // @Override
    // public List<PostEntity> findPostsByUserIdAndPageNum(Integer userId, Integer
    // pageNum, Integer pageSize) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'findPostsByUserIdAndPageNum'");
    // }

    // @Override
    // public Page<PostUserIsLikeDto> findAllByUserId(Integer userId, Integer
    // pageNum, Integer pageSize) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'findAllByUserId'");
    // }
}
