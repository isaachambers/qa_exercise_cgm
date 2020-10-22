package cgmqa.demo.utils;

import java.util.Scanner;

public class OutputInputUtil {

  public static void displayDefaultOptions() {
    System.out.println("* Insert 1 to Add a question");
    System.out.println("* Insert 2 to Ask a question which is not in the system");
  }

  public static String getInputLineAsString() {
    return new Scanner(System.in).nextLine();
  }

  public static void printMessage(String message) {
    System.out.println(message);
  }

  public static String getInput() {
    return new Scanner(System.in).nextInt() + "";
  }
}