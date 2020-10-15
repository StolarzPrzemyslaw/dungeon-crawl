package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Map;

import java.sql.Date;

public class GameStateModel extends BaseModel {
    private final Date savedAt;
    private final Map currentMap;
    private final PlayerModel player;
    private final String saveName;

    public GameStateModel(Map currentMap, Date savedAt, PlayerModel player, String saveName) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.saveName = saveName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public String getSaveName() {
        return saveName;
    }
}
