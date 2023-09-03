package org.example.StreamLine.Repository;

import org.example.StreamLine.Model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    // method to find all the followers
    List<Friend> findByFollowerId(Integer userId);

    // method to find all the accounts followed by a user
    List<Friend> findByFolloweeId(Integer userId);

    // method to stop following or remove a follower
    String deleteByFollowerIdAndFolloweeId(Integer user1, Integer user2);

}
