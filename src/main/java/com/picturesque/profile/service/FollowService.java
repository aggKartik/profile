package com.picturesque.profile.service;

import com.picturesque.profile.databaseModels.Follow;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.payloads.FollowAddResponse;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.FollowRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.repos.FollowRepository;
import com.picturesque.profile.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FollowService {
    private FollowRepository followRepository;
    private PersonRepository personRepository;

    @Autowired
    public FollowService(FollowRepository followRepository, PersonRepository personRepository) {
        this.followRepository = followRepository;
        this.personRepository = personRepository;
    }

    public ResponseEntity<Response<FollowAddResponse>> addFollow(FollowRequest req) {
        String message = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        boolean badRequest = false;

        // can't follow yourself
        if (req.requester == req.requested) {
            message = "Can't follow yourself";
            badRequest = true;
        }

        Person requested = personRepository.findByUserName(req.requested);
        Person requester = personRepository.findByUserName(req.requester);

        // check if they're already in the other person's invite list????? IS this needed?

        // check if person already follows the person
        if (followRepository.findByFollowingAndUserID(requester.getUserID(), requested.getUserID()) != null) {
            message = "You already follow this person";
            badRequest = true;
        }

        if (badRequest) {
            Response<FollowAddResponse> resp = new Response<>(new FollowAddResponse(message),
                    400);
            return new ResponseEntity<>(resp, status);
        }

        // if they're private add to their invite list
        if (requested.getProfileType() == Person.PROFILE_PRIVACY.PRIVATE) {
            requested.addFollowerInvite(requester.getUserID());
            personRepository.save(requested);
            message = "User " + requested.getName() + " requested successfully!";
            Response resp = new Response<>(new PersonAddResponse(message), 200);
            status = HttpStatus.OK;
            return new ResponseEntity<Response<FollowAddResponse>>(resp, status);
        }

        // if it is a public profile
        if (requested.getProfileType() == Person.PROFILE_PRIVACY.PUBLIC) {
            Date now = new Date();
            Follow follow = new Follow(requested.getUserID(), requester.getUserID(), now);
            try {
                followRepository.save(follow);
                message = "You followed " + requested.getName() + " successfully!";
                Response resp = new Response<>(new PersonAddResponse(message), 200);
                status = HttpStatus.OK;
                return new ResponseEntity<Response<FollowAddResponse>>(resp, status);
            } catch (DataAccessException e) {
                Response<FollowAddResponse> resp = new Response<>(new FollowAddResponse(e.getMessage()),
                        500);
                return new ResponseEntity<>(resp, status);
            }
        }

        // else catch any other errors can't find
        else {
            Response<FollowAddResponse> resp = new Response<>(new FollowAddResponse("Some kind of error not handled"),
                    500);
            return new ResponseEntity<>(resp, status);
        }
    }
}
