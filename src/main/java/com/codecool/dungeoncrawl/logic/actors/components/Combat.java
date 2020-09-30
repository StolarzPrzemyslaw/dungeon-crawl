package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.*;

public class Combat {

    public void simulateCombat(Person player, Person enemy) {
        enemy.setHealth(enemy.getHealth() - player.getStrength());
        player.setHealth(player.getHealth() - enemy.getStrength());
        if (isEnemyDestroyed(player, enemy)) {
            destroyEnemy(player, enemy);
        }
    }

    private boolean isEnemyDestroyed(Person player, Person enemy) {
        return enemy.getHealth() <= player.getStrength();
    }

    public void destroyEnemy(Person player, Person enemy) {
        ((Enemy) enemy).runActionAfterDefeat((Player) player);
        enemy.getCell().setActor(null);
    }
}
