package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Follow;
import com.picturesque.profile.helperModels.UserID;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowRepository extends MongoRepository<Follow, String> {

public List<Follow> findByFollower(UserID follower);

public List<Follow> findByFollowing(UserID following);

}
