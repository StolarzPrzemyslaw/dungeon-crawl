package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Knife extends Item {

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
