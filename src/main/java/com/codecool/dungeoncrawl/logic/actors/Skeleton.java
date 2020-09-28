package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    private final int skeletonStrength = 4;

    public Skeleton(Cell cell) {
        super(cell);
        setPlayerStrength(skeletonStrength);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
