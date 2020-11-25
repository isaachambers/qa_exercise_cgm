package cgmqa.demo.service.impl;

import cgmqa.demo.model.Answer;
import cgmqa.demo.model.Question;
import cgmqa.demo.service.QuestionExtractor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionExtractorImpl implements QuestionExtractor {

  public QuestionExtractorImpl() {
  }

  /**
   * @param answersString String containing question and answer in the format "<answer1>" "<answer2>" "<answerX>"
   * @return List<String> of Strings extracted. eg. answer1, answer2, answer3
   */
  private List<Answer> extractAnswers(String answersString) {
    List<Answer> answers = new ArrayList<>();
    Pattern p = Pattern.compile("\"([^\"]*)\"");
    Matcher m = p.matcher(answersString);
    while (m.find()) {
      answers.add(new Answer(m.group(1)));
    }
    return answers;
  }


  @Override
  public Question extractQuestion(String questionAnswersString) {

    if (questionAnswersString == null || questionAnswersString.trim().length() == 0) {
      throw new IllegalArgumentException("Invalid question format");
    } else {
      String separator = "?";
      String question = questionAnswersString.substring(0, questionAnswersString.indexOf(separator) + 1);
      if (question.length() > 255) {
        throw new IllegalArgumentException("Question should contain upto 255 characters");
      } else if (question.length() == 0) {
        throw new IllegalArgumentException("Invalid question format");
      }

      String answersString = questionAnswersString.substring(questionAnswersString.indexOf(separator) + 1);
      List<Answer> answers = extractAnswers(answersString);

      if (answers.isEmpty()) {
        throw new IllegalArgumentException("Question should have at-least one answer");
      }
      answers.forEach(answerString -> {
        if (answerString.getAnswer().length() > 255) {
          throw new IllegalArgumentException("Answer should contain upto 255 characters");
        }
      });

      return new Question(question, answers);
    }
  }
}