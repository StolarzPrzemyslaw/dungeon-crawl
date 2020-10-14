package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.items.*;

public class ItemModel extends BaseModel {
    private String name;
    private int statistic;
    transient private Type itemType;

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

    public Item getItem() {
        switch (name) {
            case "Axe":
                return new Axe(null);
            case "Dagger":
                return new Dagger(null);
            case "Knife":
                return new Knife(null);
            case "Sword":
                return new Sword(null);
            case "Small Potion":
                return new Potion(null);
            default:
                return new Key(null);
        }
    }

    public enum Type {
        CONSUMABLE, USABLE, NONE;
    }
}
