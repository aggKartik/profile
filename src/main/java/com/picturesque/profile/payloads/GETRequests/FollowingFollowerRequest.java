package com.picturesque.profile.payloads.GETRequests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FollowingFollowerRequest {

  @NotNull @NotEmpty
  private String requester;
  @NotNull @NotEmpty
  private String requested;
  private Integer pageNumber = 1;
  private Integer entriesPerPage = 10;

//  public FollowingFollowerRequest(@NotNull @NotEmpty String requester, @NotNull @NotEmpty
//          String requested, Integer pageNumber, Integer entriesPerPage) {
//    this.requester = requester;
//    this.requested = requested;
//    this.pageNumber = pageNumber;
//    this.entriesPerPage = entriesPerPage;
//  }

  private FollowingFollowerRequest() {}

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

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getEntriesPerPage() {
    return entriesPerPage;
  }

  public void setEntriesPerPage(Integer entriesPerPage) {
    this.entriesPerPage = entriesPerPage;
  }
}
