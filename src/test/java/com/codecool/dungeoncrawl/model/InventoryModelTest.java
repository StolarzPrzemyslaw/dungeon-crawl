package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Axe;
import com.codecool.dungeoncrawl.logic.actors.items.Potion;
import com.codecool.dungeoncrawl.logic.actors.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryModelTest {

    private List<ItemModel> items;
    private int inventoryId;
    private Inventory inventory;
    private InventoryModel inventoryModel;
    private Cell cell;

    @BeforeEach
    public void initInventory() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
        inventory = new Inventory();
        inventory.addItem(new Axe(cell));
        inventory.addItem(new Sword(cell));

        items = new ArrayList<>();
        items.add(new ItemModel(new Axe(cell)));
        items.add(new ItemModel(new Sword(cell)));

        inventoryId = 1;
    }

    @Test
    public void inventoryConstructorWithOneParameter_parametersRequiredToCreateInventory_returnInventoryModelWhichIsNotNull() {
        inventoryModel = new InventoryModel(inventory);
        assertNotNull(inventoryModel);
    }

    @Test
    public void inventoryConstructorWithOneParameter_parametersRequiredToCreateInventory_returnNullValue() {
        assertNull(inventoryModel);
    }

    @Test
    public void inventoryConstructorWithTwoParameters_parametersRequiredToCreateInventory_returnInventoryModelWhichIsNotNull() {
        inventoryModel = new InventoryModel(items, inventoryId);
        assertNotNull(inventoryModel);
    }

    @Test
    public void inventoryConstructorWithTwoParameters_parametersRequiredToCreateInventory_returnNullValue() {
        assertNull(inventoryModel);
    }

    @Test
    public void getInventory_parametersRequiredToCreateInventory_listOfInventoryItems() {
        inventoryModel = new InventoryModel(items, inventoryId);
        Inventory expectedInventory = inventory;

        assertEquals(expectedInventory, inventoryModel.getInventory());
    }

    @Test
    public void getInventory_parametersRequiredToCreateInventory_inventoryItemsDoesNotMatch() {
        inventoryModel = new InventoryModel(items, inventoryId);
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(new Sword(cell));

        assertNotEquals(expectedInventory, inventoryModel.getInventory());
    }

    @Test
    public void getInventory_parametersRequiredToCreateInventory_listOfItems() {
        inventoryModel = new InventoryModel(items, inventoryId);
        List<ItemModel> expectedInventory = items;

        assertEquals(expectedInventory, inventoryModel.getItems());
    }

    @Test
    public void getInventory_parametersRequiredToCreateInventory_inventoryId() {
        inventoryModel = new InventoryModel(items, inventoryId);
        int expectedInventoryId = inventoryId;

        assertEquals(expectedInventoryId, inventoryModel.getId());
    }
}
