package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    Sword sword;
    GameMap gameMap;

    @BeforeEach
    void createSword() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        sword = new Sword(cell);
    }

    @Test
    @Order(1)
    void getTileName_Sword_ReturnsSwordString() {
        //Arrange
        String expectedTileName = "sword";
        //Act
        String actualTileName = sword.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }
}