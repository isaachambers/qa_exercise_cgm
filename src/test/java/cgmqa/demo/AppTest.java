package cgmqa.demo;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;


import cgmqa.demo.model.Answer;
import cgmqa.demo.model.Question;
import cgmqa.demo.repository.QuestionRepository;
import cgmqa.demo.utils.OutputInputUtil;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class AppTest {

  @DisplayName("Should add new Question to Application")
  @Test
  void shouldAddNewQuestion() {
    try (MockedStatic mocked = mockStatic(OutputInputUtil.class)) {
      String[] args = {};
      String testInputQuestion = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
      mocked.when(OutputInputUtil::getInput).thenReturn("1");
      mocked.when(OutputInputUtil::getInputLineAsString).thenReturn(testInputQuestion);
      App.main(args);

      mocked.verify(times(1), () -> OutputInputUtil.printMessage("Question added"));
    }
  }

  @DisplayName("Should return \"the answer to life, universe and everything is 42\" when question does not exist")
  @Test
  void shouldPrintDefaultMessageForNonExistingQuestions() {
    try (MockedStatic mocked = mockStatic(OutputInputUtil.class)) {
      String[] args = {};
      String testInputQuestion = "What is Peters favorite food?";
      mocked.when(OutputInputUtil::getInput).thenReturn("2");
      mocked.when(OutputInputUtil::getInputLineAsString).thenReturn(testInputQuestion);

      App.main(args);

      mocked.verify(times(1), () -> OutputInputUtil.printMessage("the answer to life, universe and everything is 42"));
    }
  }

  @DisplayName("Should return all answer if question exists")
  @Test
  void shouldReturnAnswer() {
    try (MockedStatic mocked = mockStatic(OutputInputUtil.class)) {
      Question question1 = new Question("question1?", Arrays.asList(new Answer("answer1")));
      QuestionRepository.questions.add(question1);
      String[] args = {};
      String testInputQuestion = "question1?";
      mocked.when(OutputInputUtil::getInput).thenReturn("2");
      mocked.when(OutputInputUtil::getInputLineAsString).thenReturn(testInputQuestion);

      App.main(args);

      mocked.verify(times(1), () -> OutputInputUtil.printMessage("• answer1"));
    }
  }
}