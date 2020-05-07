package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.helperModels.GroupID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    public Group findByName(String name);
    public Group findByGroupID(GroupID groupID);
}
