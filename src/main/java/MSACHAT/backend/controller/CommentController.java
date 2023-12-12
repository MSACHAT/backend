package MSACHAT.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentEntity>> getCommentsByPostId(@PathVariable Integer postId) {
        List<CommentEntity> comments = commentService.findCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}