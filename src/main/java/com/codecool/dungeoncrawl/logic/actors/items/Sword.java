package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Sword extends Item {

    private final int statistic = 10;

    public Sword(Cell cell) {
        super(cell);
        this.name = "Sword +10";
        this.obtainMessage = "You have obtained a sword!";
    }

    public String getTileName() {
        return "item";
    }
}
