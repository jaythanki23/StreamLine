package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Friend;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.FriendRepository;
import org.example.StreamLine.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService implements FriendServiceInterface {
    private FriendRepository friendRepository;
    private UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> getFollowers(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + userId + " doesn't exist");
        }

        return friendRepository.findByFolloweeId(userId);
    }

    @Override
    public List<Friend> getFollowing(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + userId + " doesn't exist");
        }

        return friendRepository.findByFollowerId(userId);
    }

    @Override
    public Friend follow(Friend friend) throws UserNotFoundException {
        User follower = userRepository.findById(friend.getFollower().getId()).orElse(null);
        User followee = userRepository.findById(friend.getFollowee().getId()).orElse(null);

        if(follower == null) {
            throw new UserNotFoundException("User with id " + friend.getFollower().getId() + " doesn't exist");
        }

        if(followee == null) {
            throw new UserNotFoundException("User with id " + friend.getFollowee().getId() + " doesn't exist");
        }

        return friendRepository.save(friend);
    }

    @Override
    public String removeFollower(Integer user1, Integer user2) throws UserNotFoundException {
        User follower = userRepository.findById(user1).orElse(null);
        User followee = userRepository.findById(user2).orElse(null);

        if(follower == null) {
            throw new UserNotFoundException("User with id " + user1 + " doesn't exist");
        }

        if(followee == null) {
            throw new UserNotFoundException("User with id " + user2 + " doesn't exist");
        }

        friendRepository.deleteByFollowerIdAndFolloweeId(user2, user1);
        return "Follower has been removed";
    }

    @Override
    public String stopFollowing(Integer user1, Integer user2) throws UserNotFoundException {
        User follower = userRepository.findById(user1).orElse(null);
        User followee = userRepository.findById(user2).orElse(null);

        if(follower == null) {
            throw new UserNotFoundException("User with id " + user1 + " doesn't exist");
        }

        if(followee == null) {
            throw new UserNotFoundException("User with id " + user2 + " doesn't exist");
        }
        friendRepository.deleteByFollowerIdAndFolloweeId(user1, user2);
        return "Stopped following";
    }


}
