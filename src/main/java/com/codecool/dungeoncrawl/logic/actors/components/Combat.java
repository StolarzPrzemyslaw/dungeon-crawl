package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.characters.Skeleton;

public class Combat {

    public boolean simulateFight(Cell nextCell, Cell cell) {
        if (cell.getActor() instanceof Player && nextCell.getActor() instanceof Skeleton) {
            Player player = (Player) cell.getActor();
            Skeleton enemy = (Skeleton) nextCell.getActor();

            if (enemy.getHealth() > player.getStrength()) {
                enemy.setHealth(enemy.getHealth() - player.getStrength());
                player.setHealth(player.getHealth() - enemy.getStrength());
                return false;
            }
            return true;
        }

        return true;
    }
}
