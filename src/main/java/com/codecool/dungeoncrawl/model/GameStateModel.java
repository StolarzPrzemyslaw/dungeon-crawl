package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Map;

import java.sql.Date;

public class GameStateModel extends BaseModel {
    private Date savedAt;
    private Map currentMap;
    private PlayerModel player;

    private String saveName;

    public GameStateModel(Map currentMap, Date savedAt, PlayerModel player, String saveName) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.saveName = saveName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
