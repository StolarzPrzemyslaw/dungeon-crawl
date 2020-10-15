package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    public void addItemToInventory_addValidItem_existOnlyThatItem() {
        // arrange
        Inventory inventory = new Inventory();
        Item item = new Key(null);

        // act
        inventory.addItem(item);

        // assert
        assertEquals(1, inventory.getItems().size());
        assertTrue(inventory.contains(item.getName()));
        assertNotNull(inventory.getItems().get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> assertNotNull(inventory.getItems().get(1)));
    }

    @Test
    public void addItemToInventory_addMultipleValidItem_itemsInInventoryInGoodOrder() {
        // arrange
        Inventory inventory = new Inventory();
        Item item1 = new Key(null);
        Item item2 = new Axe(null);
        Item item3 = new Dagger(null);

        // act
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);

        // assert
        assertEquals(3, inventory.getItems().size());
        assertEquals(item1, inventory.getItems().get(0));
        assertEquals(item2, inventory.getItems().get(1));
        assertEquals(item3, inventory.getItems().get(2));
    }

    @Test
    public void removeItemFromInventory_addMultipleItemsAndRemoveOnlyTwo_ThreeItemsLeft() {
        // arrange
        Inventory inventory = new Inventory();
        Item item1 = new Key(null);
        Item item2 = new Axe(null);
        Item item3 = new Dagger(null);
        Item item4 = new Sword(null);
        Item item5 = new Potion(null);

        // act
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.addItem(item5);
        inventory.removeItem(item3);
        inventory.removeItem(item2);

        // assert
        assertEquals(3, inventory.getItems().size());
        assertEquals(item1, inventory.getItems().get(0));
        assertEquals(item4, inventory.getItems().get(1));
        assertEquals(item5, inventory.getItems().get(2));
    }

    @Test
    public void removeItemByName_addMultipleItemsAndRemoveOnlyTwo_ThreeItemsLeft() {
        // arrange
        Inventory inventory = new Inventory();
        Item item1 = new Axe(null);
        Item item2 = new Axe(null);
        Item item3 = new Sword(null);
        Item item4 = new Sword(null);
        Item item5 = new Potion(null);

        // act
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.addItem(item5);
        inventory.removeItem("Axe");
        inventory.removeItem("Sword");

        // assert
        assertEquals(3, inventory.getItems().size());
        assertEquals(item2, inventory.getItems().get(0));
        assertEquals(item4, inventory.getItems().get(1));
        assertEquals(item5, inventory.getItems().get(2));
    }

}

