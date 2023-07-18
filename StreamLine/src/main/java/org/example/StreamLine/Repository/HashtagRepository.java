package org.example.StreamLine.Repository;

import org.example.StreamLine.Model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    List<Hashtag> findByPostId(Integer postId);
}
