package com.picturesque.profile.payloads.POSTRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
@Scope("request")
public class PersonRequest {

  @NotNull @NotEmpty public String name;
  @NotNull @NotEmpty public String userName;
  public String token; // probably need a seperate token class?
  @NotNull @NotEmpty public String pass;
  @NotNull public Date dob;
  @Autowired HttpServletRequest request;

  public PersonRequest(
      @NotNull @NotEmpty String name,
      @NotNull @NotEmpty String userName,
      String token,
      @NotNull @NotEmpty String pass,
      @NotNull Date dob) {
    this.name = name;
    this.userName = userName;
    this.token = token;
    this.pass = pass;
    this.dob = dob;
  }

  public String getClientIp() {
    String remoteAddr = "";
    if (request != null) {
      remoteAddr = request.getHeader("X-FORWARDED-FOR");
      if (remoteAddr == null || "".equals(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
      }
    }
    return remoteAddr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }
}
