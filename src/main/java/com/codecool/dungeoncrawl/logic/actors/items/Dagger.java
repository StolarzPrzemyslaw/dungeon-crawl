package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Dagger extends Weapon implements Usable {
    public Dagger(Cell cell) {
        super(cell);
        this.statistic = 0;
        this.name = "Basic dagger";
    }

    @Override
    public String getTileName() {
        return "dagger";
    }
}
