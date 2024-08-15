package Input;

import java.util.InputMismatchException;

public class PositionParser {
    public static Position parse(String input){
        if (input == null || input.isBlank()) return null;
        input = input.replaceAll("\\s+", " ");
        if (!input.matches("\\d+ \\d+ [NSEWnsew]")) return null;

        String[] inputArray = input.split(" ");

        Directions facing = switch (inputArray[2].toUpperCase()) {
            case "N" -> Directions.N;
            case "S" -> Directions.S;
            case "E" -> Directions.E;
            case "W" -> Directions.W;
            default -> throw new InputMismatchException("Bad direction made it through check! Shutting down!");
        };

        return new Position(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]), facing);
    }
}
