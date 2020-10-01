package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty") {
        @Override
        public boolean isPassable() {
            return false;
        }
    },
    WALL("wall") {
        @Override
        public boolean isPassable() {
            return false;
        }
    },
    RIVER("river") {
        @Override
        public boolean isPassable() {
            return false;
        }
    },
    FLOOR("floor"),
    BRIDGE("bridge");

    private final String tileName;

    public boolean isPassable() {
        return true;
    }

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
