package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.characters.BridgeGuardian;
import com.codecool.dungeoncrawl.logic.actors.characters.Cow;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.characters.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.items.*;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Stairs;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(Map mapType, boolean firstRun) {

        InputStream is = MapLoader.class.getResourceAsStream("/" + mapType.getName());
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine();

        GameMap map = new GameMap(width, height, CellType.EMPTY, mapType);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    if (firstRun && x == mapType.getStartX() && y == mapType.getStartY()) {
                        cell.setType(CellType.FLOOR);
                        map.setPlayer(new Player(cell));
                        continue;
                    }
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
                        case '~':
                            cell.setType(CellType.RIVER);
                            break;
                        case '-':
                            cell.setType(CellType.BRIDGE);
                            break;
                        case 'A':
                            cell.setType(CellType.BONFIRE);
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        case 'i':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'j':
                            cell.setType(CellType.FLOOR);
                            new Axe(cell);
                            break;
                        case 'n':
                            cell.setType(CellType.FLOOR);
                            new Knife(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cow(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new BridgeGuardian(cell);
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            new Stairs(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
