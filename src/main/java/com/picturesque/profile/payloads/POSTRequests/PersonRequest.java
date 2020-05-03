package com.picturesque.profile.payloads.POSTRequests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonRequest {

    @NotNull @NotEmpty
    public String name;
    @NotNull @NotEmpty
    public String userName;
    public String token; // probably need a seperate token class?
    public String pass;

    public PersonRequest(String name, String userName, String token, String pass) {
        this.name = name;
        this.userName = userName;
        this.token = token;
        this.pass = pass;
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
}
