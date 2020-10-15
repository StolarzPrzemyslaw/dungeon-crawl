package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person;
    GameMap gameMap;

    @BeforeEach
    void createPerson() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        person = new Player(cell);
    }

    @Test
    @Order(1)
    void setStrength_Strength_StrengthIsSet() {
        //Arrange
        int expectedStrength = 10;
        //Act
        person.setStrength(expectedStrength);
        //Assert
        assertEquals(expectedStrength, person.getStrength());
    }

    @Test
    @Order(2)
    void setBackgroundCellActor_Sword_SwordIsSetAsBackgroundActor() {
        //Arrange
        Actor expectedBackgroundCellActor = new Sword(person.getCell());
        //Act
        person.setBackgroundCellActor(expectedBackgroundCellActor);
        //Assert
        assertEquals(expectedBackgroundCellActor, person.getBackgroundCellActor());
    }

    @Test
    @Order(3)
    void getBackgroundCellActor_Sword_ReturnsSword() {
        //Arrange
        Sword expectedBackgroundCellActor = new Sword(person.getCell());
        person.setBackgroundCellActor(expectedBackgroundCellActor);
        //Act
        Actor actualBackgroundCellActor = person.getBackgroundCellActor();
        //Assert
        assertEquals(expectedBackgroundCellActor, actualBackgroundCellActor);
    }

    @Test
    @Order(4)
    void move_MoveOneCellUp_PersonIsMovedToRightCell() {
        //Arrange
        Cell expectedCell = gameMap.getCell(3, 3);
        //Act
        person.move(1, 1);
        //Assert
        assertEquals(expectedCell, person.getCell());

    }

}