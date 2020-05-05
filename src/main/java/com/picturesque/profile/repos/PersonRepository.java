package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findById(int id);
    public Person findByUserName(String userName);


}
