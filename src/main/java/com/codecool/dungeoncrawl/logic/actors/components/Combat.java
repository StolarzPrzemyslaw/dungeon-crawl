package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.*;
import com.codecool.dungeoncrawl.view.Game;

public class Combat {

    Game ui;

    public Combat(Game game) {
        this.ui = game;
    }

    public void simulateCombat(Person player, Person enemy) {
        enemy.setCurrentHealth(enemy.getCurrentHealth() - player.getStrength());
        player.setCurrentHealth(player.getCurrentHealth() - enemy.getStrength());
        ui.displayLog("You get " + enemy.getStrength() + " damage and Enemy get " + player.getStrength() + " damage!");
        if (isEnemyDestroyed(enemy)) {
            destroyEnemy(player, enemy);
        }
    }

    private boolean isEnemyDestroyed(Person enemy) {
        return enemy.getCurrentHealth() <= 0;
    }

    public void destroyEnemy(Person player, Person enemy) {
        ((Enemy) enemy).runActionAfterDefeat(ui, (Player) player);
        enemy.getCell().setActor(null);
    }
}
