package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Item {

    private State state;

    private enum State {
        OPEN,
        CLOSED
    }

    public Door(Cell cell) {
        super(cell);
        name = "Door";
        state = State.CLOSED;
    }

    @Override
    public String getTileName() {
        return "door";
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isOpen() {
        return state.equals(State.OPEN);
    }
}
