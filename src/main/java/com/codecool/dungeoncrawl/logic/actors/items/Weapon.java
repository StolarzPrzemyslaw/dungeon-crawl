package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Item;

public abstract class Weapon extends Item {

    protected int statistic;

    public Weapon(Cell cell) {
        super(cell);
    }
}
