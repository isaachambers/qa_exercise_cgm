package cgmqa.demo.service;

import cgmqa.demo.model.Question;

public interface QuestionExtractor {

  /**
   * @param questionAnswersString String containing question and answer in the format <question>? "<answer1>" "<answer2>" "<answerX>"
   * @return @Question extracted from the input with its answers
   */
  Question extractQuestion(String questionAnswersString);
}