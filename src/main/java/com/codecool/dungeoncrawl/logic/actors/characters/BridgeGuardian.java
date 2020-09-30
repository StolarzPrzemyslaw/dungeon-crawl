package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;

public class BridgeGuardian extends Enemy {

    public BridgeGuardian(Cell cell) {
        super(cell);
        this.strength = 3;
        this.health = 20;
        this.name = "Bridge Guardian";
    }

    public void runActionAfterDefeat(Player player) {
        player.increaseBaseStrength(10);
    }

    @Override
    public String getTileName() {
        return "guardian";
    }
}
