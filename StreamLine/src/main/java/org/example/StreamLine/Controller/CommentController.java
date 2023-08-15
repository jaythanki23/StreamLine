package org.example.StreamLine.Controller;

import jakarta.validation.Valid;
import org.example.StreamLine.Exceptions.CommentNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
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
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable("id") Integer userId) throws UserNotFoundException {
        return new ResponseEntity<List<Comment>>(commentService.getCommentsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("id") Integer postId) throws PostNotFoundException {
        return new ResponseEntity<List<Comment>>(commentService.getCommentsByPost(postId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody @Valid Comment comment) throws UserNotFoundException, PostNotFoundException {
        return new ResponseEntity<Comment>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Integer commentId, @RequestBody @Valid Comment comment) throws CommentNotFoundException {
        return new ResponseEntity<Comment>(commentService.updateComment(commentId, comment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Integer commentId) throws CommentNotFoundException {
        return new ResponseEntity<String>(commentService.deleteCommentById(commentId), HttpStatus.OK);
    }

}
