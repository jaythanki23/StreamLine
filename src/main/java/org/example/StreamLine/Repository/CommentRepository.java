package org.example.StreamLine.Repository;


import org.example.StreamLine.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByUserId(Integer userId);

    public List<Comment> findByPostId(Integer postId);
}
