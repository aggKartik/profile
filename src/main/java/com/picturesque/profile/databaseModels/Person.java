package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
import org.springframework.data.annotation.Id;

import java.util.List;


/*
Person

Name
User_name (specified by user)
User_id - generated
Token - login info
Password (encrypted?)

 */

public class Person {

  public enum PROFILE_PRIVACY {
    PRIVATE, PUBLIC
  }

  public String name;
  public String userName;
  @Id
  private UserID userID;
  public String token; // probably need a seperate token class?
  public String pass; // probably need a Password token class?
  public int points;
  public String pic;
  public PROFILE_PRIVACY profileType;
  public List<UserID> followerInvite;
  public List<GroupID> groupInvite;

  public Person(String name, String userName, UserID userID, String token, String pass,
                int points, String pic, PROFILE_PRIVACY profileType, List<UserID> followerInvite,
                List<GroupID> groupInvite) {
    this.name = name;
    this.userName = userName;
    this.userID = userID;
    this.token = token;
    this.pass = pass;
    this.points = points;
    this.pic = pic;
    this.profileType = profileType;
    this.followerInvite = followerInvite;
    this.groupInvite = groupInvite;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserID getUserID() {
    return userID;
  }

  public void setUserID(UserID userID) {
    this.userID = userID;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public PROFILE_PRIVACY getProfileType() {
    return profileType;
  }

  public void setProfileType(PROFILE_PRIVACY profileType) {
    this.profileType = profileType;
  }

  public List<UserID> getFollowerInvite() {
    return followerInvite;
  }

  public void setFollowerInvite(List<UserID> followerInvite) {
    this.followerInvite = followerInvite;
  }

  public void addFollowerInvite(UserID requester) {
    this.followerInvite.add(requester);
  }

  public List<GroupID> getGroupInvite() {
    return groupInvite;
  }

  public void setGroupInvite(List<GroupID> groupInvite) {
    this.groupInvite = groupInvite;
  }

  @Override
  public String toString() {
    return "Person{" +
            "name='" + name + '\'' +
            ", userName='" + userName + '\'' +
            ", userID=" + userID +
            ", token='" + token + '\'' +
            ", pass='" + pass + '\'' +
            ", points=" + points +
            ", pic='" + pic + '\'' +
            ", profileType=" + profileType +
            ", followerInvite=" + followerInvite +
            ", groupInvite=" + groupInvite +
            '}';
  }
}
