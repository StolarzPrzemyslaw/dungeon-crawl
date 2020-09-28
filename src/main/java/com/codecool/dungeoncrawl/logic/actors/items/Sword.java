package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Sword extends Item {

    private int statistic = 10;

    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "item";
    }
}
