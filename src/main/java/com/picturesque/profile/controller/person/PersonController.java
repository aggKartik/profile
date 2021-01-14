package com.picturesque.profile.controller.person;

import com.picturesque.profile.payloads.GETRequests.PersonGetRequest;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;
import com.picturesque.profile.payloads.PUTRequests.PersonPutRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.payloads.PersonGetResponse;
import com.picturesque.profile.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class PersonController {

  private PersonService personService;

  @Autowired
  public void setServices(PersonService personService) {
    this.personService = personService;
  }

  // @GetMapping(path = "/person")
  @RequestMapping(value = "/person", method = RequestMethod.GET)
  public ResponseEntity<Response<PersonGetResponse>> getPersonInfo(
      @Valid @RequestBody PersonGetRequest req) {
    Response<PersonGetResponse> resp = personService.getPersonInfo(req);
    System.out.println(resp);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }

  @PostMapping(path = "/person")
  public ResponseEntity<Response<PersonAddResponse>> addUserToSystem(
      @Valid @RequestBody PersonRequest req) {
    Response<PersonAddResponse> resp = personService.addPerson(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }

  @PutMapping(path = "/person")
  public ResponseEntity<Response<PersonAddResponse>> modifyUserInSystem(
      @Valid @RequestBody PersonPutRequest req) {
    Response<PersonAddResponse> resp = personService.changePerson(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }
}
