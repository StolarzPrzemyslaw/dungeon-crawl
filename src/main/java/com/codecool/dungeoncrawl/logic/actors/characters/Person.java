package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;

public abstract class Person extends Actor {
    protected int health = 10;

    public Person(Cell cell) {
        super(cell);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        boolean isWall = nextCell.getType() == CellType.WALL;
        boolean isEmpty = nextCell.getType() == CellType.EMPTY;
        boolean isClosedDoor = nextCell.getActor() instanceof Door && !((Door) nextCell.getActor()).isOpen();
        if (!isWall && !isEmpty && !isClosedDoor) {
            checkPossibleEncounter(nextCell);
        }
    }

    private void checkPossibleEncounter(Cell nextCell) {
        if (nextCell.getActor() == null || combat.simulateFight(nextCell, cell)) {
            updatePosition(nextCell);
        }
    }

    private void updatePosition(Cell nextCell) {
        cell.setActor(null);
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
        if (this instanceof Player) {
            return ((Player) this).getStrengthBasedOnWeapon();
        }
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public abstract void actionAfterDefeat(Actor actorWhichDefeatedCow);
}
