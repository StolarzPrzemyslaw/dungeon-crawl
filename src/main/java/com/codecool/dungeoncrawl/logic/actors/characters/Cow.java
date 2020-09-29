package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cow extends Person {

    private final int healthPointsRestoredAfterDefeat = 10;

    public Cow(Cell cell) {
        super(cell);
        this.strength = 2;
        this.health = 5;
        this.name = "Cow";
    }

    public String getTileName() {
        return "cow";
    }

    @Override
    public void actionAfterDefeat(Actor actorWhichDefeatedCow) {
        // show message after defeat
        Player player = (Player) actorWhichDefeatedCow;
        player.setHealth(player.getHealth() + healthPointsRestoredAfterDefeat);
    }
}
