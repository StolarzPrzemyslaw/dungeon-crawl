package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Skeleton extends Enemy {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        this.health = 10;
        this.name = "Skeleton";
    }

    public void runActionAfterDefeat(Player player) {

    }

    public String getTileName() {
        return "skeleton";
    }
}
