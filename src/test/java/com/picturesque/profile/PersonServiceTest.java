package com.picturesque.profile;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.exceptions.PersonIllegalArgument;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.PersonMDRepository;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.service.PersonService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @Mock
  private PersonMDRepository personMDRepository;

  @InjectMocks
  private PersonService personService;

  private PersonRequest req;
  private Person addedPerson;
  private PersonMD metaData;

  @BeforeEach
  void setupTest() {
    req = new PersonRequest("John", "JohnDoe", "123456",
            "password", new Date(946688400));

    addedPerson = new Person("John", "JohnDoe", new UserID("-2019924783"),
            "123456", "password", 0, "", Person.PROFILE_PRIVACY.PUBLIC,
            new ArrayList<>(), new ArrayList<>());

    DateTime today = new LocalDateTime().toDateTime();
    metaData = new PersonMD(addedPerson.getUserID(), req.getDob(), today.toDate(),
            today.toDate(), req.getClientIp(), "", new ArrayList<>());
  }


  @Test
  void testAddUser() {

    given(personRepository.findByUserName(req.userName)).willReturn(null);
    given(personRepository.save(any(Person.class))).willAnswer(invocation -> addedPerson);
    given(personMDRepository.save(any(PersonMD.class))).willAnswer(invocation -> metaData);

    Response<PersonAddResponse> resp = personService.addPerson(req);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(resp.getResponse().getMessage(),
            equalTo("User JohnDoe added successfully!"));

  }

  @Test
  void tooYoungToJoinTest() {

    given(personRepository.findByUserName(req.userName)).willReturn(null);

    try {
      personService.addPerson(req);
    } catch (PersonIllegalArgument e) {
      assertThat(e.getMessage(), equalTo("Too Young to Join Platform"));
    }
  }

  @Test
  void allRestrictionsTest() {

    req.setUserName("---JohnDoe---");
    given(personRepository.findByUserName(req.userName)).willReturn(null);

    try {
      personService.addPerson(req);
    } catch (PersonIllegalArgument e) {
      assertThat(e.getMessage(), equalTo("Illegal username specified"));
    }
    try {
      req.setUserName("JohnDoe");
      req.setName("                                                                          " +
              "                                                                              ");
      personService.addPerson(req);
    } catch (PersonIllegalArgument e) {
      assertThat(e.getMessage(), equalTo("Invalid name specified"));
    }

    given(personRepository.findByUserName(req.userName)).willReturn(addedPerson);

    try {
      personService.addPerson(req);
    } catch (PersonIllegalArgument e) {
      assertThat(e.getMessage(), equalTo("User with this name already exists!"));
    }

  }

}

