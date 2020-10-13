package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.view.Game;

public abstract class Item extends Actor {

    public Item(Cell cell) {
        super(cell);
    }

    public int getStatistic() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void showObtainMessage(Game ui) {
        ui.displayLog("You have obtained a " + getName());
    }
}
