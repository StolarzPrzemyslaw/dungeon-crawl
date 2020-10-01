package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> listOfItems;

    public Inventory() {
        this.listOfItems = new ArrayList<>();
    }

    public Inventory(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public void addItemToInventory(Item item) {
        listOfItems.add(item);
    }

    public void removeItemFromInventory(Item item) {
        listOfItems.remove(item);
    }

    public Item getItemByName(String itemName) {
        for (Item item : listOfItems) {
            if (item.getName().equals(itemName)) return item;
        }
        return null;
    }

    public void removeItemByName(String itemName) {
        removeItemFromInventory(getItemByName(itemName));
    }

    public boolean contains(String itemName) {
        return getAllItemNames().contains(itemName);
    }

    public List<String> getAllItemNames() {
        List<String> listOfNames = new ArrayList<>();
        listOfItems.forEach(item -> listOfNames.add(item.getName()));
        return listOfNames;
    }

    public List<Item> getItems() {
        return listOfItems;
    }
}
