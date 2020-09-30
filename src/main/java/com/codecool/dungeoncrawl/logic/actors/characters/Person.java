package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Person extends Actor {
    protected int health = 10;
    protected int strength = 5;
    protected Actor backgroundCellActor;

    public Person(Cell cell) {
        super(cell);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(backgroundCellActor);
        backgroundCellActor = nextCell.getActor();
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return this instanceof Player ? ((Player) this).getStrengthBasedOnWeapon() : strength;
    }

    public void setBackgroundCellActor(Actor backgroundCellActor) {
        this.backgroundCellActor = backgroundCellActor;
    }

    public Actor getBackgroundCellActor() {
        return backgroundCellActor;
    }
}
