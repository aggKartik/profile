package com.picturesque.profile.service;

import com.picturesque.profile.Utilities.Rules;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.exceptions.PersonIllegalArgument;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

  private PersonRepository personRepo;
  private PersonMDRepository personMDRepo;

  @Autowired
  public PersonService(PersonRepository personRepo, PersonMDRepository personMDRepo) {
    this.personRepo = personRepo;
    this.personMDRepo = personMDRepo;
  }

  public Response<PersonAddResponse> addPerson(PersonRequest req) {

    // 1. Validate inputs from user first
    String message = "";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if (personRepo.findByUserName(req.userName) != null) {
      throw new PersonIllegalArgument("User with this name already exists!");
    }
    if (!userNameValid(req.getUserName())) {
      throw new PersonIllegalArgument("Illegal username specified!");
    }
    if (!nameValid(req.getName())) {
      throw new PersonIllegalArgument("Invalid name specified");
    }
    if (!isOldEnough(req.getDob())) {
      throw new PersonIllegalArgument("Too Young to Join Platform");
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
    Response<PersonAddResponse> resp = new Response<>(new PersonAddResponse(message), HttpStatus.OK);
    return resp;
  }


  public Response<PersonAddResponse> changePerson(PersonPutRequest req) {

    // 1. Verify user inputs
    // TODO add some kind of validation here such that only the actual user can modify their own details

    Person modifiedPerson = personRepo.findByUserName(req.getUserName());
    if (modifiedPerson == null) {
      throw new PersonIllegalArgument("Person does not exist in the database");
    }
    PersonMD modifiedPersonMD = personMDRepo.findByUserId(modifiedPerson.getUserID());

    // 2. Modify what can be modified
    List<String> messages = new ArrayList<>();
    Date dob = req.getDob();
    String bio = req.getBio();

    if (dob != null) {
      if (!isOldEnough(dob)) {
        messages.add("DOB specified is not valid!");
      } else {
        modifiedPersonMD.setDob(dob);
        messages.add("DOB modified successfully");
      }
    }

    if (bio != null) {
      if (bio.length() > 200) {
        messages.add("Bio Length is too long!");
      } else {
        modifiedPersonMD.setBio(bio);
        messages.add("Bio added successfully!");
      }
    }

    if (messages.size() == 0) {
      messages.add("Nothing was modified!");
    }

    personRepo.save(modifiedPerson);
    personMDRepo.save(modifiedPersonMD);

    return new Response<>(new PersonAddResponse(messages.toString()),
            HttpStatus.OK);
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
            today.toDate(), today.toDate(), req.getClientIp(), "", new ArrayList<>());
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

  private boolean isOldEnough(Date date) {
    DateTime dt = new DateTime(date);
    DateTime today = new LocalDateTime().toDateTime();
    return Years.yearsBetween(dt, today).getYears() >= 13;
  }
}
