package com.picturesque.profile;

import com.picturesque.profile.databaseModels.Group;
import com.picturesque.profile.databaseModels.GroupMD;
import com.picturesque.profile.databaseModels.Person;
import com.picturesque.profile.exceptions.GroupIllegalArgument;
import com.picturesque.profile.helperModels.GroupID;
import com.picturesque.profile.helperModels.UserID;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.payloads.PUTRequests.GroupPutRequest;
import com.picturesque.profile.repos.GroupMDRepository;
import com.picturesque.profile.repos.GroupRepository;
import com.picturesque.profile.repos.PersonRepository;
import com.picturesque.profile.service.GroupService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTests {

  @Mock private GroupRepository groupRepository;

  @Mock private PersonRepository personRepository;

  @Mock private GroupMDRepository groupMDRepository;

  @InjectMocks private GroupService groupService;

  @Test
  void addGroupTest() {

    Person person =
        new Person(
            "John",
            "JohnDoe",
            new UserID("-2019924783"),
            "123456",
            "password",
            0,
            "",
            Person.PROFILE_PRIVACY.PUBLIC,
            new ArrayList<>(),
            new ArrayList<>());
    GroupRequest req = new GroupRequest("Test Group", "testuser");
    given(personRepository.findByUserName(req.getUser())).willReturn(person);

    List<UserID> members = new ArrayList<>();
    members.add(new UserID("-2019924783"));
    Group group = new Group(new GroupID("123"), req.getName(), members, "");
    GroupMD groupMD = new GroupMD(new GroupID("123"), new Date());
    given(groupRepository.save(any(Group.class))).willAnswer(invocation -> group);
    given(groupMDRepository.save(any(GroupMD.class))).willAnswer(invocation -> groupMD);

    Response<GroupAddResponse> resp = groupService.addGroup(req);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(resp.getResponse().getMessage(), equalTo("Group Test Group added successfully!"));
  }

  @Test
  void addPersonNotExistGroup() {
    GroupRequest req = new GroupRequest("Test Group", "testuser");
    given(personRepository.findByUserName(req.getUser())).willReturn(null);

    assertThrows(
        GroupIllegalArgument.class, () -> groupService.addGroup(req), "This user does not exist");
  }

  @Test
  void changePersonTest() {

    GroupPutRequest putRequest =
        new GroupPutRequest(new GroupID("1234"), "Life's Good", "This is a bio.", "xyz.jpg");
    List<UserID> members = new ArrayList<>();
    members.add(new UserID("-2019924783"));
    Group group = new Group(new GroupID("123"), putRequest.getGroupName(), members, "");
    GroupMD groupMD = new GroupMD(new GroupID("123"), new Date());

    given(groupRepository.findByGroupID(putRequest.getGroupID())).willReturn(group);
    given(groupMDRepository.findByGroupID(group.getGroupID())).willReturn(groupMD);

    Response<GroupAddResponse> resp = groupService.modifyGroup(putRequest);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(
        resp.getResponse().getMessage(),
        equalTo(
            "[Group name modified successfully, "
                + "Bio added successfully!, Picture added successfully!]"));
  }

  @Test
  void changeJustBioTest() {

    GroupPutRequest putRequest =
        new GroupPutRequest(new GroupID("1234"), null, "This is a bio.", null);
    List<UserID> members = new ArrayList<>();
    members.add(new UserID("-2019924783"));
    Group group = new Group(new GroupID("123"), putRequest.getGroupName(), members, "");
    GroupMD groupMD = new GroupMD(new GroupID("123"), new Date());

    given(groupRepository.findByGroupID(putRequest.getGroupID())).willReturn(group);
    given(groupMDRepository.findByGroupID(group.getGroupID())).willReturn(groupMD);

    Response<GroupAddResponse> resp = groupService.modifyGroup(putRequest);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(resp.getResponse().getMessage(), equalTo("[Bio added successfully!]"));
  }

  @Test
  void changeJustPictureTest() {

    GroupPutRequest putRequest = new GroupPutRequest(new GroupID("1234"), null, null, "pic.jpg");
    List<UserID> members = new ArrayList<>();
    members.add(new UserID("-2019924783"));
    Group group = new Group(new GroupID("123"), putRequest.getGroupName(), members, "");
    GroupMD groupMD = new GroupMD(new GroupID("123"), new Date());

    given(groupRepository.findByGroupID(putRequest.getGroupID())).willReturn(group);
    given(groupMDRepository.findByGroupID(group.getGroupID())).willReturn(groupMD);

    Response<GroupAddResponse> resp = groupService.modifyGroup(putRequest);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(resp.getResponse().getMessage(), equalTo("[Picture added successfully!]"));
  }

  @Test
  void changeJustNameTest() {

    GroupPutRequest putRequest =
        new GroupPutRequest(new GroupID("1234"), "Test Group 2", null, null);
    List<UserID> members = new ArrayList<>();
    members.add(new UserID("-2019924783"));
    Group group = new Group(new GroupID("123"), putRequest.getGroupName(), members, "");
    GroupMD groupMD = new GroupMD(new GroupID("123"), new Date());

    given(groupRepository.findByGroupID(putRequest.getGroupID())).willReturn(group);
    given(groupMDRepository.findByGroupID(group.getGroupID())).willReturn(groupMD);

    Response<GroupAddResponse> resp = groupService.modifyGroup(putRequest);

    assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    assertThat(resp.getResponse().getMessage(), equalTo("[Group name modified successfully]"));
  }

  @Test
  void groupNotInDBTest() {

    GroupPutRequest putRequest =
        new GroupPutRequest(new GroupID("1234"), "Test Group 2", null, null);
    given(groupRepository.findByGroupID(putRequest.getGroupID())).willReturn(null);
    assertThrows(
        GroupIllegalArgument.class,
        () -> groupService.modifyGroup(putRequest),
        "Group does not exist in the database");
  }
}
