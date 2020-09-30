package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;

public class Potion extends Item  {
    protected final int healthRestored;

    public Potion(Cell cell) {
        super(cell);
        healthRestored = 5;
        name = "Small potion";
    }

    @Override
    public String getTileName() {
        return "potion";
    }

    public void consume(Player player) {
        player.healUp(healthRestored);
        player.getInventory().removeItemFromInventory(this);
    }
}
