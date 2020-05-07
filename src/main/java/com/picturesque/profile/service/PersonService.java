package com.picturesque.profile.service;

import com.picturesque.profile.Utilities.Rules;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.exceptions.CustomApiError;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Service
public class PersonService {

  private PersonRepository personRepo;

  @Autowired
  public PersonService(PersonRepository personRepo) {
    this.personRepo = personRepo;
  }

  public ResponseEntity<Response<PersonAddResponse>> addPerson(PersonRequest req) {

    // Validate inputs from user first

    // 1. Check to see if user name already exists in DB
    String message = "";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if (personRepo.findByUserName(req.userName) != null) {
      message = "User with this name already exists!";
    }
    if (!userNameValid(req.getUserName())) {
      message = "Illegal username specified!";
    }
    if (!nameValid(req.getName())) {
      message = "Invalid name specified";
    }

    if (!message.equals("")) {
      Response<PersonAddResponse> resp = new Response<>(new PersonAddResponse(message),
              400);
      return new ResponseEntity<>(resp, status);
    }

    /*
    TODO:
     add verification for passwords
     will depend on encryption/token scheme
     */

    Date now = new Date();
    long nowVal = now.getTime();
    String userId = Integer.toString(Objects.hash(req.getName(), req.getUserName(), nowVal));
    Person newPerson = new Person(req.getName(),
            req.getUserName(),
            new UserID(userId),
            req.getToken(),
            req.getPass(),
            0, "",
            Person.PROFILE_PRIVACY.PUBLIC,
            new ArrayList<>(),
            new ArrayList<>());

    personRepo.save(newPerson); // interacting with mongo here
    message = "User " + req.getUserName() + " added successfully!";
    Response resp = new Response<>(new PersonAddResponse(message), 200);
    status = HttpStatus.OK;
    return new ResponseEntity<Response<PersonAddResponse>>(resp, status);
  }

  private boolean nameValid(String name) {
    if (name.length() > 50) {
      return false;
    }

    String[] names = name.split("\\s");

    for (String n : names) {
      if (!n.matches("[a-zA-Z]+")) {
        return false;
      }
    }
    return true;
  }

  private boolean userNameValid(String userName) {
    return Rules.isValidUserName(userName);
  }
}
