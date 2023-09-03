package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Friend;

import java.util.List;

public interface FriendServiceInterface {
    // get all followers
    List<Friend> getFollowers(Integer userId) throws UserNotFoundException;

    // get all the accounts being followed by a user
    List<Friend> getFollowing(Integer userId) throws UserNotFoundException;

    // follow an account
    Friend follow(Friend friend) throws UserNotFoundException;

    // remove a follower
    String removeFollower(Integer user1, Integer user2) throws UserNotFoundException;

    // stop following an account
    String stopFollowing(Integer user1, Integer user2) throws UserNotFoundException;
}
