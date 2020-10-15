package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private final Map map;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType, Map map) {
        this.map = map;
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public int getLevelId() {
        return map.getId();
    }

    public List<Actor> getAllEnemiesOnMap(Class<?> className) {
        List<Actor> listOfActors = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].isOccupiedByClass(className)) {
                    listOfActors.add(cells[x][y].getActor());
                }
            }
        }
        return listOfActors;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().isInstance(this)
                && ((GameMap) obj).getWidth() == this.getWidth()
                && ((GameMap) obj).getHeight() == this.getHeight()
                && ((GameMap) obj).getLevelId() == this.getLevelId()
                && Arrays.deepEquals(((GameMap) obj).getCells(), this.getCells())
                && ((GameMap) obj).getPlayer().equals(this.getPlayer());
    }

}
