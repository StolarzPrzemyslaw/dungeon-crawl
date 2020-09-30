package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Axe extends Weapon {

    public Axe(Cell cell) {
        super(cell);
        this.statistic = 7;
        this.name = "Axe +7";
        this.obtainMessage = "You have obtained a axe!";
    }

    public String getTileName() {
        return "weapon";
    }
}
