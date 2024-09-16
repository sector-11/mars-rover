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
    Controller controller;

    public void run(){
        System.out.println("   \\  |                     _ \\                       ");
        System.out.println("  |\\/ |   _` |   _| (_-<      /   _ \\ \\ \\ /  -_)   _| ");
        System.out.println(" _|  _| \\__,_| _|   ___/   _|_\\ \\___/  \\_/ \\___| _|   ");

        while (true){
            mainMenu();
        }
    }

    public void mainMenu(){
        System.out.println("\n---------");
        System.out.println("Main Menu");
        System.out.println("---------\n");
        System.out.println("1) Start");
        System.out.println("2) Demo");
        System.out.println("3) Quit");
        switch (nextIntBounded(1,3)){
            case 1 -> customStart();
            case 2 -> demoStart();
            case 3 -> {
                System.out.println("Thank you for using this program.");
                System.exit(0);
            }
        }
    }

    public void demoStart(){
        PlateauSize size = new PlateauSize(5,5);
        ArrayList<InputRover> rovers = new ArrayList<>();
        rovers.add(new InputRover(
                new Position(1, 2, Directions.N),
                InstructionParser.parse("LMLMLMLMM"),
                "Curiosity"));
        rovers.add(new InputRover(
                new Position(3, 3, Directions.E),
                InstructionParser.parse("MMRMMRMRRM"),
                "Opportunity"));

        controller = new Controller(size, rovers);
        System.out.println("\nStarting Positions:");
        controller.outputAll();

        controller.executeAll();
        System.out.println("\nEnding Positions:");
        controller.outputAll();

        System.out.println("\nPress enter to continue.");
        scanner.nextLine();
    }

    public void customStart(){
        ArrayList<InputRover> rovers = new ArrayList<>();

        PlateauSize plateauSize = getPlateauSize();

        System.out.println("\nHow many rovers do you want to add? (1 rover minimum)");
        int roverAmount = nextInteger(true);

        Optional<ArrayList<String>> namesList;
        System.out.println("\nDo you want to give your rovers names? Y/N:");
        if (yesOrNo()){
            namesList = roverNames(roverAmount);
        } else {
            namesList = Optional.empty();
        }

        for (int i = 0; i < roverAmount; i++) {
            String roverName = namesList.isPresent() ? namesList.get().get(i) : Integer.toString(i + 1);
            Position roverPosition = startingPosition(roverName, plateauSize);

            System.out.println("Instructions are given as a string of letters where 'L' and 'R' are left/right turns, and 'M' is moving one space forward.");
            Instruction[] roverInstructions = getInstructions(roverName);

            rovers.add(new InputRover(roverPosition, roverInstructions, roverName));
        }

        controller = new Controller(plateauSize, rovers);
        System.out.println("\nStarting Positions:");
        controller.outputAll();

        controller.executeAll();
        System.out.println("\nEnding Positions:");
        controller.outputAll();

        System.out.println("\nPress enter to continue.");
        scanner.nextLine();
    }

    public Optional<ArrayList<String>> roverNames(int roverAmount){
        if (roverAmount == 0) return Optional.empty();
        ArrayList<String> nameList = new ArrayList<>();
        String currentName;

        for (int i = 0; i < roverAmount; i++) {
            boolean shouldContinueLoop = true;
            do {
                System.out.println("\nPlease enter a name for rover " + (i + 1) + ":");
                currentName = scanner.nextLine();

                System.out.println("\nIs the name '" + currentName + "' ok? Y/N:");
                shouldContinueLoop = !yesOrNo();
            } while (shouldContinueLoop);

            nameList.add(currentName);
        }
        return Optional.of(nameList);
    }

    public boolean yesOrNo(){
        String userInputString;
        boolean shouldContinueLoop = true;
        do {
            userInputString = scanner.nextLine();

            if (!Pattern.compile("^(Yes)$|^(No)$|^[YN]$", Pattern.CASE_INSENSITIVE).matcher(userInputString).matches()){
                System.out.println("Input must be 'Yes' or 'No'!");
                continue;
            }
            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        switch (userInputString.toUpperCase()){
            case "YES", "Y" -> {return true;}
            case "NO", "N" -> {return false;}
        }

        return false;
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
            System.out.println("Please enter initial cardinal direction for rover " + roverName + ":");
            userInputString = scanner.nextLine();

            if (!Pattern.compile("^(North)$|^(East)$|^(South)$|^(West)$|^[NSEW]$", Pattern.CASE_INSENSITIVE).matcher(userInputString).matches()){
                System.out.println("Input must only consist of direction 'North', 'East', 'South, or 'West'!");
                continue;
            }

            shouldContinueLoop = false;
        } while (shouldContinueLoop);

        switch (userInputString.toUpperCase()){
            case "NORTH", "N" -> startingPosition.append("N");
            case "SOUTH", "S" -> startingPosition.append("S");
            case "EAST", "E" -> startingPosition.append("E");
            case "WEST", "W" -> startingPosition.append("W");
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
