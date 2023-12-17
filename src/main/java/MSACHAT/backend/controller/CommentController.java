<<<<<<< HEAD
package MSACHAT.controller;
=======
package MSACHAT.backend.controller;
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.entity.CommentEntity;
import MSACHAT.service.CommentService;

import java.util.ArrayList;
=======
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MSACHAT.backend.dto.PageNumDto;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.service.CommentService;

>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

<<<<<<< HEAD
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentEntity>> getCommentsByPostId(@PathVariable Integer postId) {
        List<CommentEntity> comments = commentService.findCommentsByPostId(postId);
=======
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentEntity>> getAllCommentsByPostId(
            @PathVariable Integer postId,
            @RequestParam Integer pageNum) {
        List<CommentEntity> comments = commentService.findAllCommentsByPostId(postId, pageNum);
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
        return ResponseEntity.ok(comments);
    }

}
<<<<<<< HEAD

=======
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
