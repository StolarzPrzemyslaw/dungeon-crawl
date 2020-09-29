package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Weapon extends Item {

    protected int statistic;

    public Weapon(Cell cell) {
        super(cell);
    }

    public int getStatistic() {
        return statistic;
    }
}
