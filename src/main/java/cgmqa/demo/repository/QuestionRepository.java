package cgmqa.demo.repository;

import cgmqa.demo.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionRepository {

  public static List<Question> questions = new ArrayList<>();

  public QuestionRepository() {

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
