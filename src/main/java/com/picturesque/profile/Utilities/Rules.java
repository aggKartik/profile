package com.picturesque.profile.Utilities;

public class Rules {

  public static boolean isValidUserName(String str) {
    return !(containsWhiteSpace(str) || isNotMinimumLength(str));
  }

  public static boolean containsWhiteSpace(String str) {
    //return str.contains(" ");
    return str.matches(".*\\s.*");
  }

  public static boolean isNotMinimumLength(String str) {
    return str.length() < 2;
  }

  /**
   * TODO:
   * add more rules here as needed
   */

}
