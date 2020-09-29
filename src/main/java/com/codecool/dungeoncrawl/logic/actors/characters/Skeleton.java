package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Character;

public class Skeleton extends Character {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        setStrength(strength);
    }

    public String getTileName() {
        return "skeleton";
    }
}
