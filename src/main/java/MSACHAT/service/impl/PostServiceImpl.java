package MSACHAT.service.impl;

import MSACHAT.dto.PostWithCommentsDto;
import MSACHAT.entity.PostEntity;
import MSACHAT.repository.CommentRepository;
import MSACHAT.repository.PostRepository;
import MSACHAT.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;


    }
    @Override
    public PostEntity addPost(PostEntity postEntity) {
        postRepository.save(postEntity);
        return postEntity;
    }

    

    public List<PostEntity> findAllPosts() {
        return postRepository.findAllWithComments();
    }

    @Override
    public List<PostEntity> findAll() {
        return StreamSupport.stream(postRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

}