package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Character;

public class Player extends Character {

    public Player(Cell cell) {
        super(cell);
        this.strength = 5;
        setStrength(strength);
    }

    public String getTileName() {
        return "player";
    }

}
