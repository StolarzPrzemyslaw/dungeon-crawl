package com.codecool.dungeoncrawl.logic.actors.components;

import com.codecool.dungeoncrawl.logic.actors.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public Inventory(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeItem(String itemName) {
        removeItem(getFirstMatchingItem(itemName));
    }

    public Item getFirstMatchingItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return item;
        }
        return null;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean contains(String name) {
        return getAllItemNames().contains(name);
    }

    public List<String> getAllItemNames() {
        List<String> listOfNames = new ArrayList<>();
        items.forEach(item -> listOfNames.add(item.getName()));
        return listOfNames;
    }
}
