package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knife extends Weapon {

    public Knife(Cell cell) {
        super(cell);
        this.statistic = 3;
        this.name = "Knife +3";
        this.obtainMessage = "You have obtained a knife!";
    }

    public String getTileName() {
        return "weapon";
    }
}
