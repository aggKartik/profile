package com.picturesque.profile.databaseModels;
import org.springframework.data.annotation.Id;

/*
Person

Name
User_name (specified by user)
User_id - generated
Token - login info
Password (encrypted?)

 */

public class Person {

    @Id
    public int id;
    public String name;
    public String userName;
    private String userId;
    public String token; // probably need a seperate token class?
    public String pass; // probably need a Password token class?

    public Person(int id, String name, String userName, String userId, String token, String pass) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
