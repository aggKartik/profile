package com.picturesque.profile.controller.group;

import com.picturesque.profile.payloads.GenericResponse.Response;
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
    public ResponseEntity<Response<PersonAddResponse>> addUserToSystem(@Valid @RequestBody GroupService req) {
        return groupService.addGroup(req);
    }

}
