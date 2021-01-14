package com.picturesque.profile.payloads.PUTRequests;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonPutRequest {

  @NotNull @NotEmpty private String userName;
  private String bio;
  private String picture;
  private Date dob;

  public PersonPutRequest(String userName, String bio, String picture, Date dob) {
    this.userName = userName;
    this.bio = bio;
    this.picture = picture;
    this.dob = dob;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  @Override
  public String toString() {
    return "PersonPutRequest{"
        + "userName='"
        + userName
        + '\''
        + ", bio='"
        + bio
        + '\''
        + ", picture='"
        + picture
        + '\''
        + ", dob="
        + dob
        + '}';
  }
}
