package com.picturesque.profile.controller.group;

import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.payloads.PUTRequests.GroupPutRequest;
import com.picturesque.profile.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class GroupController {
  private GroupService groupService;

  @Autowired
  public void setServices(GroupService groupService) {
    this.groupService = groupService;
  }

  @PostMapping(path = "/group")
  public ResponseEntity<Response<GroupAddResponse>> addGroupToSystem(
      @Valid @RequestBody GroupRequest req) {
    Response<GroupAddResponse> resp = groupService.addGroup(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }

  @PutMapping(path = "/group")
  public ResponseEntity<Response<GroupAddResponse>> modifyGroup(
      @Valid @RequestBody GroupPutRequest req) {
    Response<GroupAddResponse> resp = groupService.modifyGroup(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }
}
