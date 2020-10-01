package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.view.Game;

public abstract class Enemy extends Person {

    public Enemy(Cell cell) {
        super(cell);
    }

    public abstract void runActionAfterDefeat(Game ui, Player player);
}
