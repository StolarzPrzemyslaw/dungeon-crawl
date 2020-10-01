package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;

public class Player extends Person {

    private final Inventory inventory;
    private Weapon chosenWeapon;

    public Player(Cell cell) {
        super(cell);
        this.name = "Hero Name";
        this.strength = 5;
        this.health = 20;
        this.currentHealth = this.health;
        this.inventory = new Inventory();
    }

    public Player(Cell cell, Inventory inventory) {
        super(cell);
        this.inventory = inventory;
    }

    public void setPlayerCell(Cell cell) {
        this.cell = cell;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }

    public void setWeapon(Weapon chosenWeapon) {
        this.chosenWeapon = chosenWeapon;
    }

    public Weapon getWeapon() {
        return chosenWeapon;
    }

    @Override
    public int getStrength() {
        return chosenWeapon != null ? chosenWeapon.getStatistic() + strength : strength;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public void healUp(int healthRestored) {
        currentHealth = Math.min(currentHealth + healthRestored, health);
    }
}
