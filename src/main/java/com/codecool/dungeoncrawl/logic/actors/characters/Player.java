package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Dagger;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;

public class Player extends Person {

    private Inventory inventory;
    private Weapon chosenWeapon;
    private final int INITIAL_STRENGTH = 5;

    public Player(Cell cell) {
        super(cell);
        createPlayer();
    }

    public Player(Cell cell, Inventory inventory) {
        super(cell);
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void getItemFromTheFloor(Item obtainedItem) {
        inventory.addItemToInventory(obtainedItem);
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

    public int getStrengthBasedOnWeapon() {
        return chosenWeapon != null ? chosenWeapon.getStatistic() + strength : INITIAL_STRENGTH;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public void healUp(int healthRestored) {
        currentHealth = Math.min(currentHealth + healthRestored, health);
    }

    private void createPlayer() {
        this.name = "Hero Name";
        this.strength = 5;
        this.health = 20;
        this.currentHealth = this.health;
        chosenWeapon = new Dagger(cell);
        this.inventory = new Inventory();
        this.inventory.addItemToInventory(chosenWeapon);
        cell.setActor(this);
    }
}