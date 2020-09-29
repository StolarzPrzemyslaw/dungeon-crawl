package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Combat;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    public Combat combat = new Combat();
    protected int health = 10;
    protected int strength = 5;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!(nextCell.getType() == CellType.WALL) && !(nextCell.getType() == CellType.EMPTY)) {
            checkPossibleEncounter(nextCell);
        }
    }

    private void checkPossibleEncounter(Cell nextCell) {
        if ((!(nextCell.getActor() == null) && combat.simulateFight(nextCell, cell)) || nextCell.getActor() == null) {
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

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
