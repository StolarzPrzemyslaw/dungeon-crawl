package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.characters.Player;

public class PlayerModel extends BaseModel {
    private final String playerName;
    private final int hp;
    private final int currentHp;
    private final int strength;
    private final int x;
    private final int y;
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
        this.weaponId = weaponId;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.currentHp = player.getCurrentHealth();
        this.strength = player.getStrength();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHp() {
        return hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getStrength() {
        return strength;
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
