package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Cell implements Drawable {
    private boolean xDirection = true;
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(validatePosition(dx, xDirection), validatePosition(dy, !xDirection));
    }

    private int validatePosition(int shift, boolean direction) {
        int currentPosition = direction ? x : y;
        int mapSizeDirection = direction ? gameMap.getWidth() : gameMap.getHeight();

        if (currentPosition + shift >= mapSizeDirection || currentPosition + shift < 0) {
            return  currentPosition;
        } else {
            return currentPosition + shift;
        }
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
