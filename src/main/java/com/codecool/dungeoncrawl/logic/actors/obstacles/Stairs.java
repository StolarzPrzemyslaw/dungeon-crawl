package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Stairs extends Obstacle {

    public Stairs(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "stairs";
    }
}
