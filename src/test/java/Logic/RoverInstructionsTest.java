package Logic;

import Input.Directions;
import Input.Instruction;
import Input.PlateauSize;
import Input.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoverInstructionsTest {
    @Test
    @DisplayName("Test rover can reject invalid instructions")
    public void testReceiveInvalidOrders(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover = new Rover(new Position(1, 1, Directions.N));

        boolean expected1 = rover.giveInstructions(null);
        boolean expected2 = rover.giveInstructions(new Instruction[0]);

        assertAll(
                () -> assertFalse(expected1),
                () -> assertFalse(expected2)
        );
    }

    @Test
    @DisplayName("Test rover can take valid instructions")
    public void testReceiveOrders(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover = new Rover(new Position(1, 1, Directions.N));

        Instruction[] instructions = new Instruction[]{Instruction.L, Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.R, Instruction.M, Instruction.L, Instruction.M};

        boolean expected = rover.giveInstructions(instructions);

        assertTrue(expected);
    }

    @Test
    @DisplayName("Test rover can execute valid instructions")
    public void testExecuteOrders(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover = new Rover(new Position(1, 1, Directions.N), plateau);
        plateau.addEntity(rover);

        Instruction[] instructions = new Instruction[]{Instruction.R, Instruction.M, Instruction.L, Instruction.M};

        boolean receivedInstructions = rover.giveInstructions(instructions);
        rover.executeInstructions(-1);

        assertAll(
                () -> assertTrue(receivedInstructions),
                () -> assertEquals("[x: 2, y: 2. facing: N]", rover.getPosition().toString())
        );
    }
}
