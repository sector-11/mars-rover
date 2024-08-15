package Logic;

import Input.Directions;
import Input.Instruction;
import Input.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverRotate {
    @Test
    @DisplayName("Returns current direction when given invalid instruction")
    public void testInvalidInput(){
        Instruction input = Instruction.M;
        Rover testRover = new Rover(new Position(1, 1, Directions.N));

        var output = testRover.rotate(input);

        assertEquals(Directions.N, output);
    }

    @Test
    @DisplayName("Returns correct direction for all left rotations")
    public void testInvalidInput(){
        Instruction input = Instruction.L;
        Rover testRoverN = new Rover(new Position(1, 1, Directions.N));
        Rover testRoverS = new Rover(new Position(1, 1, Directions.S));
        Rover testRoverE = new Rover(new Position(1, 1, Directions.E));
        Rover testRoverW = new Rover(new Position(1, 1, Directions.W));

        var outputN = testRoverN.rotate(input);
        var outputS = testRoverS.rotate(input);
        var outputE = testRoverE.rotate(input);
        var outputW = testRoverW.rotate(input);

        assertAll(
                () -> assertEquals(Directions.W, outputN),
                () -> assertEquals(Directions.E, outputS),
                () -> assertEquals(Directions.N, outputE),
                () -> assertEquals(Directions.S, outputW)
        );
    }

    @Test
    @DisplayName("Returns correct direction for all right rotations")
    public void testInvalidInput(){
        Instruction input = Instruction.R;
        Rover testRoverN = new Rover(new Position(1, 1, Directions.N));
        Rover testRoverS = new Rover(new Position(1, 1, Directions.S));
        Rover testRoverE = new Rover(new Position(1, 1, Directions.E));
        Rover testRoverW = new Rover(new Position(1, 1, Directions.W));

        var outputN = testRoverN.rotate(input);
        var outputS = testRoverS.rotate(input);
        var outputE = testRoverE.rotate(input);
        var outputW = testRoverW.rotate(input);

        assertAll(
                () -> assertEquals(Directions.E, outputN),
                () -> assertEquals(Directions.W, outputS),
                () -> assertEquals(Directions.S, outputE),
                () -> assertEquals(Directions.N, outputW)
        );
    }
}
