package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel extends BaseModel {
    private final List<ItemModel> items;

    public InventoryModel(List<ItemModel> items, int inventoryId) {
        this.items = items;
        this.id = inventoryId;
    }

    public InventoryModel(Inventory inventory) {
        List<ItemModel> itemsModels = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            itemsModels.add(new ItemModel(item));
        }
        this.items = itemsModels;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public Inventory getInventory() {
        List<Item> oldGoodItems = new ArrayList<>();
        for (ItemModel item : items) {
            oldGoodItems.add(item.getItem());
        }
        return new Inventory(oldGoodItems);
    }
}
