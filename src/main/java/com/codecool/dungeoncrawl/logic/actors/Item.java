package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Item extends Actor {

    protected String obtainMessage;

    public Item(Cell cell) {
        super(cell);
    }

    public String getObtainMessage() {
        return this.obtainMessage;
    }

}
