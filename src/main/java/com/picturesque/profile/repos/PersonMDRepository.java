package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.helperModels.UserID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMDRepository extends MongoRepository<PersonMD, String> {

  PersonMD findByUserId(UserID userId);

  PersonMD findBylastIP(Long lastIP);
}
