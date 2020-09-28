package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knife extends Weapon {

    private final int statistic = 3;

    public Knife(Cell cell) {
        super(cell);
        this.name = "Knife +3";
        this.obtainMessage = "You have obtained a knife!";
    }

    public String getTileName() {
        return "weapon";
    }
}
