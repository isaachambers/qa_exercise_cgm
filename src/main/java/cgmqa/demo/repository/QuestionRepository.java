package cgmqa.demo.repository;

import cgmqa.demo.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionRepository {

  private static QuestionRepository questionRepository;
  private List<Question> questions;

  private QuestionRepository() {
    questions = new ArrayList<>();
  }

  public static QuestionRepository getInstance() {

    if (questionRepository == null) {
      questionRepository = new QuestionRepository();
    }
    return questionRepository;
  }

  public void addQuestion(Question question) {
    this.questions.add(question);
  }

  public Optional<Question> getByQuestionName(String askedQuestion) {
    return questions.stream().filter(question -> question.getQuestion().equals(askedQuestion)).findFirst();
  }

  public List<Question> getQuestions() {
    return questions;
  }
}
