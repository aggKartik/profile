package com.picturesque.profile.service;

import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.payloads.POSTRequests.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class PersonService {

    private PersonRepository personRepo;

    @Autowired
    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    private Person instantiatePerson(PersonRequest req) {
        return new Person(1, req.getName(), req.getUserName(), "123", req.getToken(), passwordEncoder().encode(req.getPass()), "", 0);
    }

    public ResponseEntity<Response<PersonAddResponse>> addPerson(PersonRequest req) {

        Person newPerson = instantiatePerson(req);

        personRepo.save(newPerson); // interacting with mongo here

        Response resp = new Response<PersonAddResponse>(new PersonAddResponse("user kartik.agg successfully added"), 200);
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<Response<PersonAddResponse>>(resp, status);
    }
}
