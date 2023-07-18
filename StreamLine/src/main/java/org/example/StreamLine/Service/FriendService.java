package org.example.StreamLine.Service;


import org.example.StreamLine.Model.Friend;
import org.example.StreamLine.Repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService implements FriendServiceInterface {
    private FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> getFollowers(Integer userId) {
        return friendRepository.findByFolloweeId(userId);
    }

    @Override
    public List<Friend> getFollowing(Integer userId) {
        return friendRepository.findByFollowerId(userId);
    }

    @Override
    public Friend follow(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public String removeFollower(Integer user1, Integer user2) {
        friendRepository.deleteByFollowerIdAndFolloweeId(user2, user1);
        return "Follower has been removed";
    }

    @Override
    public String stopFollowing(Integer user1, Integer user2) {
        friendRepository.deleteByFollowerIdAndFolloweeId(user1, user2);
        return "Stopped following";
    }


}
