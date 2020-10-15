package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Axe;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateModelTest {
    private GameStateModel gameStateModel;
    private final Date saveAt = new Date(System.currentTimeMillis());
    private final String saveName = "new save";
    private PlayerModel player;
    private InventoryModel playerInventory;
    private ItemModel playerWeapon;

    @BeforeEach
    public void initGameStateModel() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        Cell cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
        Inventory inventory = new Inventory();
        inventory.addItem(new Axe(cell));
        inventory.addItem(new Sword(cell));
        playerInventory = new InventoryModel(inventory);
        playerWeapon = new ItemModel(new Axe(cell));
        player = new PlayerModel(10, 13, 16, "hero", 2, 2, playerInventory, playerWeapon);

        gameStateModel = new GameStateModel(Map.LEVEL1, saveAt, player, saveName);
    }

    @Test
    public void gameStateModelConstructor_parametersRequiredToCreateGameStateModel_returnNewGameStateModelWhichIsNotNull() {
        assertNotNull(gameStateModel);
    }

    @Test
    public void getSavedAt_parametersRequiredToCreateGameStateModel_TimeOfSavedGame() {
        Date expectedTime = saveAt;

        assertEquals(expectedTime, gameStateModel.getSavedAt());
    }

    @Test
    public void getSaveName_parametersRequiredToCreateGameStateModel_stringWithNameOfSavedGame() {
        String expectedSaveName = saveName;

        assertEquals(expectedSaveName, gameStateModel.getSaveName());
    }

    @Test
    public void getSaveName_parametersRequiredToCreateGameStateModel_PlayerModel() {
        PlayerModel expectedPlayer = player;

        assertSame(expectedPlayer, gameStateModel.getPlayer());
    }

    @Test
    public void getSaveName_parametersRequiredToCreateGameStateModel_DifferentPlayerModels() {
        PlayerModel expectedPlayer = new PlayerModel(3, 10, 10, "janusz",
                2, 2, playerInventory, playerWeapon);;

        assertNotEquals(expectedPlayer, gameStateModel.getPlayer());
    }

    @Test
    public void getSaveName_parametersRequiredToCreateGameStateModel_MapModel() {
        Map expectedMap = Map.LEVEL1;

        assertSame(expectedMap, gameStateModel.getCurrentMap());
    }
}
