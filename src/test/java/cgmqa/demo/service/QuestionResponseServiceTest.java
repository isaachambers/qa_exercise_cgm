package cgmqa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


import cgmqa.demo.model.Question;
import cgmqa.demo.service.impl.QuestionExtractorImpl;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuestionResponseServiceTest {

  private QuestionResponseService questionResponseService = new QuestionResponseService();
  private static QuestionExtractorImpl questionExtractorImpl = new QuestionExtractorImpl();

  @Test
  @DisplayName("Should add question by question name")
  void shouldAddQuestion() {
    String testInputQuestion = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";

    boolean addQuestion = questionResponseService.addQuestion(testInputQuestion, questionExtractorImpl);

    assertThat(addQuestion).isTrue();
  }

  @Test
  @DisplayName("Should get added question by name")
  void shouldGetQuestionByQuestionName() {
    String testInputQuestion = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    questionResponseService.addQuestion(testInputQuestion, questionExtractorImpl);

    Optional<Question> question = questionResponseService.getQuestionByName("What is Peters favorite food?");

    assertThat(question).isPresent();
  }

  @Test
  @DisplayName("Should throw exception if question is invalid")
  void shouldThrowExceptionWhenQuestionInvalid() {
    String testInputQuestion = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"";

    Throwable thrown = catchThrowable(() -> questionResponseService.addQuestion(testInputQuestion, questionExtractorImpl));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Invalid question format");
  }

}