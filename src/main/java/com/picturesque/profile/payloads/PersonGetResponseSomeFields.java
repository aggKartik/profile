package com.picturesque.profile.payloads;

import com.picturesque.profile.payloads.PersonGetResponse;

// At minimum: user_name, number of followers and following, picture, name

/**
 * This class represents the minimum number of fields for a get response.
 */
public class PersonGetResponseSomeFields extends PersonGetResponse {

  public PersonGetResponseSomeFields(String userName, Integer numFollowers, Integer numFollowing,
                                     String picture, String name) {
    super(userName, numFollowers, numFollowing, picture, name);
  }

  @Override
  public String toString() {
    return "PersonGetResponseSomeFields{" +
            "userName='" + userName + '\'' +
            ", numFollowers=" + numFollowers +
            ", numFollowing=" + numFollowing +
            ", picture='" + picture + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
