package com.picturesque.profile.helperModels;

public class UserID {

  public String userId;

  public UserID(String userId) {
    this.userId = userId;
  }

  public Object getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "userId{" + "userId=" + userId + '}';
  }
}
