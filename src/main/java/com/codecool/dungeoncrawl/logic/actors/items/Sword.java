package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Weapon {

    private final int statistic = 10;

    public Sword(Cell cell) {
        super(cell);
        this.name = "Sword +10";
        this.obtainMessage = "You have obtained a sword!";
    }

    public String getTileName() {
        return "weapon";
    }
}
