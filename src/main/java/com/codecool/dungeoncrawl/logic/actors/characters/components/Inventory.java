package com.codecool.dungeoncrawl.logic.actors.characters.components;

import com.codecool.dungeoncrawl.logic.actors.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> listOfItems;

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

    public List<String> getAllItemNames() {
        List<String> listOfNames = new ArrayList<>();

        for (Item item : listOfItems) {
            listOfNames.add(item.getName());
        }

        return listOfNames;
    }
}
