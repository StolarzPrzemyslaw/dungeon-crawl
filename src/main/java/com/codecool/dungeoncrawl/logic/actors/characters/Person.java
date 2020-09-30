package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.components.Combat;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;

public abstract class Person extends Actor {
    protected int health = 10;
    protected int strength = 5;
    protected Combat combat = new Combat();
    protected Actor backgroundCellActor;

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
        boolean isClosedDoor = isNextFieldClosedDoor(nextCell);
        return !isWallType && !isEmptyType && !isClosedDoor;
    }

    protected boolean isEncounterDone(Cell nextCell) {
        return combat.simulateFight(nextCell, cell);
    }

    protected void updatePosition(Cell nextCell) {
        cell.setActor(backgroundCellActor);
        backgroundCellActor = nextCell.getActor();
        nextCell.setActor(this);
        cell = nextCell;
    }

    protected boolean isNextFieldClosedDoor(Cell nextCell) {
        return nextCell.getActor() instanceof Door && !((Door) nextCell.getActor()).isOpen();
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


    public void setBackgroundCellActor(Actor backgroundCellActor) {
        this.backgroundCellActor = backgroundCellActor;
    }

    public Actor getBackgroundCellActor() {
        return backgroundCellActor;
    }

    public abstract void actionAfterDefeat(Actor actorWhichDefeatedCow);
}
