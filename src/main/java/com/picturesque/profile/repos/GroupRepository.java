package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {

    public Group findById(int id);
    public Group findByName(String name);

}
