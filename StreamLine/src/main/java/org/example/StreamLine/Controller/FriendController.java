package org.example.StreamLine.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Friend;
import org.example.StreamLine.Service.FriendService;
import org.example.StreamLine.Service.FriendServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    private FriendServiceInterface friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping({"/followers/{id}"})
    public ResponseEntity<List<Friend>> getFollowers(@PathVariable("id") Integer userId) throws UserNotFoundException {
        return new ResponseEntity<List<Friend>>(friendService.getFollowers(userId), HttpStatus.OK);
    }

    @GetMapping({"/following/{id}"})
    public ResponseEntity<List<Friend>> getFollowing(@PathVariable("id") Integer userId) throws UserNotFoundException {
        return new ResponseEntity<List<Friend>>(friendService.getFollowing(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Friend> follow(@RequestBody @Valid Friend friend) throws UserNotFoundException {
        return new ResponseEntity<Friend>(friendService.follow(friend), HttpStatus.CREATED);
    }

    @DeleteMapping("/followers/{id1}/{id2}")
    @Transactional
    public ResponseEntity<String> removeFollower(@PathVariable("id1") Integer user1, @PathVariable("id2") Integer user2) throws UserNotFoundException {
        return new ResponseEntity<String>(friendService.removeFollower(user1, user2), HttpStatus.OK);
    }

    @DeleteMapping("/following/{id1}/{id2}")
    @Transactional
    public ResponseEntity<String> stopFollowing(@PathVariable("id1") Integer user1, @PathVariable("id2") Integer user2) throws UserNotFoundException {
        return new ResponseEntity<String>(friendService.stopFollowing(user1, user2), HttpStatus.OK);
    }

}
