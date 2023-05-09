package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Friend;

import java.util.List;

public interface FriendServiceInterface {
    List<Friend> getFollowers(Integer userId);

    List<Friend> getFollowing(Integer userId);

    Friend follow(Friend friend);

    String removeFollower(Integer user1, Integer user2);

    String stopFollowing(Integer user1, Integer user2);
}
