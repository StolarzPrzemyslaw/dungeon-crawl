package com.codecool.dungeoncrawl.logic.actors.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {
    Potion potion;
    GameMap gameMap;

    @BeforeEach
    void createPotion() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        potion = new Potion(cell);
    }

    @Test
    @Order(1)
    void getTileName_Potion_ReturnsPotionString() {
        //Arrange
        String expectedTileName = "potion";
        //Act
        String actualTileName = potion.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Test
    @Order(2)
    void getStatistic_Potion_ReturnsHealthRestored() {
        //Arrange
        int expectedStatistic = 5;
        //Act
        int actualStatistic = potion.getStatistic();
        //Assert
        assertEquals(expectedStatistic, actualStatistic);
    }

    @Test
    @Order(3)
    void consume_Player_PlayerIsHealed() {
        //Arrange
        Player player = new Player(potion.getCell().getNeighbor(1, 1));
        player.setCurrentHealth(player.getCurrentHealth() - 10);
        int expectedPlayerCurrentHealth = player.getCurrentHealth() + potion.getStatistic();
        //Act
        potion.consume(player);
        //Assert
        assertEquals(expectedPlayerCurrentHealth, player.getCurrentHealth());
    }

    @Test
    @Order(4)
    void consume_Player_PotionIsRemovedFromInventory() {
        //Arrange
        Player player = new Player(potion.getCell().getNeighbor(1, 1));
        player.getInventory().addItem(potion);
        //Act
        potion.consume(player);
        //Assert
        assertFalse(player.getInventory().contains(potion.getName()));
    }
}