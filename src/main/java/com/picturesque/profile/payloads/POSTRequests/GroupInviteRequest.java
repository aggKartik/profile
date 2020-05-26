package com.picturesque.profile.payloads.POSTRequests;

import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;

import javax.validation.constraints.NotNull;

public class GroupInviteRequest {

  @NotNull
  private GroupID groupID;
  @NotNull
  private UserID userIdInviter;
  @NotNull
  private UserID userIdInvited;

  public GroupInviteRequest(@NotNull GroupID groupID, @NotNull UserID userIdInviter,
                            @NotNull UserID userIdInvited) {
    this.groupID = groupID;
    this.userIdInviter = userIdInviter;
    this.userIdInvited = userIdInvited;
  }

  public GroupID getGroupID() {
    return groupID;
  }

  public void setGroupID(GroupID groupID) {
    this.groupID = groupID;
  }

  public UserID getUserIdInviter() {
    return userIdInviter;
  }

  public void setUserIdInviter(UserID userIdInviter) {
    this.userIdInviter = userIdInviter;
  }

  public UserID getUserIdInvited() {
    return userIdInvited;
  }

  public void setUserIdInvited(UserID userIdInvited) {
    this.userIdInvited = userIdInvited;
  }

}
