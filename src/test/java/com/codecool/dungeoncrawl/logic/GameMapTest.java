package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    private static GameMap gameMap;

    @BeforeAll
    public static void prepareGameMap() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
    }

    @Test
    public void getLevelId_runGetLevelIdOnMap_ReturnCorrectValue() {
        assertEquals(1, gameMap.getLevelId());
    }

    @Test
    public void getWidth_runGetWidthOnMap_ReturnCorrectValue() {
        assertEquals(30, gameMap.getWidth());
    }

    @Test
    public void getWidth_runGetWidthOnMap_ReturnValidValue() {
        assertNotEquals(15, gameMap.getWidth());
    }

    @Test
    public void getWidth_runGetHeightOnMap_ReturnCorrectValue() {
        assertEquals(30, gameMap.getHeight());
    }

}