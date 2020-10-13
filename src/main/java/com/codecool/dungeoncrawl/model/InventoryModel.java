package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel extends BaseModel {
    private List<ItemModel> items;
    private int id;

    public InventoryModel() {

    }

    public InventoryModel(Inventory inventory) {
        List<ItemModel> itemsModels = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            itemsModels.add(new ItemModel(item));
        }
        this.items = itemsModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public Inventory getInventory() {
        Inventory inventory = new Inventory();
        List<Item> oldGoodItems = new ArrayList<>();
        for (ItemModel item : items) {
            oldGoodItems.add(item.getItem());
        }
        inventory.setItems(oldGoodItems);
        return inventory;
    }
}
