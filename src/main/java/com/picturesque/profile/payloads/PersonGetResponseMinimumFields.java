package com.picturesque.profile.payloads;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.payloads.PersonGetResponse;

// At minimum: user_name, number of followers and following, picture, name

/**
 * This class represents the minimum number of fields for a get response.
 */
public class PersonGetResponseMinimumFields extends PersonGetResponse {

  private Person.PROFILE_PRIVACY privacy;

  public PersonGetResponseMinimumFields(String userName, Integer numFollowers, Integer numFollowing,
                                        String picture, String name, Person.PROFILE_PRIVACY privacy,
                                        String bio) {
    super(userName, numFollowers, numFollowing, picture, name, bio);
    this.privacy = privacy;
  }

  @Override
  public String toString() {
    return "PersonGetResponseMinimumFields{" +
            "privacy=" + privacy +
            ", userName='" + userName + '\'' +
            ", numFollowers=" + numFollowers +
            ", numFollowing=" + numFollowing +
            ", picture='" + picture + '\'' +
            ", name='" + name + '\'' +
            ", bio='" + bio + '\'' +
            '}';
  }
}
