package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaggerTest {
    Dagger dagger;
    GameMap gameMap;

    @BeforeEach
    void createDagger() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        dagger = new Dagger(cell);
    }

    @Test
    @Order(1)
    void getTileName_Dagger_ReturnsDaggerString() {
        //Arrange
        String expectedTileName = "dagger";
        //Act
        String actualTileName = dagger.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }
}