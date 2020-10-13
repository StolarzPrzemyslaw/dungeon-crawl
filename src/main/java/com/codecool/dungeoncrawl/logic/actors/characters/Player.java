package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.*;


public class Player extends Person {

    private final Inventory inventory;
    private Weapon chosenWeapon;
    private int weaponId;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
        createPlayer();
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

    @Override
    public String getTileName() {
        if (chosenWeapon instanceof Sword) {
            return "playerLvL3";
        } else if (chosenWeapon instanceof Axe) {
            return "playerLvL2";
        }
        return "player";
    }

    public void setWeapon(Weapon weapon) {
        this.chosenWeapon = weapon;
        chosenWeaponById(weapon);
    }

    private void chosenWeaponById(Weapon weapon) {
        if (weapon instanceof Axe) {
            setWeaponId(0);
        } else if (weapon instanceof Dagger) {
            setWeaponId(1);
        } else if (weapon instanceof Knife) {
            setWeaponId(2);
        } else {
            setWeaponId(3);
        }
    }

    public Weapon getWeapon() {
        return chosenWeapon;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
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
        this.inventory.addItemToInventory(chosenWeapon);
        cell.setActor(this);
    }
}
