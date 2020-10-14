package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;

import java.io.Serializable;

public abstract class Weapon extends Item implements Usable {

    protected int statistic;

    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public int getStatistic() {
        return statistic;
    }

    @Override
    public void use(Player player) {
        player.setWeapon(this);
    }
}
