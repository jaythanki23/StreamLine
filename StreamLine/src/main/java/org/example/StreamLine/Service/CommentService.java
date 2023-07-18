package org.example.StreamLine.Service;


import org.example.StreamLine.Model.Comment;
import org.example.StreamLine.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentServiceInterface {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {

        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsByUser(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    public List<Comment> getCommentsByPost(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer commentId, Comment comment) {
        Comment dbComment = commentRepository.findById(commentId).get();

        if(dbComment.getUser().getId() == comment.getUser().getId() && dbComment.getPost().getId() == comment.getPost().getId()) {
            dbComment.setDescription(comment.getDescription());
        }
        return commentRepository.save(dbComment);
    }

    public String deleteCommentById(Integer commentId) {
        commentRepository.deleteById(commentId);
        return "The comment has been deleted";
    }

}
