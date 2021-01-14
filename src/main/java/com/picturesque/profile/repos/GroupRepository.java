package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.helperModels.GroupID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
  Group findByName(String name);

  Group findByGroupID(GroupID groupID);
}
