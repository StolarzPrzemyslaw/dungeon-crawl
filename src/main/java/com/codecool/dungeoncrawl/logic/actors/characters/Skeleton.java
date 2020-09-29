package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Skeleton extends Person {

    public Skeleton(Cell cell) {
        super(cell);
        this.strength = 4;
        this.health = 10;
        this.name = "Skeleton";
    }

    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void actionAfterDefeat(Actor actorWhichDefeatedSkeleton) {
        // show message after defeat
    }
}
