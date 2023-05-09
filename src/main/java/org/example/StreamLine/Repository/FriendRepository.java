package org.example.StreamLine.Repository;

import org.example.StreamLine.Model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findByFollowerId(Integer userId);

    List<Friend> findByFolloweeId(Integer userId);

    String deleteByFollowerIdAndFolloweeId(Integer user1, Integer user2);

}
