package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.List;

public interface ItemDao {
    void add(ItemModel item, int inventory_id);

    void update(ItemModel item);

    ItemModel get(int id);

    List<ItemModel> getAll();

    List<ItemModel> getAll(int inventory_id);
}
