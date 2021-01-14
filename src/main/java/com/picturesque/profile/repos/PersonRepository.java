package com.picturesque.profile.repos;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.UserID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

  // public Person findById(int id);
  Person findByUserName(String userName);

  Person findByUserID(UserID userId);

  Person findByToken(String token);
}
