package com.codecool.dungeoncrawl.model;

import java.util.List;

public class InventoryModel extends BaseModel {
    private List<ItemModel> items;
    private int id;

    public InventoryModel(List<ItemModel> items) {
        this.items = items;
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
}
