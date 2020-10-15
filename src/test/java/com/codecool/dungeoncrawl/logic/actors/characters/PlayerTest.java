package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Axe;
import com.codecool.dungeoncrawl.logic.actors.items.Dagger;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void createPlayer() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
        player = new Player(cell);
    }

    @Test
    @Order(1)
    void setInventory_PlayerInventory_InventoryIsSet() {
        //Arrange
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(new Sword(player.getCell()));
        //Act
        player.setInventory(expectedInventory);
        //Assert
        assertEquals(expectedInventory, player.getInventory());
    }

    @Test
    @Order(2)
    void getInventory_ProperPlayerInventorySet_ReturnPlayerInventory() {
        //Arrange
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(new Dagger(player.getCell()));
        //Act
        Inventory actualInventory = player.getInventory();
        //Assert
        assertEquals(expectedInventory, actualInventory);
    }

    @Test
    @Order(3)
    void setPlayerCell_ProperCell_CellIsSet() {
        //Arrange
        GameMap gameMap = new GameMap(20, 20, CellType.FLOOR, Map.LEVEL1);
        Cell expectedCell = new Cell(gameMap, 10, 10, CellType.FLOOR);
        //Act
        player.setPlayerCell(expectedCell);
        //Assert
        assertEquals(expectedCell, player.getCell());
    }

    @Test
    @Order(4)
    void getTileName_PlayerWithDagger_ReturnPlayerString() {
        //Arrange
        String expectedTileName = "player";
        //Act
        String actualTileName = player.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Test
    @Order(5)
    void getTileName_PlayerWithAxe_ReturnPlayerLvL3String() {
        //Arrange
        player.setWeapon(new Axe(player.getCell()));
        String expectedTileName = "playerLvL2";
        //Act
        String actualTileName = player.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Test
    @Order(6)
    void getTileName_PlayerWithSword_ReturnPlayerLvL3String() {
        //Arrange
        player.setWeapon(new Sword(player.getCell()));
        String expectedTileName = "playerLvL3";
        //Act
        String actualTileName = player.getTileName();
        //Assert
        assertEquals(expectedTileName, actualTileName);
    }

    @Test
    @Order(7)
    void setWeapon_Axe_AxeIsSet() {
        //Arrange
        Weapon expectedWeapon = new Axe(player.getCell());
        //Act
        player.setWeapon(new Axe(player.getCell()));
        //Assert
        assertEquals(expectedWeapon, player.getWeapon());
    }

    @Test
    @Order(8)
    void getWeapon_PlayerWithDagger_ReturnDagger() {
        //Arrange
        Weapon expectedWeapon = new Dagger(player.getCell());
        //Act
        Weapon actualWeapon = player.getWeapon();
        //Assert
        assertEquals(expectedWeapon, actualWeapon);
    }

    @Test
    @Order(9)
    void getBaseStrength_PlayerWithBaseStrength_ReturnBaseStrength() {
        //Arrange
        int expectedBaseStrength = 5;
        //Act
        int actualBaseStrength = player.getBaseStrength();
        //Assert
        assertEquals(expectedBaseStrength, actualBaseStrength);
    }

    @Test
    @Order(10)
    void getStrength_PlayerWithAxe_ReturnExpectedStrength() {
        //Arrange
        player.setWeapon(new Axe(player.getCell()));
        int expectedStrength = player.getBaseStrength() + player.getWeapon().getStatistic();
        //Act
        int actualStrength = player.getStrength();
        //Assert
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    @Order(11)
    void getStrength_PlayerWithNoWeapon_ReturnBaseStrength() {
        //Arrange
        player.setWeapon(null);
        int expectedStrength = player.getBaseStrength();
        //Act
        int actualStrength = player.getStrength();
        //Assert
        assertEquals(expectedStrength, actualStrength);
    }

    @Test
    @Order(12)
    void setPlayerName_PlayerName_ProperNameIsSet() {
        //Arrange
        String expectedName = "Przemek";
        //Act
        player.setPlayerName("Przemek");
        //Assert
        assertEquals(expectedName, player.getName());
    }

    @Test
    @Order(13)
    void healUp_SumOfCurrentHealthAndHealthRestoredGreaterThanMaxHealth_CurrentHealthEqualsMaxHealth() {
        //Arrange
        int healthRestored = 10;
        player.setCurrentHealth(15);
        int expectedCurrentHealth = player.getHealth();
        //Act
        player.healUp(healthRestored);
        //Assert
        assertEquals(expectedCurrentHealth, player.getCurrentHealth());
    }

    @Test
    @Order(14)
    void healUp_SumOfCurrentHealthAndHealthRestoredLowerThanMaxHealth_ProperCurrentHealthIsSet() {
        //Arrange
        int healthRestored = 10;
        player.setCurrentHealth(5);
        int expectedCurrentHealth = player.getCurrentHealth() + healthRestored;
        //Act
        player.healUp(healthRestored);
        //Assert
        assertEquals(expectedCurrentHealth, player.getCurrentHealth());
    }



}