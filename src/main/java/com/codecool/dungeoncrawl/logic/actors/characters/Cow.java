package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cow extends Enemy {

    public Cow(Cell cell) {
        super(cell);
        this.strength = 0;
        this.health = 6;
        this.name = "Cow";
    }

    public void runActionAfterDefeat(Player player) {
        player.healUp(20);
    }

    public String getTileName() {
        return "cow";
    }
}
