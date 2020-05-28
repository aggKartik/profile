package com.picturesque.profile.service;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.databaseModels.GroupMD;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.databaseModels.PersonMD;
import com.picturesque.profile.exceptions.GroupIllegalArgument;
import com.picturesque.profile.exceptions.PersonIllegalArgument;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.payloads.PUTRequests.GroupPutRequest;
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
import java.util.List;
import java.util.Objects;

@Service
public class GroupService {
  private GroupRepository groupRepository;
  private PersonRepository personRepository;
  private GroupMDRepository groupMDRepository;

  @Autowired
  public GroupService(GroupRepository groupRepo, PersonRepository personRepo, GroupMDRepository groupMDRepo) {
    this.groupRepository = groupRepo;
    this.personRepository = personRepo;
    this.groupMDRepository = groupMDRepo;
  }

  public Response<GroupAddResponse> addGroup(GroupRequest req) {

    Person owner = personRepository.findByUserName(req.getUser());

    // check that the person with the user name exists
    if (owner == null) {
      throw new GroupIllegalArgument("This user does not exist");
    }
    // TODO: check if they're in less than the max number of groups

    Date now = new Date();
    long nowVal = now.getTime();
    String name = req.getName();

    // try saving the Group and Group Metadata
    GroupID groupID = new GroupID(Integer.toString(Objects.hash(owner, name, nowVal)));
    List<UserID> members = new ArrayList<>();
    members.add(owner.getUserID());
    Group group = new Group(groupID, name, members, "");

    try {
      groupRepository.save(group);
      groupMDRepository.save(new GroupMD(groupID, now));
    } catch (DataAccessException e) {
      Response<GroupAddResponse> resp = new Response<>(new GroupAddResponse(e.getMessage()),
              HttpStatus.BAD_REQUEST);
      return resp;
    }

    String message = "Group " + name + " added successfully!";
    return new Response<GroupAddResponse>(new GroupAddResponse(message), HttpStatus.OK);
  }

  public Response<GroupAddResponse> modifyGroup(GroupPutRequest req) {

    // 1. Verify user inputs
    // TODO add some kind of validation here such that only the actual user can modify their own details

    Group modifiedGroup = groupRepository.findByGroupID(req.getGroupID());
    if (modifiedGroup == null) {
      throw new GroupIllegalArgument("Group does not exist in the database");
    }
    GroupMD modifiedGroupMD = groupMDRepository.findByGroupID(modifiedGroup.getGroupID());

    // 2. Modify what can be modified
    List<String> messages = new ArrayList<>();
    String bio = req.getBio();
    String groupName = req.getGroupName();
    String picture = req.getPicture();

    if (groupName != null) {
        modifiedGroup.setName(groupName);
        messages.add("Group name modified successfully");
    }

    if (bio != null) {
      if (bio.length() > 200) {
        messages.add("Bio Length is too long!");
      } else {
        modifiedGroup.setBio(bio);
        messages.add("Bio added successfully!");
      }
    }

    if (picture != null) {
      // TODO probably check here
        modifiedGroup.setPic(picture);
        messages.add("Picture added successfully!");
    }

    if (messages.size() == 0) {
      messages.add("Nothing was modified!");
    }

    groupRepository.save(modifiedGroup);
    groupMDRepository.save(modifiedGroupMD);

    return new Response<>(new GroupAddResponse(messages.toString()),
            HttpStatus.OK);
  }
}
