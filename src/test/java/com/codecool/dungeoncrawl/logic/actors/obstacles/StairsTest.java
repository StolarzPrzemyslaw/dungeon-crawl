package com.codecool.dungeoncrawl.logic.actors.obstacles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StairsTest {
    private static Cell cell;
    private static Stairs stairs;

    @BeforeAll
    public static void createNewCell() {
        GameMap gameMap = new GameMap(3, 3, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 2, 2, CellType.EMPTY);
        stairs = new Stairs(cell);
    }

    @Test
    public void stairsConstructor_stairsClass_obstacleClass() {
        assertThat(stairs, instanceOf(Obstacle.class));
    }

    @Test
    public void stairsConstructor_cell_returnNewStairsObject() {
        assertNotNull(stairs);
    }

    @Test
    public void getTileName_stairs_stairsTitleNameString() {
        assertEquals("stairs", stairs.getTileName());
    }

    @Test
    public void getTileName_stairs_stairsTitleNameIsNotNull() {
        assertNotNull(stairs.getTileName());
    }
}
