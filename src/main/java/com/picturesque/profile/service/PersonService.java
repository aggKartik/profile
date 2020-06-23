package com.picturesque.profile.service;

import com.picturesque.profile.Utilities.Rules;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.exceptions.PersonIllegalArgument;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GETRequests.PersonGetRequest;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.PUTRequests.PersonPutRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.payloads.PersonGetResponse;
import com.picturesque.profile.repos.FollowRepository;
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
  private FollowRepository followRepo;

  @Autowired
  public PersonService(PersonRepository personRepo, PersonMDRepository personMDRepo,
                       FollowRepository followRepo) {
    this.personRepo = personRepo;
    this.personMDRepo = personMDRepo;
    this.followRepo = followRepo;
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

  public Response<PersonGetResponse> getPersonInfo(PersonGetRequest req) {

    // This stuff might need to be changed depending on how session is implemented.

    Person requester = personRepo.findByUserName(req.getRequester());
    Person requested = personRepo.findByUserName(req.getRequested());

    if (requester == null || requested == null) {
      throw new PersonIllegalArgument("Either requester or requested person doesn't exist");
    }

    PersonMD requestedMD = personMDRepo.findByUserId(requested.getUserID());
    Integer followerCount = followRepo.countByFollowing(requested.getUserID());
    Integer followingCount = followRepo.countFollowByUserID(requested.getUserID());

    PersonGetResponse.Builder build = new PersonGetResponse.Builder(requested.getUserName(),
            followerCount, followingCount, requested.getPic(),
            requested.getName(), requestedMD.getBio());
    PersonGetResponse response;

    // 1. Person themselves, return all data
    if (requester.getUserName().equals(requested.getUserName())) {
      response = build
              .withPoints(requested.getPoints())
              .withPrivacy(requested.getProfileType())
              .withFollowerInvite(requested.getFollowerInvite())
              .withGroupInvite(requested.getGroupInvite())
              .withLastIP(requestedMD.getLastIP())
              .withLastLogin(requestedMD.getLastLogin())
              .withListOfGroups(requestedMD.getGroupIds())
              .withDateJoined(requestedMD.getDateJoined())
              .build();
    }
    // 2. Follower looking at this profile - most info would be displayed, somethings like ip don't
    //    need to be returned or its a public profile
    else if (followRepo.findByFollowingAndUserID(requested.getUserID(), requester.getUserID()) != null
            || requested.getProfileType() == Person.PROFILE_PRIVACY.PUBLIC) {
      response = build
              .withPoints(requested.getPoints())
              .withListOfGroups(requestedMD.getGroupIds())
              .withPrivacy(requested.getProfileType())
              .build();
    }
    // 3. Person who doesn't follow this person - minimum info
    else {
      response = build
              .withPrivacy(requested.getProfileType())
              .withPoints(requested.getPoints())
              .build();
    }

    return new Response<>(response, HttpStatus.OK);
  }
}
