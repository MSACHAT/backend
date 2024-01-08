package MSACHAT.backend.controller;

import MSACHAT.backend.dto.CommentsReturn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Object CommentsReturn = new CommentsReturn(comments,20,true);

        return ResponseEntity.ok(CommentsReturn);
    }

}
