package com.picturesque.profile.payloads;

import java.util.List;

public class FollowingFollowerResponse {

  private Integer count;
  private List<String> listOfFollow;

  public FollowingFollowerResponse(Integer count, List<String> listOfFollow) {
    this.count = count;
    this.listOfFollow = listOfFollow;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<String> getListOfFollow() {
    return listOfFollow;
  }

  public void setListOfFollow(List<String> listOfFollow) {
    this.listOfFollow = listOfFollow;
  }
}
