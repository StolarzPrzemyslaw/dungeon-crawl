package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        this.health = 10;
        this.currentHealth = this.health;
        this.name = "Skeleton";
    }

    public void runActionAfterDefeat(Player player) {

    }

    public String getTileName() {
        return "skeleton";
    }
}
