package com.picturesque.profile.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Rules {


  private static List<Function<String, Boolean>> listOfRules() {
    List<Function<String, Boolean>> rules = new ArrayList<>();
    rules.add(str -> str.matches(".*\\s.*"));
    rules.add(str -> str.length() < 2);
    return rules;
  }

  public static boolean isValidUserName(String str) {
    return listOfRules().stream().noneMatch(func -> func.apply(str));
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
