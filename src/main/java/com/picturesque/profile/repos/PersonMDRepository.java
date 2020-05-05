package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.PersonMD;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMDRepository extends MongoRepository<PersonMD, String> {

    public PersonMD findById(int id);
    public PersonMD findByUserId(String userId);
    public PersonMD findBylastIP(Long lastIP);
}
