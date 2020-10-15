package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    @Test
    public void loadMap_loadLevel1_playerOnCorrectXY() {
        GameMap gameMap = MapLoader.loadMap(Map.LEVEL1, true);
        assertEquals(Map.LEVEL1.getStartX(), gameMap.getPlayer().getX());
        assertEquals(Map.LEVEL1.getStartY(), gameMap.getPlayer().getY());
    }

    @Test
    public void loadMap_loadLevel2_playerOnCorrectXY() {
        GameMap gameMap = MapLoader.loadMap(Map.LEVEL2, true);
        assertEquals(Map.LEVEL2.getStartX(), gameMap.getPlayer().getX());
        assertEquals(Map.LEVEL2.getStartY(), gameMap.getPlayer().getY());
    }

    @Test
    public void loadMap_loadLevel3_playerOnCorrectXY() {
        GameMap gameMap = MapLoader.loadMap(Map.LEVEL3, true);
        assertEquals(Map.LEVEL3.getStartX(), gameMap.getPlayer().getX());
        assertEquals(Map.LEVEL3.getStartY(), gameMap.getPlayer().getY());
    }

    @Test
    public void loadMap_loadLevel4_playerOnCorrectXY() {
        GameMap gameMap = MapLoader.loadMap(Map.LEVEL4, true);
        assertEquals(Map.LEVEL4.getStartX(), gameMap.getPlayer().getX());
        assertEquals(Map.LEVEL4.getStartY(), gameMap.getPlayer().getY());
    }

    @Test
    public void loadMap_loadTestLevel_throwsException() {
        assertThrows(RuntimeException.class, () -> MapLoader.loadMap(null, true));
    }

}