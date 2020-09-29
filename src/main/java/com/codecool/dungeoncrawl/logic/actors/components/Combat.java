package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.characters.Person;

public class Combat {

    public boolean simulateFight(Cell nextCell, Cell cell) {
        if (cell.getActor() instanceof Person && nextCell.getActor() instanceof Person) {
            Person player = (Person) cell.getActor();
            Person enemy = (Person) nextCell.getActor();

            if (enemy.getHealth() > player.getStrength()) {
                enemy.setHealth(enemy.getHealth() - player.getStrength());
                player.setHealth(player.getHealth() - enemy.getStrength());
                return false;
            }

            enemy.actionAfterDefeat(player);
            nextCell.setActor(null);
            return true;
        }

        return true;
    }
}
