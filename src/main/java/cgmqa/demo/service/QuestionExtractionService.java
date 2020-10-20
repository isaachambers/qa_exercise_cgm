package cgmqa.demo.service;

import cgmqa.demo.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionExtractionService implements QuestionExtractor {
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
   * @param answersString String containing question and answer in the format "<answer1>" "<answer2>" "<answerX>"
   * @return List<String> of Strings extracted. eg. answer1, answer2, answer3
   */
  private List<String> extractAnswers(String answersString) {
    List<String> answers = new ArrayList<>();
    Pattern p = Pattern.compile("\"([^\"]*)\"");
    Matcher m = p.matcher(answersString);
    while (m.find()) {
      answers.add(m.group(1));
    }
    return answers;
  }


  @Override
  public Question extractQuestion(String questionAnswersString) {
    String separator = "?";
    int markIndex = questionAnswersString.indexOf(separator);
    if (markIndex < 3) {
      throw new IllegalArgumentException("Invalid question format");
    }
    String question = questionAnswersString.substring(0, questionAnswersString.indexOf(separator) + 1);
    if (question.length() > 255 || question.length() == 0) {
      throw new IllegalArgumentException("Question should contain 1 to 255 characters");
    }
    String answersString = questionAnswersString.substring(questionAnswersString.indexOf(separator) + 1);
    List<String> answers = extractAnswers(answersString);

    if (answers.isEmpty()) {
      throw new IllegalArgumentException("Question should have at-least one answer");
    }
    answers.forEach(answerString -> {
      if (answerString.length() > 255 || answerString.length() == 0) {
        throw new IllegalArgumentException("Answer should contain 1 to 255 characters");
      }
    });

    return new Question(question, answers);
  }
}