package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Friend;

import java.util.List;

public interface FriendServiceInterface {
    List<Friend> getFollowers(Integer userId) throws UserNotFoundException;

    List<Friend> getFollowing(Integer userId) throws UserNotFoundException;

    Friend follow(Friend friend) throws UserNotFoundException;

    String removeFollower(Integer user1, Integer user2) throws UserNotFoundException;

    String stopFollowing(Integer user1, Integer user2) throws UserNotFoundException;
}
