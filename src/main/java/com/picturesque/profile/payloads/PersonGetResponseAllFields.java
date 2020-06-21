package com.picturesque.profile.payloads;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;

import java.util.Date;
import java.util.List;

public class PersonGetResponseAllFields extends PersonGetResponse{

  private Integer points;
  private Person.PROFILE_PRIVACY profileType;
  private List<UserID> followerInvite;
  private List<GroupID> groupInvite;
  private String picture;
  private Date dob;
  private Date dateJoined;
  private Date lastLogin;
  private String lastIP;
  private List<GroupID> groupIds;

  public PersonGetResponseAllFields(String userName, Integer numFollowers, Integer numFollowing,
                                    String picture, String name, Integer points,
                                    Person.PROFILE_PRIVACY profileType, List<UserID> followerInvite,
                                    List<GroupID> groupInvite, Date dob,
                                    Date dateJoined, Date lastLogin, String lastIP, String bio,
                                    List<GroupID> groupIds) {
    super(userName, numFollowers, numFollowing, picture, name, bio);
    this.points = points;
    this.profileType = profileType;
    this.followerInvite = followerInvite;
    this.groupInvite = groupInvite;
    this.picture = picture;
    this.dob = dob;
    this.dateJoined = dateJoined;
    this.lastLogin = lastLogin;
    this.lastIP = lastIP;
    this.groupIds = groupIds;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public Person.PROFILE_PRIVACY getProfileType() {
    return profileType;
  }

  public void setProfileType(Person.PROFILE_PRIVACY profileType) {
    this.profileType = profileType;
  }

  public List<UserID> getFollowerInvite() {
    return followerInvite;
  }

  public void setFollowerInvite(List<UserID> followerInvite) {
    this.followerInvite = followerInvite;
  }

  public List<GroupID> getGroupInvite() {
    return groupInvite;
  }

  public void setGroupInvite(List<GroupID> groupInvite) {
    this.groupInvite = groupInvite;
  }

  @Override
  public String getPicture() {
    return picture;
  }

  @Override
  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Date getDateJoined() {
    return dateJoined;
  }

  public void setDateJoined(Date dateJoined) {
    this.dateJoined = dateJoined;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public String getLastIP() {
    return lastIP;
  }

  public void setLastIP(String lastIP) {
    this.lastIP = lastIP;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public List<GroupID> getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(List<GroupID> groupIds) {
    this.groupIds = groupIds;
  }

  @Override
  public String toString() {
    return "PersonGetResponseAllFields{" +
            "points=" + points +
            ", profileType=" + profileType +
            ", followerInvite=" + followerInvite +
            ", groupInvite=" + groupInvite +
            ", picture='" + picture + '\'' +
            ", dob=" + dob +
            ", dateJoined=" + dateJoined +
            ", lastLogin=" + lastLogin +
            ", lastIP='" + lastIP + '\'' +
            ", bio='" + bio + '\'' +
            ", groupIds=" + groupIds +
            ", userName='" + userName + '\'' +
            ", numFollowers=" + numFollowers +
            ", numFollowing=" + numFollowing +
            ", picture='" + picture + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
