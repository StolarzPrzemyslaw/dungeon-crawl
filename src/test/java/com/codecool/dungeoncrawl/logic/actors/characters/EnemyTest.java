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

class EnemyTest {
    Enemy enemy;
    GameMap gameMap;

    @BeforeEach
    void createPerson() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        enemy = new Skeleton(cell);
    }

    @Ignore
    @Test
    @Order(1)
    void runActionAfterDefeat_EnemyIsDefeated_DisplayLogShowsMessage() {
        // TODO
//        //Arrange
//        Main main = new Main();
//        Game game = new Game(main);
//        Player player = new Player(enemy.getCell().getNeighbor(-1, -1));
//        String expectedDisplayLogMessage = "You defeated skeleton!";
//        //Act
//        enemy.runActionAfterDefeat(game, player);
    }

}