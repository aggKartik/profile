package com.picturesque.profile.payloads;

public class GroupAddResponse {
  private String message;

  public GroupAddResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
