package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.GroupMD;
import com.picturesque.profile.helperModels.GroupID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMDRepository extends MongoRepository<GroupMD, String> {

    public GroupMD findByGroupID(GroupID groupID);

}
