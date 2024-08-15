package Input;

import java.util.ArrayList;

public class InstructionParser {
    public static Instruction[] parse(String input){
        if (input == null || input.isBlank()) return new Instruction[0];

        String[] inputArray = input.split("");
        ArrayList<Instruction> parsedInstructions = new ArrayList<Instruction>();
        for (String instruction : inputArray) {
            switch (instruction){
                case "L":
                    parsedInstructions.add(Instruction.L);
                    break;
                case "R":
                    parsedInstructions.add(Instruction.R);
                    break;
                case "M":
                    parsedInstructions.add(Instruction.M);
                    break;
                default:
                    break;
            }

        }

        return parsedInstructions.toArray(new Instruction[0]);
    }
}
