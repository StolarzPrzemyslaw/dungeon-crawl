package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.characters.Person;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.view.Game;

public class Combat {

    Game ui;

    public Combat(Game game) {
        this.ui = game;
    }

    public void simulateCombat(Person player, Person enemy) {
        enemy.setCurrentHealth(enemy.getCurrentHealth() - player.getStrength());
        setPersonDeadStatus(player, enemy);
        if (ui != null) {
            ui.displayLog(player.getName() + " get " + enemy.getStrength() + " damage!");
        }
        player.setCurrentHealth(player.getCurrentHealth() - enemy.getStrength());
        setPersonDeadStatus(enemy, player);
        if (ui != null) {
            ui.displayLog(enemy.getName() + " get " + player.getStrength() + " damage!");
        }
    }

    private void setPersonDeadStatus(Person player, Person enemy) {
        if (isPersonDestroyed(player)) {
            player.setCurrentHealth(0);
            destroyPerson(enemy, player);
        }
    }

    private boolean isPersonDestroyed(Person enemy) {
        return enemy.getCurrentHealth() <= 0;
    }

    public void destroyPerson(Person player, Person enemy) {
        if (ui != null) {
            if (enemy instanceof Player) {
                ui.generateLoseScreen(player);
            } else {
                ((Enemy) enemy).runActionAfterDefeat(ui, (Player) player);
                enemy.getCell().setActor(null);
            }
        }
    }
}
