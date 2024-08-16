package Logic;

import Input.Directions;
import Input.Instruction;
import Input.Position;

import java.util.concurrent.atomic.AtomicInteger;

public class Rover {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    private Position position;
    public final int id;

    public Rover(Position position){
        this.position = position;
        this.id = NEXT_ID.getAndIncrement();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public Directions rotate(Instruction instruction){
        if (instruction == Instruction.L) {
            switch (this.position.getFacing()){
                case Directions.N -> this.position.setFacing(Directions.W);
                case Directions.W -> this.position.setFacing(Directions.S);
                case Directions.S -> this.position.setFacing(Directions.E);
                case Directions.E -> this.position.setFacing(Directions.N);
            }
        } else if (instruction == Instruction.R) {
            switch (this.position.getFacing()){
                case Directions.N -> this.position.setFacing(Directions.E);
                case Directions.E -> this.position.setFacing(Directions.S);
                case Directions.S -> this.position.setFacing(Directions.W);
                case Directions.W -> this.position.setFacing(Directions.N);
            }
        }

        return this.position.getFacing();
    }
}
