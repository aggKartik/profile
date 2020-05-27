package com.picturesque.profile;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.picturesque.profile.controller.person.PersonController;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.service.PersonService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
//
//@WebMvcTest(PersonController.class)
//public class PersonServiceTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private PersonService service;
//
//  @Autowired
//  ObjectMapper mapper;
//
//  @Test
//  public void testAddPersonInService() throws Exception {
//
//    PersonRequest req = new PersonRequest("John", "JohnDoe", "123456",
//            "password", new Date());
//
//    Response<PersonAddResponse> resp = new Response<>(new PersonAddResponse("Added user JohnDoe"),
//            HttpStatus.OK);
//
//    when(service.addPerson(req)).thenReturn(resp);
//    mapper = new ObjectMapper();
//
//    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/person")
//            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
//            .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(req));
//
//    mockMvc.perform(builder)
//            .andExpect(status().isCreated()).andExpect(status().isOk())
//            .andExpect(content().string("Added user JohnDoe"));
//
////    mockMvc.perform(post("/v1/person")
////            .contentType(MediaType.APPLICATION_JSON_VALUE)
////            .accept(MediaType.APPLICATION_JSON)
////            .content(this.mapper.writeValueAsString(req)))
//
//
//  }

//}
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;


  @Test
  void testAddUser() {

    PersonRequest req = new PersonRequest("John", "JohnDoe", "123456",
            "password", new Date(946688400));

    Person addedPerson = new Person("John", "JohnDoe", new UserID("-2019924783"),
            "123456", "password", 0, "", Person.PROFILE_PRIVACY.PUBLIC,
            new ArrayList<>(), new ArrayList<>());
    
    given(personRepository.findByUserName(req.userName)).willReturn(null);
    given(personRepository.save(addedPerson)).willAnswer(invocation-> invocation.getArgument(0));

    Response<PersonAddResponse> resp = personService.addPerson(req);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));



  }


}