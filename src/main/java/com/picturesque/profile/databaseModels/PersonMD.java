package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;

import java.util.Date;
import java.util.List;

// Person MetaData

public class PersonMD {

  public UserID userId;
  public Date dob;
  public Date dateJoined;
  public Date lastLogin;
  // https://stackoverflow.com/questions/8677707/data-type-for-storing-ip-addresses
  public String lastIP;
  public String bio;
  public List<GroupID> groupIds;

  public PersonMD(
      UserID userId,
      Date dob,
      Date dateJoined,
      Date lastLogin,
      String lastIP,
      String bio,
      List<GroupID> groupIds) {
    this.userId = userId;
    this.dob = dob;
    this.dateJoined = dateJoined;
    this.lastLogin = lastLogin;
    this.lastIP = lastIP;
    this.bio = bio;
    this.groupIds = groupIds;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public UserID getUserId() {
    return userId;
  }

  public void setUserId(UserID userId) {
    this.userId = userId;
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

  public List<GroupID> getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(List<GroupID> groupIds) {
    this.groupIds = groupIds;
  }

  @Override
  public String toString() {
    return "PersonMD{"
        + "userId='"
        + userId
        + '\''
        + ", dob="
        + dob
        + ", dateJoined="
        + dateJoined
        + ", lastLogin="
        + lastLogin
        + ", lastIP="
        + lastIP
        + ", groupIds="
        + groupIds
        + '}';
  }
}
