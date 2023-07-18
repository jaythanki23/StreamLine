package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Comment;

import java.util.List;

public interface CommentServiceInterface {
    List<Comment> getCommentsByUser(Integer userId);

    List<Comment> getCommentsByPost(Integer postId);

    Comment createComment(Comment comment);

    Comment updateComment(Integer commentId, Comment comment);

    String deleteCommentById(Integer commentId);

}
