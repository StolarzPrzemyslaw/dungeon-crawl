package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.characters.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int currentHp;
    private int strength;
    private int x;
    private int y;


    public PlayerModel(int hp, int currentHp, int strength, String playerName, int x, int y) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.strength = strength;
        this.playerName = playerName;
        this.x = x;
        this.y = y;
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
}
