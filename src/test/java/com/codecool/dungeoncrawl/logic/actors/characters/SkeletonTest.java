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

class SkeletonTest {
    private Skeleton skeleton;

    @BeforeEach
    void createSkeleton() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 10, 10, CellType.FLOOR);
        skeleton = new Skeleton(cell);
    }

    @Test
    @Order(1)
    void getTileName_Skeleton_ReturnsSkeletonString() {
        //Arrange
        String expectedTileName = "skeleton";
        //Act
        String actualTileName = skeleton.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Ignore
    @Test
    @Order(2)
    void runActionAfterDefeat_CowIsDefeated_DisplayLogShowsMessage() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(skeleton.getCell().getNeighbor(-1, -1));
//        String expectedDisplayLogMessage = "You defeated skeleton!";
//        //Act
//        skeleton.runActionAfterDefeat(game, player);
    }
}