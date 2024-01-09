package MSACHAT.backend.controller;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<Object> getAllCommentsByPostId(
            @PathVariable Integer postId,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ) {
        List<CommentEntity> comments = commentService.findAllCommentsByPostId(postId, pageNum,pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("comments",comments);
        returnResult.put("totalPages",commentService.countTotalPagesByPageSize(pageSize));
        return ResponseEntity.ok(returnResult);
    }

}
