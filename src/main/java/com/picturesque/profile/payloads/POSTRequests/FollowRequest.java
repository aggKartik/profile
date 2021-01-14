package com.picturesque.profile.payloads.POSTRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Component
@Scope("request")
public class FollowRequest {
  @NotNull public String requester;
  @NotNull public String requested;

  @Autowired HttpServletRequest request;

  public FollowRequest(@NotNull String requested, @NotNull String requester) {
    this.requested = requested;
    this.requester = requester;
  }

  public String getRequester() {
    return requester;
  }

  public void setRequester(String requester) {
    this.requester = requester;
  }

  public String getRequested() {
    return requested;
  }

  public void setRequested(String requested) {
    this.requested = requested;
  }

  @Override
  public String toString() {
    return "FollowRequest{"
        + "requester='"
        + requester
        + '\''
        + ", requested='"
        + requested
        + '\''
        + '}';
  }
}
