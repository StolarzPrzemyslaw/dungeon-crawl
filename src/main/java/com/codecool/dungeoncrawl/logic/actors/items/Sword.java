package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Weapon implements Usable {

    public Sword(Cell cell) {
        super(cell);
        this.statistic = 10;
        this.name = "Sword";
    }

    public String getTileName() {
        return "sword";
    }
}
