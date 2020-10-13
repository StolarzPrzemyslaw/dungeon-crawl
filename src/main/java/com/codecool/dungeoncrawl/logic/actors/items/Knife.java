package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knife extends Weapon implements Usable {

    public Knife(Cell cell) {
        super(cell);
        this.statistic = 3;
        this.name = "Knife";
    }

    public String getTileName() {
        return "knife";
    }
}
