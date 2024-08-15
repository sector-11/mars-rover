package Input;

public class Position {
    private int x;
    private int y;
    private Directions facing;

    public Position(int x, int y, Directions facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + ". facing: " + facing.name() + "]";
    }
}
