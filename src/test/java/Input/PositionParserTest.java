package Input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {
    @Test
    @DisplayName("Parser returns an null position when given empty and null inputs")
    public void testEmptyOrNull(){
        String nullInput = null;
        String emptyInput = "";

        var nullOutput = PositionParser.parse(nullInput);
        var emptyOutput = PositionParser.parse(emptyInput);

        assertAll(
                () -> assertNull(nullInput),
                () -> assertNull(emptyOutput)
        );
}


    @Test
    @DisplayName("Parser returns null position when given bad or incorrectly formatted input")
    public void testBadInput() {
        String inputBad = "5A5A";
        String inputBadFormat1 = "5,5,N";
        String inputBadFormat2 = "5 S 5";


        var outputBad = PositionParser.parse(inputBad);
        var outputBadFormat1 = PositionParser.parse(inputBadFormat1);
        var outputBadFormat2 = PositionParser.parse(inputBadFormat2);

        assertAll(
                () -> assertNull(outputBad),
                () -> assertNull(outputBadFormat1),
                () -> assertNull(outputBadFormat2)
        );
    }

    @Test
    @DisplayName("Parser returns correct position with given good inputs")
    public void testGoodInputs(){
        String input1 = "5 5 N";
        String input2 = "2 8 S";
        String input3 = "1 1 E";

        var output1 = PositionParser.parse(input1);
        var output2 = PositionParser.parse(input2);
        var output3 = PositionParser.parse(input3);

        Position expected1 = new Position(5, 5, Directions.N);
        Position expected2 = new Position(2, 8, Directions.S);
        Position expected3 = new Position(1, 1, Directions.E);

        assertAll(
                () -> assertEquals(expected1.toString(), output1.toString()),
                () -> assertEquals(expected2.toString(), output2.toString()),
                () -> assertEquals(expected3.toString(), output3.toString())
        );
    }

}