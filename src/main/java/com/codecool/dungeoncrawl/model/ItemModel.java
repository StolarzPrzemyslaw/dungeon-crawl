package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.items.Consumable;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.items.Usable;

public class ItemModel extends BaseModel {
    private String name;
    private int statistic;
    private Type itemType;
    private int id;

    public ItemModel(Item item) {
        this.name = item.getName();
        this.statistic = item.getStatistic();
        if (item instanceof Usable) {
            this.itemType = Type.USABLE;
        } else if (item instanceof Consumable) {
            this.itemType = Type.CONSUMABLE;
        } else {
            this.itemType = Type.NONE;
        }
    }

    public ItemModel(int id, String name, int statistic, String itemType) {
        this.id = id;
        this.name = name;
        this.statistic = statistic;
        if (itemType.equals("usable")) {
            this.itemType = Type.USABLE;
        } else if (itemType.equals("consumable")) {
            this.itemType = Type.CONSUMABLE;
        } else {
            this.itemType = Type.NONE;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public int getStatistic() {return statistic;}

    public void setStatistic(int statistic) {this.statistic = statistic;}

    public Type getItemType() {return itemType;}

    public void setItemType(Type itemType) {this.itemType = itemType;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Type {
        CONSUMABLE, USABLE, NONE;
    }
}
