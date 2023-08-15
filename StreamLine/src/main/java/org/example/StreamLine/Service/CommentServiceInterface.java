package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.CommentNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Comment;

import java.util.List;

public interface CommentServiceInterface {
    List<Comment> getCommentsByUser(Integer userId) throws UserNotFoundException;

    List<Comment> getCommentsByPost(Integer postId) throws PostNotFoundException;

    Comment createComment(Comment comment) throws UserNotFoundException, PostNotFoundException;

    Comment updateComment(Integer commentId, Comment comment) throws CommentNotFoundException;

    String deleteCommentById(Integer commentId) throws CommentNotFoundException;

}
