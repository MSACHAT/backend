package MSACHAT.service.impl;

import MSACHAT.dto.PostDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.repository.PostRepository;
import MSACHAT.service.PostService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    PostServiceImpl(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    @Override
    public List<PostEntity> findAll() {
        return StreamSupport.stream(postRepository
                .findAll()
                .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public PostEntity addPost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }


}
