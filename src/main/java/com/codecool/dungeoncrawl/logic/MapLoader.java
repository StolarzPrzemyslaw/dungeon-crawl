package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.characters.Cow;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.characters.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.items.Key;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Obstacle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        List<Item> itemsOnMap = new ArrayList<>();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.OPEN_DOOR);
                            new Door(cell);
                            break;
                        case 'i':
                            cell.setType(CellType.WEAPON);
                            itemsOnMap.add(new Sword(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            itemsOnMap.add(new Key(cell));
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'f':
                            cell.setType(CellType.STAIRS);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cow(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        map.setItemsOnMap(itemsOnMap);
        return map;
    }

}
