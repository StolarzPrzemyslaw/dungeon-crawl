package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.characters.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int currentHp;
    private int strength;
    private int x;
    private int y;
    private int inventoryId;
    private int weaponId;


    public PlayerModel(int hp, int currentHp, int strength, String playerName, int posX, int posY, int inventoryId, int weaponId) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.strength = strength;
        this.playerName = playerName;
        this.x = posX;
        this.y = posY;
        this.inventoryId = inventoryId;
        this.weaponId= weaponId;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.currentHp = player.getCurrentHealth();
        this.strength = player.getStrength();
        this.weaponId = player.getWeaponId();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getStrength() {
        return strength;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }
}
