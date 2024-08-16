package Logic;

import Input.Directions;
import Input.PlateauSize;
import Input.Position;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Plateau {
    PlateauSize size;
    ArrayList<Rover> entities;

    public Plateau(PlateauSize size) {
        this.size = size;
        this.entities = new ArrayList<>();
    }

    public boolean addEntity(Rover rover){
        if (rover == null) return false;
        if (!isWithinBounds.test(rover)) return false;

        for (Rover entity : entities) {
            if (entity.getPosition().getCoOrdinates().equals(rover.getPosition().getCoOrdinates())) return false;
        }

        return entities.add(rover);
    }

    private final Predicate<Rover> isWithinBounds = (rover) -> {
        boolean isWithinBounds;
        if (rover.getPosition().getX() > this.size.getX() || rover.getPosition().getX() < 0) {
            isWithinBounds = false;
        } else if ((rover.getPosition().getY() > this.size.getY() || rover.getPosition().getY() < 0)) {
            isWithinBounds = false;
        } else {
            isWithinBounds = true;
        }
        return isWithinBounds;
    };

    public boolean moveEntity(Rover rover){
        if (rover == null) return false;

        Rover entityToRemove = null;

        for (Rover entity : entities) {
            if (rover.id == entity.id){
                entityToRemove = entity;
            }
        }

        if (entityToRemove == null) return false;
        Position originalPosition = rover.getPosition();
        int x = rover.getPosition().getX();
        int y = rover.getPosition().getY();

        switch (rover.getPosition().getFacing()){
            case N -> y++;
            case S -> y--;
            case E -> x++;
            case W -> x--;
        }

        rover.setPosition(new Position(x, y, rover.getPosition().getFacing()));

        boolean couldRemove =  entities.remove(entityToRemove);
        boolean couldAdd = false;

        if (couldRemove){
            couldAdd = this.addEntity(rover);
        }

        if (!couldAdd){
            entities.add(entityToRemove);
            rover.setPosition(originalPosition);
        }

        return couldAdd;
    }
}
