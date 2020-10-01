package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.view.Game;

public class BridgeGuardian extends Enemy {

    public BridgeGuardian(Cell cell) {
        super(cell);
        this.strength = 3;
        this.health = 30;
        this.currentHealth = this.health;
        this.name = "Bridge Guardian";
    }

    public void runActionAfterDefeat(Game ui, Player player) {
        ui.displayLog("You defeated guardian! You get additional 5 strength points!");
        player.increaseBaseStrength(5);
    }

    @Override
    public String getTileName() {
        return "guardian";
    }
}
