package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private static Inventory inventory;
    private static List<Item> items;

    @BeforeEach
    public void createEmptyInventory() {
        inventory = new Inventory();
        items = new ArrayList<>();
        prepareItems();
    }

    private void prepareItems() {
        items.add(new Key(null));
        items.add(new Axe(null));
        items.add(new Dagger(null));
        items.add(new Sword(null));
        items.add(new Potion(null));
    }

    @Test
    public void addItemToInventory_addItem_itemExist() {
        Item item = new Key(null);
        inventory.addItem(item);
        assertNotNull(inventory.getItems().get(0));
    }

    @Test
    public void addItemToInventory_addItem_itemsListSizeEquals1() {
        Item item = new Key(null);
        inventory.addItem(item);
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    public void addItemToInventory_addItem_listContainsThatItem() {
        Item item = new Key(null);
        inventory.addItem(item);
        assertTrue(inventory.contains(item.getName()));
    }

    @Test
    public void addItemToInventory_getItemThatNotExist_throwsException() {
        Executable executable = () -> assertNotNull(inventory.getItems().get(0));
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    public void addItemToInventory_addMultipleItems_listsEqual() {
        items.forEach(item -> inventory.addItem(item));
        List<Item> itemsFromInventory = inventory.getItems();
        assertEquals(items, itemsFromInventory);
    }

    @Test
    public void removeItemFromInventory_addMultipleItemsAndRemoveTwo_threeItemsLeft() {
        items.forEach(item -> inventory.addItem(item));
        inventory.removeItem(items.get(0));
        inventory.removeItem(items.get(2));
        assertEquals(3, inventory.getItems().size());
    }

    @Test
    public void removeItemFromInventory_addMultipleItemsAndRemoveTwo_itemsInRightOrder() {
        items.forEach(item -> inventory.addItem(item));
        inventory.removeItem(items.get(1));
        inventory.removeItem(items.get(3));
        items.remove(1);
        items.remove(2);
        List<Item> itemsFromInventory = inventory.getItems();
        assertEquals(items, itemsFromInventory);
    }

    @Test
    public void removeItemByName_addMultipleItemsAndRemoveOnlyTwo_threeItemsLeft() {
        items.forEach(item -> inventory.addItem(item));
        inventory.removeItem("Axe");
        inventory.removeItem("Sword");
        items.remove(1);
        items.remove(2);
        List<Item> itemsFromInventory = inventory.getItems();
        assertEquals(items, itemsFromInventory);
    }

    @Test
    public void inventory_addAllInventory_getAllItems() {
        inventory = new Inventory(items);
        List<Item> itemsFromInventory = inventory.getItems();
        assertEquals(items, itemsFromInventory);
    }
}
