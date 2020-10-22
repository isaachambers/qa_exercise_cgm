package cgmqa.demo.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputInputUtilTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  @DisplayName("Should Display Default Options")
  public void displayDefaultOptions() {
    OutputInputUtil.displayDefaultOptions();

    assertEquals("* Insert 1 to Add a question\n" +
        "* Insert 2 to Ask a question which is not in the system\n", outContent.toString());
  }

  @Test
  @DisplayName("Should output message")
  public void shouldPrintOutputMessage() {
    OutputInputUtil.printMessage("message");

    assertEquals("message\n", outContent.toString());
  }

  @Test
  @DisplayName("Should get input from scanner")
  public void shouldGetInputFromScanner() {
    ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
    System.setIn(in);
    String response = OutputInputUtil.getInput();

    assertThat(response).isEqualTo("1");
  }

  @Test
  @DisplayName("Should get full string line from scanner")
  public void shouldGetFullStringFromScanner() {
    ByteArrayInputStream in = new ByteArrayInputStream("what is the universe?".getBytes());
    System.setIn(in);
    String response = OutputInputUtil.getInputLineAsString();

    assertThat(response).isEqualTo("what is the universe?");
  }
}