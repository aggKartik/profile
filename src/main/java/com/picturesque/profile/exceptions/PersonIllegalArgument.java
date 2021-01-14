package com.picturesque.profile.exceptions;

public class PersonIllegalArgument extends RuntimeException {
  public PersonIllegalArgument() {
    super("Illegal Argument Specified!");
  }

  public PersonIllegalArgument(String message) {
    super(message);
  }
}
