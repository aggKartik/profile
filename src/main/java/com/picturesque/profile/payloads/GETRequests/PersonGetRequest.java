package com.picturesque.profile.payloads.GETRequests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonGetRequest {

  @NotNull @NotEmpty private String requester;
  @NotNull @NotEmpty private String requested;

  public PersonGetRequest(
      @NotNull @NotEmpty String requester, @NotNull @NotEmpty String requested) {
    this.requester = requester;
    this.requested = requested;
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
}
