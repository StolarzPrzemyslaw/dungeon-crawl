package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemModelTest {
    private Item item;
    private Cell cell;
    private ItemModel itemModel;

    @BeforeEach
    public void initCell() {
        GameMap gameMap = new GameMap(30, 30, CellType.FLOOR, Map.LEVEL1);
        cell = new Cell(gameMap, 3, 3, CellType.FLOOR);
        item = new Sword(cell);

    }

    @Test
    public void itemModelConstructorWithOneParameter_SwordItem_returnItemModelWhichIsNotNull() {
        itemModel = new ItemModel(item);
        assertNotNull(itemModel);
    }

    @Test
    public void itemModelConstructorWithOneParameter_PotionItem_returnItemModelWhichIsNotNull() {
        itemModel = new ItemModel(new Potion(cell));
        assertNotNull(itemModel);
    }

    @Test
    public void itemModelConstructorWithOneParameter_KeyItem_returnItemModelWhichIsNotNull() {
        itemModel = new ItemModel(new Key(cell));
        assertNotNull(itemModel);
    }

    @Test
    public void itemModelConstructorWithOneParameter_Item_returnItemModelWhichIsNull() {
        assertNull(itemModel);
    }

    @Test
    public void itemModelConstructorWithMultipleParameter_ItemIdNameStatisticType_returnItemModelWhichIsNotNull() {
        itemModel = new ItemModel(1, "dagger", 4, "usable");
        assertNotNull(itemModel);
    }

    @Test
    public void getId_ItemIdNameStatisticType_returnItemModelId() {
        int expectedId = 1;
        itemModel = new ItemModel(1, "dagger", 4, "none");

        assertEquals(expectedId, itemModel.getId());
    }

    @Test
    public void getName_ItemIdNameStatisticType_returnItemModelName() {
        String expectedName = "dagger";
        itemModel = new ItemModel(1, "dagger", 4, "usable");

        assertEquals(expectedName, itemModel.getName());
    }

    @Test
    public void getStatistics_ItemIdNameStatisticType_returnItemModelStatistics() {
        int expectedStatistics = 4;
        itemModel = new ItemModel(1, "dagger", 4, "usable");

        assertEquals(expectedStatistics, itemModel.getStatistic());
    }

    @Test
    public void getType_ItemIdNameStatisticType_returnItemModelUsableType() {
        ItemModel.Type expectedType = ItemModel.Type.USABLE;
        itemModel = new ItemModel(1, "dagger", 4, "usable");

        assertEquals(expectedType, itemModel.getItemType());
    }

    @Test
    public void getType_ItemIdNameStatisticType_returnItemModelConsumableType() {
        ItemModel.Type expectedType = ItemModel.Type.CONSUMABLE;
        itemModel = new ItemModel(1, "Small potion", 4, "consumable");

        assertEquals(expectedType, itemModel.getItemType());
    }

    @Test
    public void getType_ItemIdNameStatisticType_returnInvalidItemModelType() {
        ItemModel.Type expectedType = ItemModel.Type.NONE;
        itemModel = new ItemModel(1, "dagger", 4, "usable");

        assertNotEquals(expectedType, itemModel.getItemType());
    }

    @Test
    public void getItem_ItemIdNameStatisticType_returnItemModelTypeBasicDagger() {
        Dagger expectedItem = new Dagger(cell);
        itemModel = new ItemModel(1, "Basic dagger", 4, "usable");

        assertEquals(expectedItem, itemModel.getItem());
    }

    @Test
    public void getItem_ItemIdNameStatisticType_InvalidItemModel() {
        Axe expectedItem = new Axe(cell);
        itemModel = new ItemModel(1, "Basic dagger", 4, "usable");

        assertNotEquals(expectedItem, itemModel.getItem());
    }

    @Test
    public void getItem_ItemIdNameStatisticType_returnItemModelTypePotion() {
        Potion expectedItem = new Potion(cell);
        itemModel = new ItemModel(1, "Small Potion", 4, "consumable");

        assertEquals(expectedItem, itemModel.getItem());
    }

    @Test
    public void getItem_ItemIdNameStatisticType_returnItemModelTypeAxe() {
        Knife expectedItem = new Knife(cell);
        itemModel = new ItemModel(1, "Knife", 4, "usable");

        assertEquals(expectedItem, itemModel.getItem());
    }

    @Test
    public void getItem_ItemIdNameStatisticType_returnItemModelTypeKey() {
        Key expectedItem = new Key(cell);
        itemModel = new ItemModel(1, "Key", 4, "none");

        assertEquals(expectedItem, itemModel.getItem());
    }
}
