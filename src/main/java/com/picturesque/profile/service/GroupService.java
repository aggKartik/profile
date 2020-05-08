package com.picturesque.profile.service;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.databaseModels.GroupMD;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.GroupMDRepository;
import com.picturesque.profile.repos.GroupRepository;
import com.picturesque.profile.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private PersonRepository personRepository;
    private GroupMDRepository groupMDRepository;

    @Autowired
    public GroupService(GroupRepository groupRepo, PersonRepository personRepo, GroupMDRepository groupMDRepo)
    { this.groupRepository = groupRepo;
    this.personRepository = personRepo;
    this.groupMDRepository = groupMDRepo;
    }

    public ResponseEntity<Response<GroupAddResponse>> addGroup(GroupRequest req) {

        // validate input from the user first
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        boolean isBadRequest = false;

        Person owner = personRepository.findByUserName(req.getUser());

        // check that the person with the user name exists
        if (owner == null) {
            message = "This user does not exist";
            isBadRequest = true;
        }
        // TODO: check if they're in less than the max number of groups



        // error on bad request
        if (isBadRequest) {
            Response<GroupAddResponse> resp = new Response<>(new GroupAddResponse(message),
                    400);
            return new ResponseEntity<>(resp, status);
        }
        Date now = new Date();
        long nowVal = now.getTime();
        String name = req.getName();

        // try saving the Group and Group Metadata
        try {
            GroupID groupID = new GroupID(Integer.toString(Objects.hash(owner, name, nowVal)));
            ArrayList<UserID> members = new ArrayList<UserID>();
            members.add(owner.getUserID());
            Group group = new Group(groupID , name, members, "");
            groupRepository.save(group);
            groupMDRepository.save(new GroupMD(groupID, now));

            message = "Group " + name + " added successfully!";
            Response resp = new Response<>(new PersonAddResponse(message), 200);
            status = HttpStatus.OK;
            return new ResponseEntity<Response<GroupAddResponse>>(resp, status);
        }
        catch(DataAccessException e) {
            Response<GroupAddResponse> resp = new Response<>(new GroupAddResponse(e.getMessage()),
                    400);
            return new ResponseEntity<>(resp, status);
        }



    }


}
