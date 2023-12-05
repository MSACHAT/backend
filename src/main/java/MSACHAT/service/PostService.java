package MSACHAT.service;

import MSACHAT.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;

import java.util.List;

public interface PostService {
    public PostEntity addPost(PostEntity postEntity);

    public ArrayList<PostEntity> findAll(String token, String secret);

    public String likePost(Integer postId,Integer userId);

    public String unlikePost(Integer postId,Integer userId);
    public Integer getUserIdFromToken(String token,String secret);
}
