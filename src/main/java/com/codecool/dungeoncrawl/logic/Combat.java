package com.codecool.dungeoncrawl.logic;

public class Combat {

    public boolean simulateFight(Cell nextCell, Cell cell) {
        if (nextCell.getActor().getHealth() > cell.getActor().getStrength()) {
            nextCell.getActor().setHealth(nextCell.getActor().getHealth() - cell.getActor().getStrength());
            cell.getActor().setHealth(cell.getActor().getHealth() - nextCell.getActor().getStrength());
            return false;
        }
        return true;
    }
}
