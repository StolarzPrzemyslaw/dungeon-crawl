package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.Person;

public class Combat {

    public void simulateCombat(Person player, Person enemy) {
        enemy.setHealth(enemy.getHealth() - player.getStrength());
        player.setHealth(player.getHealth() - enemy.getStrength());
        if (isEnemyDestroyed(player, enemy)) {
            destroyEnemy(enemy);
        }
    }

    private boolean isEnemyDestroyed(Person player, Person enemy) {
        return enemy.getHealth() <= player.getStrength();
    }

    public void destroyEnemy(Person enemy) {
        enemy.getCell().setActor(null);
    }
}
