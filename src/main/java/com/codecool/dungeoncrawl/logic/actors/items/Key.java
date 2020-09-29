package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public class Key extends Item {

    private boolean used = false;

    public Key(Cell cell) {
        super(cell);
        this.name = "Key";
        this.obtainMessage = "You have obtained a key!";
    }

    public String getTileName() {
        return "key";
    }
}
