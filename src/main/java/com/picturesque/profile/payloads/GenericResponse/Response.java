package com.picturesque.profile.payloads.GenericResponse;

import org.springframework.http.HttpStatus;

public class Response<T> {

  private T response;
  private HttpStatus statusCode;

  public Response(T response, HttpStatus statusCode) {
    this.response = response;
    this.statusCode = statusCode;
  }

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(HttpStatus statusCode) {
    this.statusCode = statusCode;
  }
}
