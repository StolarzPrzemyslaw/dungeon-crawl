package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Person extends Actor {
    protected int health;
    protected int currentHealth;
    protected int strength;
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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setBackgroundCellActor(Actor backgroundCellActor) {
        this.backgroundCellActor = backgroundCellActor;
    }

    public Actor getBackgroundCellActor() {
        return backgroundCellActor;
    }
}
