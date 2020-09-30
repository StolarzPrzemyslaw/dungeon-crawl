package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cow extends Person {

    public Cow(Cell cell) {
        super(cell);
        this.strength = 2;
        this.currentHealth = 5;
        this.name = "Cow";
    }

    public String getTileName() {
        return "cow";
    }
}
