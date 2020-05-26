package com.picturesque.profile.controller.group;

import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.POSTRequests.GroupInviteRequest;
import com.picturesque.profile.payloads.POSTRequests.GroupRequest;
import com.picturesque.profile.payloads.PersonAddResponse;
import com.picturesque.profile.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Response<GroupAddResponse>> addUserToSystem(@Valid @RequestBody GroupRequest req) {
        Response<GroupAddResponse> resp = groupService.addGroup(req);
        return new ResponseEntity<>(resp, resp.getStatusCode());
    }

    @PostMapping(path = "/group_invite")
    public ResponseEntity<Response<GroupAddResponse>> addGroupInvitation(@Valid @RequestBody GroupInviteRequest req) {
      Response<GroupAddResponse> resp = groupService.addGroupInvitation(req);
      return new ResponseEntity<>(resp, resp.getStatusCode());
    }
}
