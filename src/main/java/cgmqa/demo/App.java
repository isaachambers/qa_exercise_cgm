package cgmqa.demo;

import cgmqa.demo.service.QuestionExtractionService;
import cgmqa.demo.service.QuestionResponseService;
import java.util.Scanner;

public class App {

  private static QuestionExtractionService questionExtractionService = QuestionExtractionService.getInstance();
  private static QuestionResponseService questionResponseService = QuestionResponseService.getInstance();

  public static void main(String args[]) {

    String userInput;
    Scanner scanner = new Scanner(System.in);
    System.out.println("***** Welcome to Q&A *****");
    displayOptions();
    boolean keepRunning = true;

    while (keepRunning) {
      userInput = scanner.next();
      switch (userInput) {
        case "1":
          System.out.println("Type your question");
          String userQuestionAndAnswers = new Scanner(System.in).nextLine();
          if (questionResponseService.addQuestion(userQuestionAndAnswers, questionExtractionService)) {
            System.out.println("Question added");
            displayOptions();
          }
          break;
        case "2":
          System.out.println("What is your question?");
          String questionString = new Scanner(System.in).nextLine();
          questionResponseService.getQuestionByName(questionString).ifPresentOrElse(question -> {
            question.getAnswers().forEach(answer -> {
              System.out.println("\u2022 " + answer);
            });
          }, () -> {
            System.out.println("the answer to life, universe and everything is 42");
          });
          displayOptions();
          break;
        case "3":
          keepRunning = false;
          System.out.println("Shutting down");
          System.exit(0);
        default:
          System.exit(0);
      }
    }
  }

  public static void displayOptions() {
    System.out.println("* Insert 1 to Add a question");
    System.out.println("* Insert 2 to Ask a question which is not in the system");
    System.out.println("* Insert 3 to exit");
  }

}
