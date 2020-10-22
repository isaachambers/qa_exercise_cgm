package cgmqa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


import cgmqa.demo.model.Answer;
import cgmqa.demo.model.Question;
import cgmqa.demo.service.impl.QuestionExtractorImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionExtractorImplTest {

  private QuestionExtractorImpl extractionService = new QuestionExtractorImpl();

  @Test
  @DisplayName("Should correctly extract question from question-answers string")
  public void shouldCorrectlyExtractQuestion() {
    String testInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    Question question = extractionService.extractQuestion(testInput);

    assertThat(question.getQuestion()).isEqualTo("What is Peters favorite food?");
  }

  @Test
  @DisplayName("Should correctly extract answers from question-answers string")
  public void shouldCorrectlyExtractListOfAnswers() {
    String testInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
    Question question = extractionService.extractQuestion(testInput);
    List<Answer> answers = question.getAnswers();

    assertThat(answers).extracting(Answer::getAnswer).containsExactlyInAnyOrder("Pizza", "Spaghetti", "Ice cream");
  }

  @Test
  @DisplayName("Should throw exception if question exceeds 255 characters")
  public void shouldThrowExceptionWhenQuestionExceedsAllowedChars() {
    String longQuestion = "t7qDUWiUmARpDMCP2df7pnGbsd8jb0XfH2MJeCEMGx8zinPJA73aEsOUiQJGDdJAKbX204S2e2DHryL0RM5QBePNXmlCgzxDsLOQZ4cjl05pBkK7h50VJvp0mTacOxuYJ92rcskBXNnjWWtLACHkmkTO2kKsimr7vgjbt04p6jX4rkKRBpFcv0g4sijrDKkJaQHx4eHEyTRZ8ZASBFvk0XS5SvwhpBhecyBSSq7lEOlLPBNZyWOLj2h0ziCBBlw";
    String testInput = longQuestion + "? \"Pizza\" \"Spaghetti\" \"Ice cream\"";

    Throwable thrown = catchThrowable(() -> extractionService.extractQuestion(testInput));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Question should contain upto 255 characters");
  }

  @Test
  @DisplayName("Should throw exception if any answer exceeds 255 characters")
  public void shouldThrowExceptionWhenAnyAnswerChars() {
    String longAnswer = "t7qDUWiUmARpDMCP2df7pnGbsd8jb0XfH2MJeCEMGx8zinPJA73aEsOUiQJGDdJAKbX204S2e2DHryL0RM5QBePNXmlCgzxDsLOQZ4cjl05pBkK7h50VJvp0mTacOxuYJ92rcskBXNnjWWtLACHkmkTO2kKsimr7vgjbt04p6jX4rkKRBpFcv0g4sijrDKkJaQHx4eHEyTRZ8ZASBFvk0XS5SvwhpBhecyBSSq7lEOlLPBNZyWOLj2h0ziCBBlwe";
    String testInput = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"" + longAnswer + "\"";

    Throwable thrown = catchThrowable(() -> extractionService.extractQuestion(testInput));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Answer should contain upto 255 characters");
  }

  @Test
  @DisplayName("Should throw exception if question has no answers")
  public void shouldThrowExceptionWhenQuestionHasNoAnswers() {
    String testQuestionWithNoAnswers = "What is Peters favorite food?";

    Throwable thrown = catchThrowable(() -> extractionService.extractQuestion(testQuestionWithNoAnswers));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Question should have at-least one answer");
  }

  @Test
  @DisplayName("Should throw exception if question is null")
  public void shouldThrowExceptionWhenQuestionIsNull() {

    Throwable thrown = catchThrowable(() -> extractionService.extractQuestion(null));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Invalid question format");
  }

  @Test
  @DisplayName("Should throw exception if question is empty")
  public void shouldThrowExceptionWhenQuestionIsEmpty() {
    String testInput = " ";

    Throwable thrown = catchThrowable(() -> extractionService.extractQuestion(testInput));

    assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    assertThat(thrown.getMessage()).isEqualTo("Invalid question format");
  }
}