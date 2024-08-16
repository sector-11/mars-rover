import Input.PlateauSize;
import Input.PlateauSizeParser;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIOHandler {
    private final String PLATEAUSIZE_REGEX = "^\\d+ \\d+$";
    private final String POSITION_REGEX = "^\\d+ \\d+ [NEWSnews]$";
    private final String INSTRUCTION_REGEX = "^[LRMlrm]+$";

    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void mainMenu(){
        while (true) {
            clearScreen();
            System.out.println("============\n MARS ROVER \n============");
            System.out.println("  1. CUSTOM INPUT");
            System.out.println("  2. DEFAULT INPUT");
            System.out.println("  3. EXIT\n");
            switch (boundedScan(1, 3, "SELECT OPTION: ")) {
                case 1 -> {
                    ArrayList<String> userInput = customInput();
                    controller.initializeAll(userInput);
                    System.out.println("STARTING POSITIONS: ");
                    controller.outputAll();
                    controller.executeAll();
                    System.out.println("ENDING POSITIONS: ");
                    controller.outputAll();
                    System.out.println("PLEASE PRESS ENTER TO RETURN TO MENU");
                    scanner.nextLine();
                }
                case 2 -> {
                    clearScreen();
                    controller.defaultRun();
                    System.out.println("PLEASE PRESS ENTER TO RETURN TO MENU");
                    scanner.nextLine();
                }
                case 3 -> exit();
            }
        }
    }

    public ArrayList<String> customInput(){
        clearScreen();
        ArrayList<String> userInput = new ArrayList<>();

        System.out.println("FIRST, INPUT THE SIZE OF THE PLATEAU");
        System.out.println("THIS SHOULD BE FORMATTED AS \"[X AXIS SIZE] [Y AXIS SIZE]\"");
        userInput.add(regexScan(PLATEAUSIZE_REGEX, "INPUT: ", "PLEASE ENTER IN FORMAT \"[X AXIS SIZE] [Y AXIS SIZE]\""));
        PlateauSize givenSize = PlateauSizeParser.parse(userInput.getFirst());

        System.out.println("\nNOW, WE WILL CREATE A ROVER.");
        userInput.add(positionScan(givenSize));

        System.out.println("\nNEXT, WE MUST GIVE THIS ROVER INSTRUCTIONS");
        userInput.add(instructionScan());

        boolean userWantsMoreRovers = false;
        while (!userWantsMoreRovers){
            int roverCount = 2;
            System.out.println("WOULD YOU LIKE TO ADD ANOTHER ROVER? Y/N");
            if (regexScan("[YNyn]", "", "INPUT Y OR N ONLY!").equalsIgnoreCase("N")){
                userWantsMoreRovers = true;
            } else {
                System.out.println("\nWHERE SHOULD ROVER " + roverCount + " LAND?");
                System.out.println("PLEASE NOTE THAT ROVERS LANDING IN THE SAME PLACE AS ANOTHER WILL BE DESTROYED ON LANDING!");
                userInput.add(positionScan(givenSize));

                System.out.println("\nNEXT, GIVE ROVER " + roverCount + " INSTRUCTIONS.");
                userInput.add(instructionScan());
            }
        }

        return userInput;
    }

    public void exit(){
        System.out.println("\nTHANK YOU FOR USING THIS PROGRAM");
        System.exit(0);
    }

    public String positionScan(PlateauSize givenSize){
        StringBuilder stringToReturnBuilder = new StringBuilder();
        String stringToReturn;

        System.out.println("\nINPUT AN X CO-ORDINATE FOR YOUR LANDING SITE");
        stringToReturnBuilder.append(boundedScan(0, givenSize.getX(), "ENTER A NUMBER BETWEEN 0 and " + givenSize.getX() + ": "));

        stringToReturnBuilder.append(" ");

        System.out.println("\nNOW, INPUT A Y CO-ORDINATE FOR YOUR LANDING SITE");
        stringToReturnBuilder.append(boundedScan(0, givenSize.getY(), "ENTER A NUMBER BETWEEN 0 and " + givenSize.getY() + ": "));

        stringToReturnBuilder.append(" ");

        System.out.println("\nFINALLY, WHICH DIRECTION SHOULD YOUR ROVER FACE WHEN LANDED");
        System.out.println("INPUT N FOR NORTH, E FOR EAST, S FOR SOUTH, OR W FOR WEST");
        stringToReturnBuilder.append(regexScan("[NEWSnews]", "INPUT: ","INPUT N FOR NORTH, E FOR EAST, S FOR SOUTH, OR W FOR WEST"));

        stringToReturn = stringToReturnBuilder.toString();

        if (stringToReturn.matches(POSITION_REGEX)){
            return stringToReturn;
        } else {
            System.out.println("SOMETHING WENT SERIOUSLY WRONG, LET'S TRY AGAIN!");
            return positionScan(givenSize);
        }
    }

    public String instructionScan(){
        StringBuilder stringToReturnBuilder = new StringBuilder();
        String stringToReturn;
        String currentInput;
        boolean userWantsToStop = false;

        System.out.println("ENTER L FOR A LEFT TURN, R FOR A RIGHT TURN, M TO MOVE FORWARDS, OR Q TO QUIT");

        while (!userWantsToStop){
            currentInput = regexScan("[LRMlrmQq]", "NEXT INPUT: ", "ENTER L FOR A LEFT TURN, R FOR A RIGHT TURN, M TO MOVE FORWARDS, OR Q TO QUIT");
            if (currentInput.toUpperCase().equals("Q")){
                userWantsToStop = true;
            } else {
                stringToReturnBuilder.append(currentInput);
            }
        }

        stringToReturn = stringToReturnBuilder.toString();

        System.out.println("TO CONFIRM, YOU WANT TO DO THE FOLLOWING: ");
        System.out.println(stringToReturn);
        System.out.println("Y/N: ");
        if (regexScan("[YNyn]", "", "INPUT Y OR N ONLY!").equalsIgnoreCase("Y")){
            //do nothing
        } else {
            System.out.println("RESETTING INPUT!");
            stringToReturn = instructionScan();
        }

        if (stringToReturn.isEmpty()){
            System.out.println("YOU MUST ENTER SOME INSTRUCTIONS!");
        }

        if (stringToReturn.matches(INSTRUCTION_REGEX)){
            return stringToReturn;
        } else {
            System.out.println("SOMETHING WENT SERIOUSLY WRONG, LET'S TRY AGAIN!");
            return instructionScan();
        }
    }

    public String regexScan(String regex, String message, String errorMessage){
        String userInput = "";
        while (!userInput.matches(regex)){

            System.out.printf(message);

            try {
                userInput = scanner.nextLine();
            } catch (Exception ignored) {
                userInput = "";
                clearScanner();
                System.out.printf("NUMBERS ONLY! ");
            }

            if (!userInput.matches(regex)){
                System.out.println("INVALID INPUT!");
                System.out.println(errorMessage);
            }
        }

        clearScanner();
        return userInput;
    }


    public int boundedScan(int lowerBound, int upperBound, String message){
        int userInput = Integer.MIN_VALUE;

        while (!(userInput >= lowerBound && userInput <= upperBound)){

            System.out.printf(message);

            try {
                userInput = scanner.nextInt();
            } catch (Exception ignored) {
                userInput = Integer.MIN_VALUE;
                clearScanner();
                System.out.printf("NUMBERS ONLY! ");
            }

            if (!(userInput >= lowerBound && userInput <= upperBound)){
                System.out.println("INVALID INPUT!");
                System.out.println("PLEASE ENTER A NUMBER BETWEEN " + lowerBound + " AND " + upperBound);
            }
        }

        clearScanner();
        return userInput;
    }

    public void clearScreen(){
        System.out.printf("\n\n\n\n");
        System.out.flush();
    }

    public void clearScanner(){
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
