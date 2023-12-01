package MSACHAT.service;

import MSACHAT.entities.Post;

public interface PostService {

    List<Comment> getCommentsForPost(Post post);
}
