package com.picturesque.profile.exceptions;

public class FollowIllegalArgument extends RuntimeException{
    public FollowIllegalArgument() {
        super("Illegal Argument Specified!");
    }
    public FollowIllegalArgument(String message) {
        super(message);
    }
}



