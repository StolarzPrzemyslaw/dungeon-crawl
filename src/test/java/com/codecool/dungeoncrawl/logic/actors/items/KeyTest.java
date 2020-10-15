package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {
    Key key;
    GameMap gameMap;

    @BeforeEach
    void createKey() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        key = new Key(cell);
    }

    @Test
    @Order(1)
    void getTileName_Key_ReturnsKeyString() {
        //Arrange
        String expectedTileName = "key";
        //Act
        String actualTileName = key.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

}