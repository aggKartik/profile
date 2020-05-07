package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.UserID;

import java.util.List;


/**
 * Person
 *
 * Name
 * User_name (specified by user)
 * User_id -generated
 * Token - login info Password (encrypted?)
 * Points
 * Picture URL
 * Private/Public - ENUM
 * Follower Invite List (MAX 1000)
 * Group Invite List (MAX 1000)
 *
 */

public class Person {

  public enum PROFILE_PRIVACY {
    PRIVATE, PUBLIC
  }

//  public @Id
//  int id;
  public String name;
  public String userName;
  private UserID userId;
  public String token; // probably need a seperate token class?
  public String pass; // probably need a Password token class?
  public int points;
  public String pic;
  public PROFILE_PRIVACY profileType;
  public List<String> followerInvite;
  public List<String> groupInvite;

  //int id,
  public Person(String name, String userName, UserID userId, String token, String pass,
                String pic, int points, PROFILE_PRIVACY profileType, List<String> followerInvite,
                List<String> groupInvite) {
    //this.id = id;
    this.name = name;
    this.userName = userName;
    this.userId = userId;
    this.token = token;
    this.pass = pass;
    this.pic = pic;
    this.points = points;
    this.profileType = profileType;
    this.followerInvite = followerInvite;
    this.groupInvite = groupInvite;
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

  public List<String> getFollowerInvite() {
    return followerInvite;
  }

  public void setFollowerInvite(List<String> followerInvite) {
    this.followerInvite = followerInvite;
  }

  public List<String> getGroupInvite() {
    return groupInvite;
  }

  public void setGroupInvite(List<String> groupInvite) {
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

  public UserID getUserId() {
    return userId;
  }

  public void setUserId(UserID userId) {
    this.userId = userId;
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


  @Override
  public String toString() {
    return "Person{" +
         //   "id=" + id +
            ", name='" + name + '\'' +
            ", userName='" + userName + '\'' +
            ", userId='" + userId + '\'' +
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
