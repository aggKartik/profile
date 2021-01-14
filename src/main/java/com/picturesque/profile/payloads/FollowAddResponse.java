package com.picturesque.profile.payloads;

public class FollowAddResponse {

  private String message;

  public FollowAddResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
