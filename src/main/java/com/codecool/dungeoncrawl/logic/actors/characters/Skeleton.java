package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Character;

public class Skeleton extends Character {
    public Skeleton(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "skeleton";
    }
}
