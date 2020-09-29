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
        Cell nextCell = getNextCell(dx, dy);
        if (isNextFieldEmpty(nextCell)) {
            updatePosition(nextCell);
        }
    }

    protected Cell getNextCell(int dx, int dy) {
        return cell.getNeighbor(dx, dy);
    }

    protected boolean isNextFieldEmpty(Cell nextCell) {
        boolean isWallType = nextCell.getType() == CellType.WALL;
        boolean isEmptyType = nextCell.getType() == CellType.EMPTY;
        boolean isClosedDoor = nextCell.getActor() instanceof Door && !((Door) nextCell.getActor()).isOpen();
        return !isWallType && !isEmptyType && !isClosedDoor;
    }

    protected boolean isEncounterDone(Cell nextCell) {
        return combat.simulateFight(nextCell, cell);
    }

    protected void updatePosition(Cell nextCell) {
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
