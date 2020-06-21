package com.picturesque.profile.payloads;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.GroupID;

import java.util.List;

public class PersonResponseFollowerOrPublicFields extends PersonGetResponse {

  private List<GroupID> listOfGroups;
  private Integer points;
  private Person.PROFILE_PRIVACY privacy;

  public PersonResponseFollowerOrPublicFields(String userName, Integer numFollowers, Integer numFollowing,
                                              String picture, String name, List<GroupID> listOfGroups,
                                              String bio, Integer points, Person.PROFILE_PRIVACY privacy) {
    super(userName, numFollowers, numFollowing, picture, name, bio);
    this.listOfGroups = listOfGroups;
    this.points = points;
    this.privacy = privacy;
  }

  @Override
  public String toString() {
    return "PersonResponseFollowerOrPublicFields{" +
            "listOfGroups=" + listOfGroups +
            ", points=" + points +
            ", privacy=" + privacy +
            ", userName='" + userName + '\'' +
            ", numFollowers=" + numFollowers +
            ", numFollowing=" + numFollowing +
            ", picture='" + picture + '\'' +
            ", name='" + name + '\'' +
            ", bio='" + bio + '\'' +
            '}';
  }
}
