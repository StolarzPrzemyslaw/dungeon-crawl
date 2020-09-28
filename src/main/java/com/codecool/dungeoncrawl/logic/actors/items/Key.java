package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Key extends Item {

    private boolean used = false;

    public Key(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "key";
    }
}
