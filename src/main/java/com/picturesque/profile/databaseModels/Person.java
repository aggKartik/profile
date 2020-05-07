package com.picturesque.profile.databaseModels;

import com.picturesque.profile.helperModels.UserID;
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

    public @Id int id;
    public String name;
    public String userName;
    private UserID userID;
    public String token; // probably need a seperate token class?
    public String pass; // probably need a Password token class?
    public int points;
    public String pic;

    public Person(int id, String name, String userName,  UserID userID, String token, String pass, String pic, int points) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.userID = userID;
        this.token = token;
        this.pass = pass;
        this.pic = pic;
        this.points = points;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }

    public String getPic() { return pic; }

    public void setPic(String pic) { this.pic = pic; }

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

    public UserID getUserId() {
        return userID;
    }

    public void setUserId(UserID userId) {
        this.userID = userId;
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

    // doesn't include token in toString
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userID + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
