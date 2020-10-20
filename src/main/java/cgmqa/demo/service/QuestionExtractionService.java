package cgmqa.demo.service;

import cgmqa.demo.model.Question;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionExtractionService {
  private static QuestionExtractionService extractionService;

  private QuestionExtractionService() {
  }

  public static QuestionExtractionService getInstance() {
    if (extractionService == null) {
      extractionService = new QuestionExtractionService();
    }
    return extractionService;
  }

  /**
   * @param questionAnswersString String containing question and answer in the format <question>? “<answer1>” “<answer2>” “<answerX>”
   * @return question extracted from the input with its answers
   */
  public Question extractQuestion(String questionAnswersString) {

    String separator = "?";
    String question = questionAnswersString.substring(0, questionAnswersString.indexOf(separator) + 1);
    String answers = questionAnswersString.substring(questionAnswersString.indexOf(separator) + 1);

    List<String> answersString = extractAnswers(answers);
    return new Question(question, answersString);
  }

  /**
   * @param answers String containing question and answer in the format “<answer1>” “<answer2>” “<answerX>”
   * @return List<String> of Strings extracted. eg. answer1, answer2, answer3
   */
  private List<String> extractAnswers(String answers) {
    return Arrays.stream(answers.split("\"")).filter(s -> s.trim().length() == 0).collect(Collectors.toList());
  }
}