package com.picturesque.profile.service;

import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.repos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private PersonRepository personRepository;

    @Autowired
    public GroupService(GroupRepository groupRepo) { this.groupRepository = groupRepo; }

    @Autowired
    public PersonService(PersonRepository personRepo){ this.personRepository = personRepo; }

    public ResponseEntity<Response<GroupAddResponse>> addGroup(GroupRequest req) {
        // validate input from the user first
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        boolean isBadRequest = false;

        Person owner = personRepo.findByUserName(req.userName);

        // check that the person with the user name exists
        if (owner == null) {
            message = "This user does not exist";
            isBadRequest = true
        }

        // TODO: check if they're in less than the max number of groups

        if (groupRepository.findByName(req.name) != null) {
            message = "This name is already taken";
            isBadRequest = true;
        }









    }


}
