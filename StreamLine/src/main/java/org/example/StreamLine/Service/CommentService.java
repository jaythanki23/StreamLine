package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.CommentNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Comment;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.CommentRepository;
import org.example.StreamLine.Repository.PostRepository;
import org.example.StreamLine.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentServiceInterface {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Comment> getCommentsByUser(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);

        if(user != null) {
            return commentRepository.findByUserId(userId);
        } else {
            throw new UserNotFoundException("User with id " + userId + " doesn't exist");
        }
    }

    public List<Comment> getCommentsByPost(Integer postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElse(null);

        if(post != null) {
            return commentRepository.findByPostId(postId);
        } else {
            throw new PostNotFoundException("Post with id " + postId + " doesn't exist");
        }

    }

    public Comment createComment(Comment comment) throws UserNotFoundException, PostNotFoundException {
        User user = userRepository.findById(comment.getUser().getId()).orElse(null);
        Post post = postRepository.findById(comment.getPost().getId()).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + comment.getUser().getId() + " doesn't exist");
        }

        if(post == null) {
            throw new PostNotFoundException("Post with id " + comment.getPost().getId() + " doesn't exist");
        }


        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer commentId, Comment comment) throws CommentNotFoundException {
        Comment dbComment = commentRepository.findById(commentId).get();

        if(dbComment != null) {
            if(dbComment.getUser().getId() == comment.getUser().getId() && dbComment.getPost().getId() == comment.getPost().getId()) {
                dbComment.setDescription(comment.getDescription());
            }
            return commentRepository.save(dbComment);
        } else {
            throw new CommentNotFoundException("Comment with id " + commentId + " doesn't exist");
        }
    }

    public String deleteCommentById(Integer commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if(comment != null) {
            commentRepository.deleteById(commentId);
            return "The comment has been deleted";
        } else {
            throw new CommentNotFoundException("Comment with id " + commentId + " doesn't exist");
        }

    }

}
