package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Character {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        this.health = 10;
        this.name = "Skeleton";
    }

    public String getTileName() {
        return "skeleton";
    }
}
