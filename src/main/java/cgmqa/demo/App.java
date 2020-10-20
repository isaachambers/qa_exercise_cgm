package cgmqa.demo;

import cgmqa.demo.model.Question;
import cgmqa.demo.service.QuestionExtractionService;

public class App {

  private static QuestionExtractionService questionExtractionService = QuestionExtractionService.getInstance();

  public static void main(String args[]) {
    String test = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    Question q = questionExtractionService.extractQuestion(test);
    System.out.println(q);
  }
}
