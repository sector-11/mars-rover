package Input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauSizeParserTest {
    @Test
    @DisplayName("Parser returns an empty plateau when given empty and null inputs")
    public void testEmptyOrNull(){
        String nullInput = null;
        String emptyInput = "";

        var nullOutput = PlateauSizeParser.parse(nullInput);
        var emptyOutput = PlateauSizeParser.parse(emptyInput);

        PlateauSize expected = new PlateauSize(0, 0);

        assertAll(
                () -> assertEquals(expected, nullOutput),
                () -> assertEquals(expected, emptyOutput)
        );
    }


    @Test
    @DisplayName("Parser returns empty plateau when given bad input")
    public void testBadInput() {
        String input = "55A";


        var output = PlateauSizeParser.parse(input);

        PlateauSize expected = new PlateauSize(0, 0);

        assertEquals(expected, output)
    }

    @Test
    @DisplayName("Parser returns correct plateau with given inputs")
    public void testSingleInputs(){
        String input1 = "5, 5";
        String input2 = "2, 8";
        String input3 = "1, 1";

        var output1 = PlateauSizeParser.parse(input1);
        var output2 = PlateauSizeParser.parse(input2);
        var output3 = PlateauSizeParser.parse(input3);

        PlateauSize expected1 = new PlateauSize(5, 5);
        PlateauSize expected2 = new PlateauSize(2, 8);
        PlateauSize expected3 = new PlateauSize(1, 1);

        assertAll(
                () -> assertEquals(expected1, output1),
                () -> assertEquals(expected2, output2),
                () -> assertEquals(expected3, output3)
        );
    }



}