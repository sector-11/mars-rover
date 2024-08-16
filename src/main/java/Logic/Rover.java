package Logic;

import Input.Directions;
import Input.Instruction;
import Input.Position;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Rover {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    private Position position;
    public final int id;
    private Queue<Instruction> instructionsQueue;
    private Plateau onThisPlateau;

    public Rover(Position position){
        this.position = position;
        this.id = NEXT_ID.getAndIncrement();
        this.instructionsQueue = new ArrayDeque<Instruction>(10);
    }

    public Rover(Position position, Plateau plateau){
        this.position = position;
        this.id = NEXT_ID.getAndIncrement();
        this.instructionsQueue = new ArrayDeque<Instruction>(10);
        this.onThisPlateau = plateau;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPlateau(Plateau plateau){
        this.onThisPlateau = plateau;
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

    public boolean giveInstructions(Instruction[] input){
        if (input == null || input.length < 1) return false;
        return instructionsQueue.addAll(Arrays.asList(input));
    }

    public void executeInstructions(int numberToExecute){
        numberToExecute = (numberToExecute < 0 || numberToExecute > instructionsQueue.size()) ? instructionsQueue.size() : numberToExecute;
        Instruction currentInstruction;
        for (int i = 0; i < numberToExecute; i++) {
            currentInstruction = instructionsQueue.poll();
            switch (currentInstruction){
                case L, R -> this.rotate(currentInstruction);
                case M -> onThisPlateau.moveEntity(this);
                case null -> {/*do nothing*/}
            }
        }
    }
}
