package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.GroupMD;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMDRepository extends MongoRepository<GroupMD, String> {

    public GroupMD findById(int id);
    public GroupMD findByGroupID(String groupID);

}
