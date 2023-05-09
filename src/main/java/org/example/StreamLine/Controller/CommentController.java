package org.example.StreamLine.Controller;

import org.example.StreamLine.Model.Comment;
import org.example.StreamLine.Service.CommentServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentServiceInterface commentService;

    public CommentController(CommentServiceInterface commentService) {
        super();
        this.commentService = commentService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable("id") Integer userId) {
        return new ResponseEntity<List<Comment>>(commentService.getCommentsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("id") Integer postId) {
        return new ResponseEntity<List<Comment>>(commentService.getCommentsByPost(postId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<Comment>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Integer commentId, @RequestBody Comment comment) {
        return new ResponseEntity<Comment>(commentService.updateComment(commentId, comment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Integer commentId) {
        return new ResponseEntity<String>(commentService.deleteCommentById(commentId), HttpStatus.OK);
    }

}
