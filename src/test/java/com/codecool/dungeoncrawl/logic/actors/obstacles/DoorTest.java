package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    private static Cell cell;
    private static Door door;

    @BeforeAll
    public static void createNewCell() {
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 2, 2, CellType.EMPTY);
        door = new Door(cell);
    }

    @Test
    public void doorConstructor_doorClass_obstacleClass() {
        assertThat(door, instanceOf(Obstacle.class));
    }

    @Test
    public void doorConstructor_cell_returnNewDoorObject() {
        assertNotNull(door);
    }

    @Test
    public void getTileName_closeDoor_closeDoorString() {
        door = new Door(cell);

        assertEquals("closedDoor", door.getTileName());
    }

    @Test
    public void getTileName_closeDoor_openDoorString() {
        door.open();

        assertEquals("openDoor", door.getTileName());
    }

    @Test
    public void isOpen_closeDoorState_closeDoorState() {
        door = new Door(cell);

        assertFalse(door.isOpen());
    }

    @Test
    public void isOpen_closeDoor_openDoorState() {
        door.open();

        assertTrue(door.isOpen());
    }
}
