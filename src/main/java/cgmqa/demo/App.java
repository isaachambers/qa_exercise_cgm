package cgmqa.demo;

import cgmqa.demo.model.Question;
import cgmqa.demo.service.impl.QuestionExtractorImpl;
import cgmqa.demo.service.QuestionResponseService;
import cgmqa.demo.utils.OutputInputUtil;
import java.util.Optional;

public class App {

  public static void main(String args[]) {

    QuestionExtractorImpl questionExtractorImpl = new QuestionExtractorImpl();
    QuestionResponseService questionResponseService = new QuestionResponseService();

    String userInput;
    OutputInputUtil.displayDefaultOptions();

    userInput = OutputInputUtil.getInput();
    switch (userInput) {
      case "1":
        OutputInputUtil.printMessage("Type your question");
        String userQuestionAndAnswers = OutputInputUtil.getInputLineAsString();
        if (questionResponseService.addQuestion(userQuestionAndAnswers, questionExtractorImpl)) {
          OutputInputUtil.printMessage("Question added");
        }
        break;
      case "2":
        OutputInputUtil.printMessage("What is your question?");
        String questionString = OutputInputUtil.getInputLineAsString();
        Optional<Question> question = questionResponseService.getQuestionByName(questionString);
        if (question.isPresent()) {
          question.get().getAnswers().forEach(answer -> {
            OutputInputUtil.printMessage("\u2022 " + answer.getAnswer());
          });
        } else {
          OutputInputUtil.printMessage("the answer to life, universe and everything is 42");
        }
        break;
      default:
        System.exit(0);
    }
  }

}
