package MSACHAT.service.Impl;

import MSACHAT.service.PostService;


public class PostServiceImpl implements PostService {


     
    private CommentReposito r y commentRepository; 
  
    public PostServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsForPost(Post post) {
        return commentRepository.findCommentsByPost(post);
    }
}