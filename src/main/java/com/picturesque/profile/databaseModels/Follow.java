package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.UserID;


public class Follow {
    public UserID follower;
    public UserID following;

    public Follow(UserID follower, UserID following) {
        this.follower = follower;
        this.following = following;
    }

    public UserID getFollower() {
        return follower;
    }

    public void setFollower(UserID follower) {
        this.follower = follower;
    }

    public UserID getFollowing() {
        return following;
    }

    public void setFollowing(UserID following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "follower=" + follower +
                ", following=" + following +
                '}';
    }
}
