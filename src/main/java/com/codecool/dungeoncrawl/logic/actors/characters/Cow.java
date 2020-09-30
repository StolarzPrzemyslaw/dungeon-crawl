package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cow extends Enemy {

    private boolean isMovingLeft = true;
    private int stepLeft = 6;

    public Cow(Cell cell) {
        super(cell);
        this.strength = 0;
        this.health = 6;
        this.name = "Cow";
    }

    public void runActionAfterDefeat(Player player) {
        player.healUp(20);
    }

    public void resetStepsLeft() {
        this.stepLeft = 6;
    }

    public void decreaseStepsLeft() {
        this.stepLeft -= 1;
    }

    public int getStepsLeft() {
        return this.stepLeft;
    }

    public boolean isMovingLeft() {
        return this.isMovingLeft;
    }

    public void swapDirectionOfMoving() {
        this.isMovingLeft  = !this.isMovingLeft;
    }

    public String getTileName() {
        return "cow";
    }
}
