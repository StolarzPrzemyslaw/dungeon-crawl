package com.codecool.dungeoncrawl.logic;

public class Combat {

    public boolean simulateFight(Cell nextCell, Cell cell) {
        if (nextCell.getActor().getHealth() > cell.getActor().getPlayerStrength()) {
            nextCell.getActor().setHealth(nextCell.getActor().getHealth() - cell.getActor().getPlayerStrength());
            cell.getActor().setHealth(cell.getActor().getHealth() - nextCell.getActor().getPlayerStrength());
            return false;
        }
        return true;
    }
}
