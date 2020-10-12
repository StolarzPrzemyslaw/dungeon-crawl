package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.view.Game;

public class Skeleton extends Enemy {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        this.health = 10;
        this.currentHealth = this.health;
        this.name = "Skeleton";
    }

    public void runActionAfterDefeat(Game ui, Player player) {
        ui.displayLog("You defeated skeleton!");
    }

    public String getTileName() {
        return "skeleton";
    }
}
