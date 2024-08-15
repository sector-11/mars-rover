package Input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {
    @Test
    @DisplayName("Parser returns an empty Instruction array when given empty and null inputs")
    public void testEmptyOrNull(){
        String nullInput = null;
        String emptyInput = "";

        var nullOutput = InstructionParser.parse(nullInput);
        var emptyOutput = InstructionParser.parse(emptyInput);

        Instruction[] expected = new Instruction[0];

        assertAll(
                () -> assertArrayEquals(expected, nullOutput),
                () -> assertArrayEquals(expected, emptyOutput)
        );
    }

    @Test
    @DisplayName("Parser returns correct instruction when given single instructions")
    public void testSingleInputs(){
        String lInput = "L";
        String rInput = "R";
        String mInput = "M";

        var lOutput = InstructionParser.parse(lInput);
        var rOutput = InstructionParser.parse(rInput);
        var mOutput = InstructionParser.parse(mInput);

        Instruction[] lExpected = new Instruction[]{Instruction.L};
        Instruction[] rExpected = new Instruction[]{Instruction.R};
        Instruction[] mExpected = new Instruction[]{Instruction.M};

        assertAll(
                () -> assertArrayEquals(lExpected, lOutput),
                () -> assertArrayEquals(rExpected, rOutput),
                () -> assertArrayEquals(mExpected, mOutput)
        );
    }

    @Test
    @DisplayName("Parser returns expected result when given string of inputs")
    public void testStringOfInputs(){
        String input = "LMMRMRMLM";

        var output = InstructionParser.parse(input);

        Instruction[] expected = new Instruction[]{Instruction.L, Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.R, Instruction.M, Instruction.L, Instruction.M};

        assertArrayEquals(expected, output);
    }
}