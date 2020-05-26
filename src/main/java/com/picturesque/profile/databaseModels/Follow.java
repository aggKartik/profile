package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.UserID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;


public class Follow {

    @Id
    public ObjectId id;
    // following is the person who is making the request
    public UserID following;
    // userID is the person who is being followed
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
