package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.UserID;

import java.util.Date;


public class Follow {
    public UserID following;
    public UserID userID;
    public Date dateFollowed;

    public Follow(UserID userID, UserID following, Date dateFollowed) {
        this.userID = userID;
        this.following = following;
        this.dateFollowed = dateFollowed;
    }

    public UserID getUserID() {
        return userID;
    }

    public Date getDateFollowed() {
        return dateFollowed;
    }

    public void setDateFollowed(Date dateFollowed) {
        this.dateFollowed = dateFollowed;
    }

    public void setUserID(UserID userID) {
        this.userID = userID;
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
                "userID=" + userID +
                ", following=" + following +
                '}';
    }
}
