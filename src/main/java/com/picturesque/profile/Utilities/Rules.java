package com.picturesque.profile.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Rules {

  public static boolean isValidUserName(String str) {

    List<Function<String, Boolean>> rules = new ArrayList<>();
    rules.add(str1 -> str1.matches(".*\\s.*"));
    rules.add(str1 -> str.length() < 2);

    return !(containsWhiteSpace(str) || isNotMinimumLength(str));
  }

  public static boolean containsWhiteSpace(String str) {
    //return str.contains(" ");
    return str.matches(".*\\s.*");
  }

  public static List<Function<String, Boolean>> listOfRules() {
    List<Function<String, Boolean>> rules = new ArrayList<>();
    rules.add(str -> str.matches(".*\\s.*"));
    rules.add(str -> str.length() < 2);
    return rules;
  }

  public static boolean isNotMinimumLength(String str) {
    return str.length() < 2;
  }

  /**
   * TODO:
   * add more rules here as needed
   */

}
