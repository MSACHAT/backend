package MSACHAT.service;

import MSACHAT.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

public interface PostService {
    public ArrayList<PostEntity> findAll(String token,String secret);
}
