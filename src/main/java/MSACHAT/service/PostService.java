package MSACHAT.service;

import MSACHAT.entity.CommentEntity;
import MSACHAT.entity.PostEntity;
import MSACHAT.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;

import java.util.List;

public interface PostService {
    List<PostEntity> findAll();

    public PostEntity addPost(PostEntity postEntity);

    // public ArrayList<PostEntity> findAll(String token, String secret);

    public List<PostEntity> findAllPosts();

    public List<CommentEntity> findComment();

}
