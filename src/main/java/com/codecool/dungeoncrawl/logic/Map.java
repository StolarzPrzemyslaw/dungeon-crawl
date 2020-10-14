package com.codecool.dungeoncrawl.logic;

public enum Map {
    LEVEL1("level1.txt", 1),
    LEVEL2("level2.txt", 2),
    LEVEL3("level3.txt", 3);

    private final String name;
    private final int id;

    Map(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
