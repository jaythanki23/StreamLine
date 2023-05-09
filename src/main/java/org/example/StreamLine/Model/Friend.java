package org.example.StreamLine.Model;

import jakarta.persistence.*;

@Entity
@Table(name="friends_table")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;


    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee;

    public Friend() {
    }

    public Friend(Integer id, User follower, User followee) {
        this.id = id;
        this.follower = follower;
        this.followee = followee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }


}

