package com.picturesque.profile.payloads;

public class PersonAddResponse {

    private String message;

    public PersonAddResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
