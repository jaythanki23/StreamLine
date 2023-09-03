package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.CommentNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Comment;

import java.util.List;

public interface CommentServiceInterface {
    // get all the comments by a user
    List<Comment> getCommentsByUser(Integer userId) throws UserNotFoundException;

    // get all the comments on a post
    List<Comment> getCommentsByPost(Integer postId) throws PostNotFoundException;

    // make a comment
    Comment createComment(Comment comment) throws UserNotFoundException, PostNotFoundException;

    // edit a comment
    Comment updateComment(Integer commentId, Comment comment) throws CommentNotFoundException;

    // delete a comment
    String deleteCommentById(Integer commentId) throws CommentNotFoundException;

}
