package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cow extends Person {

    public Cow(Cell cell) {
        super(cell);
        this.strength = 2;
        this.health = 5;
        this.name = "Cow";
    }

    public String getTileName() {
        return "cow";
    }
}
