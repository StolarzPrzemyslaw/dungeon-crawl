package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Axe;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerModelTest {
    private Cell cell;
    private PlayerModel playerModel;
    private Player player;
    private InventoryModel playerInventory;
    private ItemModel playerWeapon;
    private GameMap gameMap;

    @BeforeEach
    public void initPlayer() {
        gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
        player = new Player(cell);
        Inventory inventory = new Inventory();
        inventory.addItem(new Axe(cell));
        inventory.addItem(new Sword(cell));
        playerInventory = new InventoryModel(inventory);
        playerWeapon = new ItemModel(new Axe(cell));
    }

    @Test
    public void playerModelConstructorWithOneParameter_Player_returnPlayerModelWhichIsNotNull() {
        playerModel = new PlayerModel(player);
        assertNotNull(playerModel);
    }

    @Test
    public void playerModelConstructorWithOneParameter_Player_returnPlayerModelWhichIsNull() {
        assertNull(playerModel);
    }

    @Test
    public void playerModelConstructorWithMultipleParameter_Player_returnPlayerModelWhichIsNotNull() {
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);
        assertNotNull(playerModel);
    }

    @Test
    public void getPlayer_Player_returnPlayerObject() {
        playerModel = new PlayerModel(player);

        assertThat(playerModel.getPlayer(), instanceOf(Player.class));
    }

    @Test
    public void getCell_Player_returnNewCellType() {
        CellType currentCellType = cell.getType();
        Cell newCell = new Cell(gameMap, 4, 4, CellType.BONFIRE);
        playerModel = new PlayerModel(player);

        playerModel.setPlayerCell(newCell);

        assertNotEquals(currentCellType, playerModel.getPlayer().getCell().getType());
    }

    @Test
    public void getPlayerName_PlayerWithMultipleParameters_returnStringPlayerName() {
        String expectedPlayerName = "hero";
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerName, playerModel.getPlayerName());
    }

    @Test
    public void getPlayerHp_PlayerWithMultipleParameters_returnPlayerHealth() {
        int expectedPlayerHp = 10;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerHp, playerModel.getHp());
    }

    @Test
    public void getX_PlayerWithMultipleParameters_returnPlayerPosX() {
        int expectedPlayerPosX = 2;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerPosX, playerModel.getX());
    }

    @Test
    public void getY_PlayerWithMultipleParameters_returnPlayerPosY() {
        int expectedPlayerPosY = 2;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerPosY, playerModel.getY());
    }

    @Test
    public void getCurrentHp_PlayerWithMultipleParameters_returnPlayerCurrentHealth() {
        int expectedPlayerCurrentHp = 12;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerCurrentHp, playerModel.getCurrentHp());
    }

    @Test
    public void getStrength_PlayerWithMultipleParameters_returnPlayerStrength() {
        int expectedPlayerStrength = 15;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);

        assertEquals(expectedPlayerStrength, playerModel.getStrength());
    }

    @Test
    public void getWeaponId_PlayerWithMultipleParameters_setWeaponIdAndReturnPlayerWeaponId() {
        int expectedPlayerWeaponId = 2;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);
        playerModel.setWeaponId(expectedPlayerWeaponId);

        assertEquals(expectedPlayerWeaponId, playerModel.getWeaponId());
    }

    @Test
    public void getInventoryId_PlayerWithMultipleParameters_setInventoryIdAndReturnPlayerInventoryId() {
        int expectedPlayerInventoryId = 3;
        playerModel = new PlayerModel(10, 12, 15, "hero", 2, 2, playerInventory, playerWeapon);
        playerModel.setInventoryId(expectedPlayerInventoryId);

        assertEquals(expectedPlayerInventoryId, playerModel.getInventoryId());
    }
}
