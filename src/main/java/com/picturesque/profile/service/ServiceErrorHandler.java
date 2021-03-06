package com.picturesque.profile.service;

import com.picturesque.profile.exceptions.FollowIllegalArgument;
import com.picturesque.profile.exceptions.GroupIllegalArgument;
import com.picturesque.profile.exceptions.PersonIllegalArgument;
import com.picturesque.profile.payloads.FollowAddResponse;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.GroupAddResponse;
import com.picturesque.profile.payloads.PersonAddResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceErrorHandler {

  @ExceptionHandler({PersonIllegalArgument.class})
  public ResponseEntity<Response<PersonAddResponse>> handlePersonInvalidArguments(
      PersonIllegalArgument e) {
    Response<PersonAddResponse> resp =
        new Response<PersonAddResponse>(
            new PersonAddResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({GroupIllegalArgument.class})
  public ResponseEntity<Response<GroupAddResponse>> handleGroupInvalidArguments(
      GroupIllegalArgument e) {
    Response<GroupAddResponse> resp =
        new Response<GroupAddResponse>(
            new GroupAddResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({FollowIllegalArgument.class})
  public ResponseEntity<Response<FollowAddResponse>> handleFollowIllegalArgument(
      FollowIllegalArgument e) {
    Response<FollowAddResponse> resp =
        new Response<FollowAddResponse>(
            new FollowAddResponse(e.getMessage()), HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
  }
}
