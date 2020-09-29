package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Obstacle extends Actor {
    public Obstacle(Cell cell) {
        super(cell);
    }
}
