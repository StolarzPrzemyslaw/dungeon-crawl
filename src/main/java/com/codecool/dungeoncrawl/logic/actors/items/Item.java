package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Item extends Actor {

    protected String obtainMessage;

    public Item(Cell cell) {
        super(cell);
    }

    public String getObtainMessage() {
        return this.obtainMessage;
    }

}
