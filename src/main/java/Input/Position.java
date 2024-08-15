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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Directions getFacing() {
        return facing;
    }

    public void setFacing(Directions facing) {
        this.facing = facing;
    }
}
