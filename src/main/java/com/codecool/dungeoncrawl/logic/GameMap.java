package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;

import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private List<Item> itemsOnMap;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
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

    public void setItemsOnMap(List<Item> itemsOnMap) {
        this.itemsOnMap = itemsOnMap;
    }

    public List<Item> getItemsOnMap() {
        return itemsOnMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
