package cgmqa.demo.service;

import cgmqa.demo.model.Question;
import cgmqa.demo.repository.QuestionRepository;
import java.util.Optional;

public class QuestionResponseService {

  private static QuestionResponseService questionResponseService;
  private static QuestionRepository questionRepository = QuestionRepository.getInstance();

  private QuestionResponseService() {
  }

  public static QuestionResponseService getInstance() {
    if (questionResponseService == null) {
      questionResponseService = new QuestionResponseService();
    }
    return questionResponseService;
  }

  public boolean addQuestion(String questionString, QuestionExtractor questionExtractor) {
    try {
      Question question = questionExtractor.extractQuestion(questionString);
      questionRepository.addQuestion(question);
      return true;
    } catch (Exception e) {
      throw e;
    }
  }

  public Optional<Question> getQuestionByName(String question) {
    return questionRepository.getByQuestionName(question);
  }
}
