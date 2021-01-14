package com.picturesque.profile.controller.follow;

import com.picturesque.profile.payloads.FollowAddResponse;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.POSTRequests.FollowRequest;
import com.picturesque.profile.service.FollowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class FollowController {

  private FollowService followService;

  @Autowired
  public void setServices(FollowService followService) {
    this.followService = followService;
  }

  @PostMapping(path = "/follow")
  public ResponseEntity<Response<FollowAddResponse>> addFollow(
      @Valid @RequestBody FollowRequest req) {
    Response<FollowAddResponse> resp = followService.addFollow(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }

  @PostMapping(path = "/unfollow")
  public ResponseEntity<Response<FollowAddResponse>> deleteFollow(
      @Valid @RequestBody FollowRequest req) {
    Response<FollowAddResponse> resp = followService.removeFollow(req);
    return new ResponseEntity<>(resp, resp.getStatusCode());
  }
}
