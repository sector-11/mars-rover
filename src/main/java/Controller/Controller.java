package Controller;

import Logic.*;
import Input.*;

import java.util.ArrayList;

public class Controller {
    private final String PLATEAUSIZE_REGEX = "^\\d+ \\d+$";
    private final String POSITION_REGEX = "^\\d+ \\d+ [NEWSnews]$";
    private final String INSTRUCTION_REGEX = "^[LRMlrm]+$";

    private Plateau plateau;


    public Controller(PlateauSize plateauSize, ArrayList<InputRover> rovers) {
        this.plateau = new Plateau(plateauSize);

        for (InputRover rover : rovers) {
            Rover currentRover = new Rover(rover.position(), plateau, rover.name());
            currentRover.giveInstructions(rover.instructions());
            this.plateau.addEntity(currentRover);
        }
    }

    public Controller (){
    }

    public void initializeAll(String[] input){
        if (input == null || input.length < 1) input = new String[]{"0 0"};

        if (input[0].matches(PLATEAUSIZE_REGEX)){
            plateau = new Plateau(PlateauSizeParser.parse(input[0]));
        } else {
            System.err.println("FIRST INPUT SHOULD ALWAYS BE PLATEAU SIZE!");
            System.err.println("USING DEFAULT SIZE (5,5)");
            plateau = new Plateau(new PlateauSize(5,5));
        }

        for (int i = 0; i < input.length; i++) {
            if (i + 1 < input.length){
                if (input[i].matches(POSITION_REGEX) && input[i+1].matches(INSTRUCTION_REGEX)){
                    Rover newRover = new Rover(PositionParser.parse(input[i]), plateau);
                    plateau.addEntity(newRover);
                    newRover.giveInstructions(InstructionParser.parse(input[i+1]));
                    i++;
                }
            } else {
                if (input[i].matches(POSITION_REGEX)){
                    Rover newRover = new Rover(PositionParser.parse(input[i]), plateau);
                    plateau.addEntity(newRover);
                }
            }
        }
    }

    public void initializeAll(ArrayList<String> input){
        initializeAll(input.toArray(new String[0]));
    }

    public void executeAll(){
        ArrayList<Rover> toExecute = new ArrayList<>(plateau.getEntities());
        for (Rover rover : toExecute) {
            rover.executeInstructions(-1);
        }
    }

    public void outputAll(){
        for (Rover entity : plateau.getEntities()) {
            System.out.println(entity.getPosition().toString());
        }
    }
}
