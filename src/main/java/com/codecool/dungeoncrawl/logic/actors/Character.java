package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Character extends Actor {
    protected int health = 10;

    public Character(Cell cell) {
        super(cell);
    }

    public void setHealth(int value) {
        this.health = value;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public void getItemFromTheFloor(Item obtainedItem) {
        // TO DO
        System.out.println(obtainedItem.getObtainMessage());
    }

    public int getHealth() {
        return health;
    }
}
