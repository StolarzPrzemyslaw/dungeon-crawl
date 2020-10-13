package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Axe extends Weapon implements Usable {

    public Axe(Cell cell) {
        super(cell);
        this.statistic = 7;
        this.name = "Axe";
    }

    public String getTileName() {
        return "axe";
    }
}
