package Input;

public class PlateauSize {
    private int x;
    private int y;

    public PlateauSize(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
