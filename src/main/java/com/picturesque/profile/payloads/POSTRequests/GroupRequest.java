package com.picturesque.profile.payloads.POSTRequests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupRequest {

  @NotNull @NotEmpty public String name; // the name of the group being created

  @NotNull @NotEmpty
  public String userName; // representing the user name of the person making the request

  public GroupRequest(@NotNull @NotEmpty String name, @NotNull @NotEmpty String user) {
    this.name = name;
    this.userName = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUser() {
    return userName;
  }

  public void setUser(String user) {
    this.userName = user;
  }
}
