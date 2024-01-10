<<<<<<<HEAD package MSACHAT.controller;=======
package MSACHAT.backend.controller;

>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;<<<<<<<HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.entity.CommentEntity;
import MSACHAT.service.CommentService;

import java.util.ArrayList;=======
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.backend.dto.ErrorDto;
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

    <<<<<<<HEAD<<<<<<<HEAD @GetMapping("/post/{postId}")

    public ResponseEntity<List<CommentEntity>> getCommentsByPostId(@PathVariable Integer postId) {
        List<CommentEntity> comments = commentService.findCommentsByPostId(postId);
=======
    @GetMapping("/{postId}")
=======

    @GetMapping("/get/{postId}")
    public ResponseEntity<Object> getAllCommentsByPostId(
            @PathVariable Integer postId,
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        List<CommentEntity> comments = commentService.findAllCommentsByPostId(postId, pageNum, pageSize);
        Map<String, Object> returnResult = new HashMap<>();
        returnResult.put("comments", comments);
        returnResult.put("totalPages", commentService.countTotalPagesByPageSize(pageSize));
        return ResponseEntity.ok(returnResult);
    }

}<<<<<<<HEAD

=======>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32
