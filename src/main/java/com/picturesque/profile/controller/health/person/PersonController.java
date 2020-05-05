package com.picturesque.profile.controller.health.person;

import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping(path = "/profile")
    public ResponseEntity<Response<PersonAddResponse>> addUserToSystem(@Valid @RequestBody PersonRequest req) {
        return personService.addPerson(req);
    }

//    @GetMapping(path = "/profile")
//    public ResponseEntity searchByName(@Valid @RequestBody PersonRequest req) {
//        return personService.addPerson(req);
//    }

}
