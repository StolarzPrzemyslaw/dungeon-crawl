package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.view.Game;
import com.codecool.dungeoncrawl.view.Main;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item;
    GameMap gameMap;
    Cell cell;

    @BeforeEach
    void createItem() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        item = new Sword(cell);
    }

    @Test
    @Order(1)
    void toString_Item_ReturnsItemName() {
        //Arrange
        String expectedItemToStringResult = item.getName();
        //Act
        String actualItemToStringResult = item.toString();
        //Assert
        assertEquals(expectedItemToStringResult, actualItemToStringResult);
    }

    @Ignore
    @Test
    @Order(2)
    void showObtainMessage_Game_DisplayLogShowsMessage() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(item.getCell().getNeighbor(-1, -1));
//        String expectedDisplayLogMessage = "You have obtained a " + item.getName();
//        //Act
//        item.showObtainMessage(game);
//        //Assert
    }
}