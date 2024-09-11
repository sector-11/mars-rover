import java.util.Scanner;

public class ConsoleIOHandler {
    private final String PLATEAUSIZE_REGEX = "^\\d+ \\d+$";
    private final String POSITION_REGEX = "^\\d+ \\d+ [NEWSnews]$";
    private final String INSTRUCTION_REGEX = "^[LRMlrm]+$";

    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

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
