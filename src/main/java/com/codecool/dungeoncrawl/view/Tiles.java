package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("axe", new Tile(7, 29));
        tileMap.put("knife", new Tile(4, 28));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("playerLvL2", new Tile(28, 0));
        tileMap.put("playerLvL3", new Tile(31, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("cow", new Tile(27, 7));
        tileMap.put("closedDoor", new Tile(0, 9));
        tileMap.put("openDoor", new Tile(2, 9));
        tileMap.put("river", new Tile(8, 4));
        tileMap.put("bridge", new Tile(16, 5));
        tileMap.put("potion", new Tile(17, 25));
        tileMap.put("guardian", new Tile(25, 9));
        tileMap.put("stairs", new Tile(3, 6));
        tileMap.put("dagger", new Tile(4, 28));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = d == null ? tileMap.get("empty") : tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
