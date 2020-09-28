package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {

    private int playerStrength = 5;

    public Player(Cell cell) {
        super(cell);
        setPlayerStrength(playerStrength);
    }

    public String getTileName() {
        return "player";
    }

}
