package MSACHAT.service.Impl;

import MSACHAT.repository.PostRepository;
import MSACHAT.service.PostService;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    PostServiceImpl(PostRepository postRepository){
        this.postRepository=postRepository;
    }
}
