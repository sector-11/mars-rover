package Input;

public class PlateauSizeParser {
    public static PlateauSize parse(String input){
        if (input == null || input.isBlank()) return new PlateauSize(0,0);

        input = input.replaceAll("[^\\d\\s]", "");

        if (input.matches("\\d+\\s+\\d+")){
            input = input.replaceAll("\\s+", " ");
        } else {
            return new PlateauSize(0,0);
        }

        String[] inputArray = input.split(" ");

        return new PlateauSize(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]));
    }

}
