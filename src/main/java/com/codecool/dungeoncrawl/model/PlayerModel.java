package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;

public class PlayerModel extends BaseModel {
    private final String playerName;
    private final int hp;
    private final int currentHp;
    private final int strength;
    private final int x;
    private final int y;
    transient private int inventoryId;
    transient private int weaponId;
    private final InventoryModel inventory;
    private final ItemModel weapon;
    private Cell playerCell;


    public PlayerModel(int hp, int currentHp, int strength, String playerName, int posX, int posY, InventoryModel inventory, ItemModel weapon) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.strength = strength;
        this.playerName = playerName;
        this.x = posX;
        this.y = posY;
        this.inventory = inventory;
        this.weapon = weapon;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.currentHp = player.getCurrentHealth();
        this.strength = player.getBaseStrength();
        this.inventory = new InventoryModel(player.getInventory());
        this.weapon = new ItemModel(player.getWeapon());
    }

    public Player getPlayer() {
        Player player = new Player(playerCell);
        player.setStrength(strength);
        player.setCurrentHealth(currentHp);
        player.setPlayerName(playerName);
        player.setInventory(inventory.getInventory());
        player.setWeapon((Weapon) weapon.getItem());
        return player;
    }

    public void setPlayerCell(Cell cell) {
        this.playerCell = cell;
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

