package com.picturesque.profile.service;

import com.picturesque.profile.Utilities.Rules;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.exceptions.CustomApiError;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.PUTRequests.PersonPutRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.PersonMDRepository;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Years;
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
  private PersonMDRepository personMDRepo;

  @Autowired
  public PersonService(PersonRepository personRepo, PersonMDRepository personMDRepo) {
    this.personRepo = personRepo;
    this.personMDRepo = personMDRepo;
  }

  public ResponseEntity<Response<PersonAddResponse>> addPerson(PersonRequest req) {

    // 1. Validate inputs from user first
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

    DateTime dt = new DateTime(req.getDob());
    DateTime today = new LocalDateTime().toDateTime();
    if (Years.yearsBetween(dt, today).getYears() < 13) {
      message = "Too Young to Join Platform";
    }
    // 2. If inputs are invalid, return a 400
    if (!message.equals("")) {
      Response<PersonAddResponse> resp = new Response<>(new PersonAddResponse(message),
              400);
      return new ResponseEntity<>(resp, status);
    }

    /*
    TODO:
     add verification for passwords
     will depend on encryption/token scheme

     Get client IP address, could also change once authentication is implemented

     */

    // 3. Start setting up entry into database
    Date now = new Date();
    long nowVal = now.getTime();
    Person newPerson = getPerson(req, nowVal);
    PersonMD newPersonMD = createPersonMD(req, newPerson);

    personRepo.save(newPerson); // interacting with mongo here
    personMDRepo.save(newPersonMD);

    // 4. Process a successful return message
    message = "User " + req.getUserName() + " added successfully!";
    Response resp = new Response<>(new PersonAddResponse(message), 200);
    status = HttpStatus.OK;

    return new ResponseEntity<Response<PersonAddResponse>>(resp, status);
  }

  public ResponseEntity<Response<PersonAddResponse>> changePerson(PersonPutRequest req) {
    return null;
  }

  private Person getPerson(PersonRequest req, long nowVal) {
    String userId = Integer.toString(Objects.hash(req.getName(), req.getUserName(), nowVal));
    return new Person(req.getName(),
            req.getUserName(),
            new UserID(userId),
            req.getToken(),
            req.getPass(),
            0, "",
            Person.PROFILE_PRIVACY.PUBLIC,
            new ArrayList<>(),
            new ArrayList<>());
  }

  private PersonMD createPersonMD(PersonRequest req, Person person) {
    DateTime today = new LocalDateTime().toDateTime();
    return new PersonMD(person.getUserID(), req.getDob(),
            today.toDate(), today.toDate(), req.getClientIp(), new ArrayList<>());
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
