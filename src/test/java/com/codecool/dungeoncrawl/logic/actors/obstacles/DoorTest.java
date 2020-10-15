package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    private static GameMap gameMap;
    private static Cell cell;
    private static Door door;

    @BeforeAll
    public static void createNewCell() {
        gameMap = new GameMap(3, 3, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 2, 2, CellType.BONFIRE);
        door = new Door(cell);
    }

    @Test
    public void DoorConstructor_Cell_returnNewDoorObject() {
        assertNotNull(door);
    }

    @Test
    public void GetTileName_CloseDoor_CloseDoorString() {
        door = new Door(cell);

        assertEquals("closedDoor", door.getTileName());
    }

    @Test
    public void GetTileName_CloseDoor_OpenDoorString() {
        door.open();

        assertEquals("openDoor", door.getTileName());
    }

    @Test
    public void IsOpen_CloseDoorState_CloseDoorState() {
        door = new Door(cell);

        assertFalse(door.isOpen());
    }

    @Test
    public void IsOpen_CloseDoor_OpenDoorState() {
        door.open();

        assertTrue(door.isOpen());
    }
}
