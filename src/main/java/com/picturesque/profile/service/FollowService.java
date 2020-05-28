package com.picturesque.profile.service;

import com.picturesque.profile.databaseModels.Follow;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.exceptions.FollowIllegalArgument;
import com.picturesque.profile.payloads.FollowAddResponse;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.FollowRequest;
import com.picturesque.profile.repos.FollowRepository;
import com.picturesque.profile.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Response<FollowAddResponse> addFollow(FollowRequest req) {
        String message = "";

        // can't follow yourself
        if (req.getRequester().equals(req.getRequested())) {
            throw new FollowIllegalArgument("Can't follow yourself");
        }

        Person requested = personRepository.findByUserName(req.getRequested());
        Person requester = personRepository.findByUserName(req.getRequester());

        // check the database if requester  exists
        if(requested == null) {
            throw new FollowIllegalArgument("User you trying to follow doesn't exist");
        }

        // if you don't exist
        if (requester == null) {
            throw new FollowIllegalArgument("You don't exist");
        }

        // check if they're already in the other person's invite list????? IS this needed?

        // check if person already follows the person
        if (followRepository.findByFollowingAndUserID(requester.getUserID(), requested.getUserID()) != null) {
            throw new FollowIllegalArgument("You already follow this person");
        }

        // if they're private add to their invite list
        if (requested.getProfileType() == Person.PROFILE_PRIVACY.PRIVATE) {
            Person dup = personRepository.findByUserID(requester.getUserID());
            // person is already in the invite list
            if(dup.getFollowerInvite().contains(requested.getUserID())) {
                throw new FollowIllegalArgument("You already requested to follow this person");
            }
            requested.addFollowerInvite(requester.getUserID());
            personRepository.save(requested);
        }
        // if it is a public profile
        else if (requested.getProfileType() == Person.PROFILE_PRIVACY.PUBLIC) {
            Date now = new Date();
            Follow follow = new Follow(requested.getUserID(), requester.getUserID(), now);
            followRepository.save(follow);
        }
        // else catch any other errors can't find
        else {
            throw new FollowIllegalArgument("Some kind of error not handled");
        }

        message = "You followed " + requested.getName() + " successfully!";
        return new Response<>(new FollowAddResponse(message), HttpStatus.OK);
    }



    public Response<FollowAddResponse> removeFollow(FollowRequest req) {
        String message = "";
        Person requested = personRepository.findByUserName(req.getRequested());
        Person requester = personRepository.findByUserName(req.getRequester());

        // can't follow yourself
        if (requested.equals(requester)) {
            throw new FollowIllegalArgument("Can't follow yourself");
        }

        // check the database if requester  exists
        if(requested == null) {
            throw new FollowIllegalArgument("User you trying to unfollow doesn't exist");
        }

        // if you don't exist
        if (requester == null) {
            throw new FollowIllegalArgument("You don't exist");
        }

        // if they're on the invite list remove them from the list
        if (requested.getFollowerInvite().contains(requester.getUserID())) {
            requested.removeFollowerInvite(requester.getUserID());
            message = "You were removed  " + requested.getName() + " successfully!";
            return new Response<>(new FollowAddResponse(message), HttpStatus.OK);
        }

        Follow follow = followRepository.findByFollowingAndUserID(requester.getUserID(), requested.getUserID());

        // check that you actually do follow them right now
        if (follow == null) {
            throw new FollowIllegalArgument("You don't follow them");
        }

        // they're not on the invite list just get rid of the request just get rid of the follower reationiship
        followRepository.delete(follow);
        message = "You unfollowed " + requested.getName() + " successfully!";
        return new Response<>(new FollowAddResponse(message), HttpStatus.OK);


    }
}
