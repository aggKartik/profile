package com.picturesque.profile.service;

import com.picturesque.profile.exceptions.PersonIllegalArgument;
import com.picturesque.profile.payloads.GenericResponse.Response;
import com.picturesque.profile.payloads.PersonAddResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonServiceErrorHandler {

  @ExceptionHandler({PersonIllegalArgument.class})
  public ResponseEntity<Response<PersonAddResponse>> handleInvalidArguments(PersonIllegalArgument e) {

    Response<PersonAddResponse> resp = new Response<PersonAddResponse>(new PersonAddResponse(e.getMessage()),
            HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
  }

}
