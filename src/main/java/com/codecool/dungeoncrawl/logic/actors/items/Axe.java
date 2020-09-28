package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Axe extends Item {

    private final int statistic = 7;

    public Axe(Cell cell) {
        super(cell);
        this.name = "Axe +7";
        this.obtainMessage = "You have obtained a axe!";
    }

    public String getTileName() {
        return "weapon";
    }
}
