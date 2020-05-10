package com.picturesque.profile.exceptions;

public class GroupIllegalArgument extends RuntimeException {
  public GroupIllegalArgument() {
    super("Illegal Argument Specified!");
  }
  public GroupIllegalArgument(String message) {
    super(message);
  }
}
