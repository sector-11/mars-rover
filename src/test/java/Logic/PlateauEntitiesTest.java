package Logic;

import Input.Directions;
import Input.PlateauSize;
import Input.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlateauEntitiesTest {
    @Test
    @DisplayName("Returns true when attempting to place rover in empty place")
    public void testPlaceOneRover(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover = new Rover(new Position(1, 1, Directions.N));

        var output = plateau.addEntity(rover);

        assertTrue(output);
    }

    @Test
    @DisplayName("Returns false when attempting to place null rover")
    public void testPlaceNullRover(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover = null;

        var output = plateau.addEntity(rover);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns false when attempting to place rover out of bounds")
    public void testPlaceRoverOutOfBounds(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover = new Rover(new Position(2, 2, Directions.N));

        var output = plateau.addEntity(rover);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns false when attempting to put two rovers in same place")
    public void testPlaceRoversSamePlace(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover1 = new Rover(new Position(1, 1, Directions.N));
        Rover rover2 = new Rover(new Position(1, 1, Directions.N));

        plateau.addEntity(rover1);
        var output = plateau.addEntity(rover2);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns false when attempting to move null rover")
    public void testMoveNullRover(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover = null;

        var output = plateau.moveEntity(rover);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns false when attempting to move rover that isn't being tracked")
    public void testMoveRoverNotTracked(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover =  new Rover(new Position(1, 1, Directions.N));

        var output = plateau.moveEntity(rover);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns true when attempting to move rover to empty place")
    public void testMoveRoverClearPath(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover = new Rover(new Position(1, 1, Directions.S));

        plateau.addEntity(rover);
        var output = plateau.moveEntity(rover);

        assertTrue(output);
    }

    @Test
    @DisplayName("Returns false when attempting to move rover out of bounds")
    public void testMoveRoverOutOfBounds(){
        Plateau plateau = new Plateau(new PlateauSize(1, 1));
        Rover rover = new Rover(new Position(0, 0, Directions.N));

        plateau.addEntity(rover);
        var output = plateau.moveEntity(rover);

        assertFalse(output);
    }

    @Test
    @DisplayName("Returns false when attempting to move rover in to occupied place")
    public void testMoveRoverColliding(){
        Plateau plateau = new Plateau(new PlateauSize(2, 2));
        Rover rover1 = new Rover(new Position(1, 1, Directions.S));
        Rover rover2 = new Rover(new Position(1, 2, Directions.N));

        plateau.addEntity(rover1);
        plateau.addEntity(rover2);

        var output = plateau.moveEntity(rover1);

        assertFalse(output);
    }
}
