package com.picturesque.profile.payloads;

// At minimum: user_name, number of followers and following, picture, name

/**
 * This abstract class allows for a Person's information to be queried and returned
 * in appropriate amounts depending on privilage.
 */
public abstract class PersonGetResponse {

  protected String userName;
  protected Integer numFollowers;
  protected Integer numFollowing;
  protected String picture;
  protected String name;

  public PersonGetResponse(String userName, Integer numFollowers, Integer numFollowing, String picture, String name) {
    this.userName = userName;
    this.numFollowers = numFollowers;
    this.numFollowing = numFollowing;
    this.picture = picture;
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getNumFollowers() {
    return numFollowers;
  }

  public void setNumFollowers(Integer numFollowers) {
    this.numFollowers = numFollowers;
  }

  public Integer getNumFollowing() {
    return numFollowing;
  }

  public void setNumFollowing(Integer numFollowing) {
    this.numFollowing = numFollowing;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
