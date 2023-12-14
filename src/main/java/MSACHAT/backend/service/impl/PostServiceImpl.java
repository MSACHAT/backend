package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.repository.CommentRepository;
import MSACHAT.backend.repository.PostRepository;
import MSACHAT.backend.service.PostService;
import MSACHAT.backend.entity.LikeEntity;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.repository.LikeRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private LikeRepository likeRepository;
<<<<<<< HEAD
    private MSACHAT.repository.CommentRepository commentRepository;

    PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository,
            MSACHAT.repository.CommentRepository commentRepository) {
=======
    private CommentRepository commentRepository;
    public PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository,
            CommentRepository commentRepository
    ) {
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
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
            } else {
                tmpEntity.setLiked(false);
                posts.set(i, tmpEntity);
            }
        }
        return posts;
    }

<<<<<<< HEAD
=======
    @Override
    public PostEntity addPost(PostEntity postEntity) {
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
        likeRepository.deleteLikeEntityByUserIdAndPostId(userId, postId);
        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
        return "successfully unliked";
    }

    @Override
    public String deletePost(Integer postId) {
        postRepository.deletePostEntityById(postId);
        likeRepository.deleteAllByPostId(postId);
        commentRepository.deleteAllByPostId(postId);
        return "post Deleted successfully";
    }

    @Override
    public Boolean IsLiked(Integer postId, Integer userId) {


        return likeRepository.existsByUserIdAndPostId(postId,userId);
    }
    @Override
    public PostEntity findPostById(Integer postId, Integer userId){
        PostEntity post = postRepository.findPostEntityById(postId);

        if (likeRepository.existsByUserIdAndPostId(userId,postId)){
            post.setLiked(true);
        } else {
            post.setLiked(false);
        }
        return post;
    }



}
