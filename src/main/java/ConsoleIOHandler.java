import Input.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleIOHandler {
    private final String PLATEAUSIZE_REGEX = "^\\d+ \\d+$";
    private final String POSITION_REGEX = "^\\d+ \\d+ [NEWSnews]$";
    private final String INSTRUCTION_REGEX = "^[LRMlrm]+$";

    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public Optional<ArrayList<String>> roverNames(int roverAmount){
        if (roverAmount == 0) return Optional.empty();
        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < roverAmount; i++) {
            System.out.println("\nPlease enter a name for rover " + roverAmount + ":");
            nameList.add(scanner.nextLine());
        }

        return Optional.of(nameList);
    }

    public Position startingPosition(String roverName, PlateauSize plateauSize){
        StringBuilder startingPosition = new StringBuilder();
        System.out.println("\nFor the horizontal starting position of rover " + roverName + ".");
        startingPosition.append(nextIntBounded(0, plateauSize.getX())).append(" ");
        System.out.println("\nFor the vertical starting position of rover " + roverName + ".");
        startingPosition.append(nextIntBounded(0, plateauSize.getY())).append(" ");

        String userInputString;
        boolean shouldContinueLoop = true;
        do {
            System.out.println("Please enter initial direction for rover " + roverName + ":");
            userInputString = scanner.nextLine();

            if (!Pattern.compile("^(North)$|^(East)$|^(South)$|^(West)$|^[NSEW]$", Pattern.CASE_INSENSITIVE).matcher(userInputString).matches()){
                System.out.println("Input must only consist of direction 'North', 'East', 'South, or 'West'!");
                continue;
            }

            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        switch (userInputString.toUpperCase()){
            case "NORTH" -> startingPosition.append("N");
            case "N" -> startingPosition.append("N");
            case "SOUTH" -> startingPosition.append("S");
            case "S" -> startingPosition.append("S");
            case "EAST" -> startingPosition.append("E");
            case "E" -> startingPosition.append("E");
            case "WEST" -> startingPosition.append("W");
            case "W" -> startingPosition.append("W");
        }

        return PositionParser.parse(startingPosition.toString());
    }

    public Instruction[] getInstructions(String roverName){
        String userInputString;
        boolean shouldContinueLoop = true;
        do {
            System.out.println("Please enter instructions for rover " + roverName + ":");
            userInputString = scanner.nextLine();

            if (!userInputString.matches("^[LlRrMm]*$")){
                System.out.println("Input must only consist of letters 'L', 'R', or 'M'!");
                continue;
            }

            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        return InstructionParser.parse(userInputString);
    }

    public PlateauSize getPlateauSize (){
        int plateauX, plateauY;
        System.out.println("You will now define a size for the plateau the rovers will be placed on.");
        System.out.println("\nThis is for the horizontal size of the plateau.");
        plateauX = nextInteger(true);

        System.out.println("\nAnd this is for the vertical size of the plateau.");
        plateauY = nextInteger(true);

        return new PlateauSize(plateauX, plateauY);
    }

    public int nextInteger(boolean isPositive){
        int userInputInt = 0;
        boolean shouldContinueLoop = true;
        do {
            System.out.println("Please enter a " + (isPositive ? "positive integer:" : "integer:"));
            String userInputString = scanner.nextLine();

            try {
                userInputInt = Integer.parseInt(userInputString);
            } catch (NumberFormatException e) {
                System.out.println("Input must be a whole number only!");
                continue;
            }

            if (isPositive && userInputInt < 1){
                System.out.println("Input must be larger than 0!");
                continue;
            }

            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        return userInputInt;
    }

    public int nextIntBounded(int lowerBound, int upperBound) {
        assert lowerBound < upperBound : "nextIntBounded used with upper bound not being larger than lower bound.";

        int userInputInt = 0;
        boolean shouldContinueLoop = true;
        do {
            System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ":");
            String userInputString = scanner.nextLine();

            try {
                userInputInt = Integer.parseInt(userInputString);
            } catch (NumberFormatException e) {
                System.out.println("Input must be a whole number only!");
                continue;
            }

            if (userInputInt < lowerBound){
                System.out.println("Input must be larger than " + lowerBound + "!");
                continue;
            }

            if (userInputInt > upperBound){
                System.out.println("Input must be smaller than " + upperBound + "!");
                continue;
            }

            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        return userInputInt;
    }
}
