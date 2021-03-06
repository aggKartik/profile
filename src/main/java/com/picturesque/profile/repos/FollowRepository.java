package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Follow;
import com.picturesque.profile.helperModels.UserID;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowRepository extends MongoRepository<Follow, String> {

  List<Follow> findByUserID(UserID userID);

  List<Follow> findByFollowing(UserID following);

  Follow findByFollowingAndUserID(UserID following, UserID userID);

  Integer countFollowByUserID(UserID userID); // Following Count

  Integer countByFollowing(UserID following); // Follower Count
}
