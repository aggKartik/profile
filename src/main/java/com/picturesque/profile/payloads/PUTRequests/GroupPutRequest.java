package com.picturesque.profile.payloads.PUTRequests;

import com.picturesque.profile.helperModels.GroupID;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupPutRequest {

  @NotNull
  private GroupID groupID ;
  private String groupName;
  private String bio;
  private String picture;


  public GroupPutRequest(@NotNull GroupID groupID, String groupName, String bio, String picture) {
    this.groupID = groupID;
    this.groupName = groupName;
    this.bio = bio;
    this.picture = picture;
  }

  public GroupID getGroupID() {
    return groupID;
  }

  public void setGroupID(GroupID groupID) {
    this.groupID = groupID;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
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

  @Override
  public String toString() {
    return "GroupPutRequest{" +
            "groupID=" + groupID +
            ", groupName='" + groupName + '\'' +
            ", bio='" + bio + '\'' +
            ", picture='" + picture + '\'' +
            '}';
  }
}
