package MSACHAT.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.backend.dto.ErrorDto;
import MSACHAT.backend.dto.PageNumDto;
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

    @GetMapping("/{postId}")
    public ResponseEntity<Object> getAllCommentsByPostId(
            @PathVariable Integer postId,
            @RequestParam Integer pageNum) {
        List<CommentEntity> comments = commentService.findAllCommentsByPostId(postId, pageNum);

        // Check for null content in comments
        for (CommentEntity comment : comments) {
            if (comment.getContent() == null) {
                ErrorDto err = new ErrorDto("Comment Content is null.", 10002);
                return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
            }
        }

        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deletePost(
            @PathVariable("id") Integer commentId) {
        if (commentService.findCommentById(commentId) == null) {
            ErrorDto err = new ErrorDto("comment No Longer Exists.", 10001);
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
        }
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
