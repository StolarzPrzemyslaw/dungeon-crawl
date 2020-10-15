package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.*;


public class Player extends Person {

    private Inventory inventory;
    private Weapon chosenWeapon;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
        createPlayer();
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setPlayerCell(Cell cell) {
        this.cell = cell;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String getTileName() {
        switch (chosenWeapon.getName()) {
            case "Sword":
                return "playerLvL3";
            case "Axe":
                return "playerLvL2";
            default:
                return "player";
        }
    }

    public void setWeapon(Weapon weapon) {
        this.chosenWeapon = weapon;
    }

    public Weapon getWeapon() {
        return chosenWeapon;
    }

    public int getBaseStrength() {
        return strength;
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

    private void createPlayer() {
        this.name = "Hero Name";
        this.strength = 5;
        this.health = 20;
        this.currentHealth = this.health;
        chosenWeapon = new Dagger(cell);
        this.inventory.addItem(chosenWeapon);
        if (cell != null) {
            cell.setActor(this);
        }
    }
}
