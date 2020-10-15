package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BridgeGuardianTest {
    private BridgeGuardian bridgeGuardian;

    @BeforeEach
    void createSkeleton() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 10, 10, CellType.FLOOR);
        bridgeGuardian = new BridgeGuardian(cell);
    }

    @Test
    @Order(1)
    void getTileName_BridgeGuardian_ReturnsGuardianString() {
        //Arrange
        String expectedTileName = "guardian";
        //Act
        String actualTileName = bridgeGuardian.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Ignore
    @Test
    @Order(2)
    void runActionAfterDefeat_BridgeGuardianIsDefeated_DisplayLogShowsMessage() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(bridgeGuardian.getCell().getNeighbor(-1, -1));
//        String expectedDisplayLogMessage = "You defeated guardian! You get additional 5 strength points!";
//        //Act
//        bridgeGuardian.runActionAfterDefeat(game, player);
    }
}