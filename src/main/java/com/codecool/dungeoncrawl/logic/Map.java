package com.codecool.dungeoncrawl.logic;

public enum Map {
    LEVEL1("level1.txt", 1, 2, 2),
    LEVEL2("level2.txt", 2, 7, 13),
    LEVEL3("level3.txt", 3,14, 24 ),
    LEVEL4("level4.txt", 4, 8, 7);

    private final String name;
    private final int id;
    private final int startX;
    private final int startY;

    Map(String name, int id, int startX, int startY) {
        this.name = name;
        this.id = id;
        this.startX = startX;
        this.startY = startY;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
