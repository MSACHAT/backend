package MSACHAT.backend.service.impl;

import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostUserIsLikeDto;
import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.repository.*;

import MSACHAT.backend.service.PostService;
import MSACHAT.backend.entity.LikeEntity;
import MSACHAT.backend.entity.PostEntity;
import jakarta.transaction.Transactional;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
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
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<PostEntity> postEntityPage = postRepository.findAll(pageRequest);
        List<PostEntity> posts = postEntityPage.getContent();
        for (int i = 0; i < posts.size(); i++) {
            int finalI = i;
            PostEntity tmpEntity = posts.get(finalI);
            if (likeRepository.findAllByUserId(userId) != null) {
                if (likeRepository.findAllByUserId(userId).stream().anyMatch(
                        likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId()))) {
                    tmpEntity.setLiked(true);
                    posts.set(finalI, tmpEntity);
                }
            } else {
                tmpEntity.setLiked(false);
                posts.set(finalI, tmpEntity);
            }
        }
        return posts;
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
        double pageCount = postRepository.count() / (pageSize * 1.0);
        return (Integer) (int) Math.ceil(pageCount) - 1;
    }

    // @Override
    // public Page<PostEntity> getPostsByUserId(Integer userId, Pageable pageable) {
    // return postRepository.findByUserId(userId, pageable);
    // }

    // @Override
    // public List<PostEntity> findPostsByPageNumselected(Integer userId, Integer
    // pageNum, Integer pageSize) {
    // PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

    // Page<PostEntity> postEntityPage = postRepository.findByUserId(userId,
    // pageRequest);
    // List<PostEntity> posts = postEntityPage.getContent();
    // //
    // for (int i = 0; i < posts.size(); i++) {
    // int finalI = i;
    // PostEntity tmpEntity = posts.get(finalI);
    // if (likeRepository.findAllByUserId(userId) != null) {
    // if (likeRepository.findAllByUserId(userId).stream().anyMatch(
    // likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId()))) {
    // tmpEntity.setLiked(true);
    // posts.set(finalI, tmpEntity);
    // }
    // } else {
    // tmpEntity.setLiked(false);
    // posts.set(finalI, tmpEntity);
    // }
    // }
    // return posts;
    // }

    // @Override
    // public Page<PostDto> getAllByUserId(Integer userId, Integer pageNum, Integer
    // pageSize) {
    // PageRequest pageable = PageRequest.of(pageNum, pageSize);
    // return postRepository.findAllByUserId(userId, pageable);
    // }
    private Boolean isPostLiked(Integer userId, Integer postId) {
        LikeEntity likeEntity = likeRepository.findByUserIdAndPostId(userId, postId);
        return likeEntity != null;
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
    // }
    @Override
    public List<PostEntity> getAllPostsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<PostEntity> postEntityPage = postRepository.findAllByUserId(userId, pageRequest);
        List<PostEntity> posts = postEntityPage.getContent();
        for (int i = 0; i < posts.size(); i++) {
            int finalI = i;
            PostEntity tmpEntity = posts.get(finalI);
            if (likeRepository.findAllByUserId(userId) != null) {
                if (likeRepository.findAllByUserId(userId).stream().anyMatch(
                        likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId()))) {
                    tmpEntity.setLiked(true);
                    posts.set(finalI, tmpEntity);
                }
            } else {
                tmpEntity.setLiked(false);
                posts.set(finalI, tmpEntity);
            }
        }
        return posts;
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
