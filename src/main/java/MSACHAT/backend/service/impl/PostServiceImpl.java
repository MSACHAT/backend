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
import org.springframework.data.domain.Page;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<<<<<<<HEAD
import java.util.ArrayList;
import java.util.stream.StreamSupport;=======
import java.sql.Date;
import java.util.List;<<<<<<<HEAD>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32=======
import java.util.stream.Collectors;>>>>>>>a37652f6c2b5e42127bf363b1c448a7a195a1f20

@Service
@Transactional
public class PostServiceImpl implements PostService {
    <<<<<<<HEAD
    private PostRepository postRepository;
    private LikeRepository likeRepository;<<<<<<<HEAD
    private MSACHAT.repository.CommentRepository commentRepository;

    PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository,
            MSACHAT.repository.CommentRepository commentRepository) {
=======

    private CommentRepository commentRepository;=======
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    >>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32

    public PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository,
            CommentRepository commentRepository,
            ImageRepository imageRepository,

<<<<<<< HEAD
    ) {
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
=======
            UserRepository userRepository) {
>>>>>>> 726cd29aca422765ef93c1fdf257065f18ba4fe2
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
<<<<<<< HEAD
    }

    @Override
    public ArrayList<PostEntity> findAll(Integer userId) {
        ArrayList<PostEntity> posts = new java.util.ArrayList<>(StreamSupport.stream(postRepository
                .findAll()
                .spliterator(), false)
                .toList());
        for (int i = 0; i < posts.size(); i++) {
            int finalI = i;
            PostEntity tmpEntity = posts.get(i);
            if (likeRepository.findAllByUserId(userId).stream().anyMatch(
                    likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId()))) {
                tmpEntity.setLiked(true);
                posts.set(i, tmpEntity);
=======
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
            // List<ImageEntity> images = new ArrayList<>();
            // images.get(0).setImageUrl("2312312313123");
            if (likeRepository.findAllByUserId(userId) != null) {
                if (likeRepository.findAllByUserId(userId).stream().anyMatch(
                        likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId()))) {
                    tmpEntity.setLiked(true);
                    posts.set(finalI, tmpEntity);
                }
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
            } else {
                tmpEntity.setLiked(false);
                posts.set(finalI, tmpEntity);
            }
        }
        return posts;
    }

    <<<<<<<HEAD=======

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
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
    }

    @Override
    public PostEntity addPost(PostEntity postEntity) {
        postRepository.save(postEntity);
        return postEntity;
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
<<<<<<< HEAD
        likeRepository.deleteLikeEntityByUserIdAndPostId(userId, postId);
=======
        likeRepository.deleteByUserIdAndPostId(userId, postId);
<<<<<<< HEAD
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
        post.setLikeCount(post.getLikeCount() + 1);
=======
        post.setLikeCount(post.getLikeCount() - 1);
>>>>>>> a37652f6c2b5e42127bf363b1c448a7a195a1f20
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

        System.out.println(post.getImages().get(0).getImageUrl());
        System.out.println("888888888888888888888888");
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
        System.out.println(pageCount);
        System.out.println("11111111111111111111111111111111111111111111");
        return (Integer) (int) Math.ceil(pageCount) - 1;
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
    public PostResponse getAllPostsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        Page<PostEntity> pageResult = postRepository.findAllByUserId(userId, PageRequest.of(pageNum, pageSize));

        List<PostEntity> posts = pageResult.getContent();

        // 提取PostId并返回S
        List<Integer> postIds = posts.stream()
                .map(PostEntity::getPostId)
                .toList();

        // 获取每个帖子的详情
        List<PostEntity> postDetails = new ArrayList<>();
        for (int i = 0; i < postIds.size(); i++) {
            postDetails.add(postRepository.findPostEntityById(postIds.get(i)));
        }
        System.out.println("999999999999999999999999999999999");
        for (int i = 0; i < postIds.size(); i++) {
            System.out.println(postDetails.get(i).getImages().get(0).getImageUrl());
        }

        List<PostEntity> postDetails1 = new ArrayList<PostEntity>();
        return new PostResponse(pageResult.getTotalPages(), pageNum, postDetails1);
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
