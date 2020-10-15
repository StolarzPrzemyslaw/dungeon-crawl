package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.view.Game;
import com.codecool.dungeoncrawl.view.Main;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CowTest {
    private Cow cow;

    @BeforeEach
    void createCow() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 10, 10, CellType.FLOOR);
        cow = new Cow(cell);
    }

    @Test
    @Order(1)
    void resetStepsLeft_StepsLeftNotEqualsInitialValue_StepsLeftEqualsInitialValue() {
        //Arrange
        int expectedStepsLeft = cow.getStepsLeft();
        cow.decreaseStepsLeft();
        //act
        cow.resetStepsLeft();
        //assert
        assertEquals(expectedStepsLeft, cow.getStepsLeft());
    }

    @Test
    @Order(2)
    void decreaseStepsLeft_StepsLeftEqualsInitialValue_StepsLeftEqualsDecreasedInitialValue() {
        //Arrange
        int expectedStepsLeft = cow.getStepsLeft() - 1;
        //Act
        cow.decreaseStepsLeft();
        //Assert
        assertEquals(expectedStepsLeft, cow.getStepsLeft());
    }

    @Test
    @Order(3)
    void getStepsLeft_StepsLeftEqualsInitialValue_StepsLeftEqualsInitialValue() {
        //Arrange
        int expectedStepsLeft = 6;
        //Act
        int actualStepsLeft = cow.getStepsLeft();
        //Assert
        assertEquals(expectedStepsLeft, actualStepsLeft);
    }

    @Test
    @Order(4)
    void isMovingLeft_IsMovingLeft_ReturnsTrue() {
        //Arrange
        //Act
        boolean actualIsMovingLeftValue = cow.isMovingLeft();
        //Assert
        assertTrue(actualIsMovingLeftValue);
    }

    @Test
    @Order(5)
    void isMovingLeft_IsMovingRight_ReturnsFalse() {
        //Arrange
        cow.swapDirectionOfMoving();
        //Act
        boolean actualIsMovingLeftValue = cow.isMovingLeft();
        //Assert
        assertFalse(actualIsMovingLeftValue);
    }

    @Test
    @Order(6)
    void swapDirectionOfMoving_IsMovingRight_IsMovingLeft() {
        //Arrange
        //Act
        cow.swapDirectionOfMoving();
        //Assert
        assertFalse(cow.isMovingLeft());
    }

    @Test
    @Order(7)
    void getTileName_Cow_ReturnsCowString() {
        //Arrange
        String expectedTileName = "cow";
        //Act
        String actualTileName = cow.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Ignore
    @Test
    @Order(8)
    void runActionAfterDefeat_CowIsDefeated_DisplayLogShowsMessage() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(cow.getCell().getNeighbor(-1, -1));
//        String expectedDisplayLogMessage = "You defeated cow! You are fully healed up!";
//        //Act
//        cow.runActionAfterDefeat(game, player);
//        //Assert
////        game.currentLog.getText()
//        assertEquals(expectedDisplayLogMessage, "");
    }

    @Ignore
    @Test
    @Order(8)
    void runActionAfterDefeat_CowIsDefeated_PlayerIsHealed() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(cow.getCell().getNeighbor(-1, -1));
//        player.setCurrentHealth(10);
//        int expectedPlayerCurrentHealth = player.getHealth();
//        //Act
//        cow.runActionAfterDefeat(game, player);
//        //Assert
//        assertEquals(expectedPlayerCurrentHealth, player.getCurrentHealth());
    }
}