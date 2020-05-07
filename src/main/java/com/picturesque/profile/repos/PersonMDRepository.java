package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.helperModels.UserID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMDRepository extends MongoRepository<PersonMD, String> {

    public PersonMD findByUserId(UserID userId);
    public PersonMD findBylastIP(Long lastIP);
}
