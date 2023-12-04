package MSACHAT.service.impl;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.LikeEntity;
import MSACHAT.entity.PostEntity;
import MSACHAT.repository.LikeRepository;
import MSACHAT.repository.PostRepository;
import MSACHAT.service.PostService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private LikeRepository likeRepository;

    PostServiceImpl(
            PostRepository postRepository,
            LikeRepository likeRepository
    ) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public ArrayList<PostEntity> findAll(String token, String secret) {
        ArrayList<PostEntity> posts = new java.util.ArrayList<>(StreamSupport.stream(postRepository
                        .findAll()
                        .spliterator(), false)
                .toList());
        Jws<Claims> jws;
        jws = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
        Integer userId = (Integer) jws.getBody().get("userId");
        for (int i = 0; i < posts.size(); i++) {
            int finalI = i;
            PostEntity tmpEntity = posts.get(i);
            if (likeRepository.findAllByUserId(userId).stream().anyMatch(
                    likeEntity -> likeEntity.getPostId().equals(posts.get(finalI).getId())
            )) {
                tmpEntity.setLiked(true);
                posts.set(i, tmpEntity);
            } else {
                tmpEntity.setLiked(false);
                posts.set(i,tmpEntity);
            }
        }
        return posts;
    }

    @Override
    public PostEntity addPost(PostEntity postEntity) {
        postEntity.setTime(new Date(System.currentTimeMillis()));
        return postRepository.save(postEntity);
    }


}
