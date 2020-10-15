package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.items.*;

public class ItemModel extends BaseModel {
    private final String name;
    private final int statistic;
    transient final private Type itemType;

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

    public int getStatistic() {return statistic;}

    public Type getItemType() {return itemType;}

    public Item getItem() {
        switch (name) {
            case "Basic dagger":
                return new Dagger(null);
            case "Axe":
                return new Axe(null);
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
