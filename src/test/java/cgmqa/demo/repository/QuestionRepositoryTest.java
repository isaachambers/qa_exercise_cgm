package cgmqa.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;


import cgmqa.demo.model.Answer;
import cgmqa.demo.model.Question;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuestionRepositoryTest {

  private QuestionRepository questionRepository = new QuestionRepository();

  @Test
  @DisplayName("Should be able to add question to repository")
  void addQuestion() {
    Question question1 = new Question("question1?", Arrays.asList(new Answer("answer1")));
    Question question2 = new Question("question2?", Arrays.asList(new Answer("answer2")));
    questionRepository.addQuestion(question1);
    questionRepository.addQuestion(question2);

    assertThat(questionRepository.getQuestions().size()).isEqualTo(2);
  }

  @Test
  @DisplayName("Should retrieve a question from the repository by question name")
  void getByQuestionName() {

    Question question1 = new Question("question1?", Arrays.asList(new Answer("answer1")));
    questionRepository.addQuestion(question1);
    Question retrievedQuestion = questionRepository.getByQuestionName(question1.getQuestion()).orElse(null);

    assertThat(retrievedQuestion).isNotNull();
    assertThat(retrievedQuestion.getQuestion()).isEqualTo("question1?");
  }
}