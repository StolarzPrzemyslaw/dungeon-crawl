package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Obstacle {

    private DoorState state;

    private enum DoorState {
        OPEN,
        CLOSED
    }

    public Door(Cell cell) {
        super(cell);
        name = "Door";
        state = DoorState.CLOSED;
    }

    @Override
    public String getTileName() {
        if (isOpen()) return "openDoor";
        return "closedDoor";
    }

    public void setState(DoorState state) {
        this.state = state;
    }

    public boolean isOpen() {
        return state.equals(DoorState.OPEN);
    }
}
