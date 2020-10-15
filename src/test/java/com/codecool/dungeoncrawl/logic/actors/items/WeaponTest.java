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

class WeaponTest {
    Weapon weapon;
    GameMap gameMap;
    Cell cell;

    @BeforeEach
    void createWeapon() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 2, 2, CellType.FLOOR);
        weapon = new Sword(cell);
    }

    @Test
    @Order(1)
    void use_Player_PlayerHasEquippedWeapon() {
        //Arrange
        Player player = new Player(weapon.getCell().getNeighbor(1, 1));
        player.getInventory().addItem(weapon);
        Weapon expectedWeapon = new Sword(cell);
        //Act
        weapon.use(player);
        //Assert
        assertEquals(expectedWeapon, player.getWeapon());
    }
}