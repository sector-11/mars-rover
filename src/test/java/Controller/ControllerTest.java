package Controller;

import Controller.Controller;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final Controller controller =  new Controller();

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
    @DisplayName("Testing Controller class with null input")
    public void testControllerNullInput(){
        String[] input = null;
        controller.initializeAll(input);
        controller.executeAll();
        controller.outputAll();

        assertAll(
                () -> assertEquals("", errContent.toString()),
                () -> assertEquals("", outContent.toString())
        );
    }

    @Test
    @DisplayName("Testing Controller class with no inputs")
    public void testControllerNoInput(){
        String[] input = new String[0];
        controller.initializeAll(input);
        controller.executeAll();
        controller.outputAll();

        assertAll(
                () -> assertEquals("", errContent.toString()),
                () -> assertEquals("", outContent.toString())
        );
    }

    @Test
    @DisplayName("Testing Controller class with badly formatted, but valid inputs")
    public void testControllerBadFormat(){
        String[] input = new String[]{"1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};
        controller.initializeAll(input);
        controller.executeAll();
        controller.outputAll();
        assertAll(
                () -> assertEquals("FIRST INPUT SHOULD ALWAYS BE PLATEAU SIZE!\nUSING DEFAULT SIZE (5,5)\n", errContent.toString()),
                () -> assertEquals("[x: 1, y: 3. facing: N]\n[x: 5, y: 1. facing: E]\n", outContent.toString())
        );
    }

    @Test
    @DisplayName("Testing Controller class with valid inputs")
    public void testControllerValidInput(){
        String[] input = new String[]{"5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};
        controller.initializeAll(input);
        controller.executeAll();
        controller.outputAll();

        assertAll(
                () -> assertEquals("", errContent.toString()),
                () -> assertEquals("[x: 1, y: 3. facing: N]\n[x: 5, y: 1. facing: E]\n", outContent.toString())
        );
    }

}