package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private final GameMap gameMap;
    private final int x;
    private final int y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        boolean isX = true;
        return gameMap.getCell(validatePosition(dx, isX), validatePosition(dy, !isX));
    }

    private int validatePosition(int shift, boolean direction) {
        int currentPosition = direction ? x : y;
        int mapSizeDirection = direction ? gameMap.getWidth() : gameMap.getHeight();
        return (currentPosition + shift >= mapSizeDirection || currentPosition + shift < 0) ? currentPosition : currentPosition + shift;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMovePossible() {
        return isCellTypePassable() && isOpenDoor();
    }

    public boolean isEnemyMovePossible() {
        return isCellTypePassable() && isOpenDoor() && !isOccupiedByClass(Enemy.class);
    }

    public boolean isOpenDoor() {
        return !isOccupiedByClass(Door.class) || ((Door) getActor()).isOpen();
    }

    public boolean isCellTypePassable() {
        return getType().isPassable();
    }

    public boolean isOccupiedByClass(Class<?> actorClass) {
        return actorClass.isInstance(getActor());
    }

    public boolean isDoorOneOfTheNeighbors() {
        return isDoorNextToPlayer(-1, 0) != null
                || isDoorNextToPlayer(1, 0) != null
                || isDoorNextToPlayer(0, -1) != null
                || isDoorNextToPlayer(0, 1) != null;
    }

    public Door isDoorNextToPlayer(int dx, int dy) {
        return getNeighbor(dx, dy).getActor() instanceof Door ? (Door) getNeighbor(dx, dy).getActor() : null;
    }

    public void openDoorNextToPlayer() {
        openDoorNextToPlayerBasedOnNextCell(-1, 0);
        openDoorNextToPlayerBasedOnNextCell(1, 0);
        openDoorNextToPlayerBasedOnNextCell(0, -1);
        openDoorNextToPlayerBasedOnNextCell(0, 1);
        gameMap.getPlayer().getInventory().removeItemByName("Key");
    }

    public void openDoorNextToPlayerBasedOnNextCell(int dx, int dy) {
        if (isDoorNextToPlayer(dx, dy) != null) {
            ((Door) getNeighbor(dx, dy).getActor()).open();
        }
    }

    public boolean isBonfireOneOfTheNeighbours() {
        return isCellTypeBonfire(-1, 0)
                || isCellTypeBonfire(1, 0)
                || isCellTypeBonfire(0, -1)
                || isCellTypeBonfire(0, 1);
    }

    private boolean isCellTypeBonfire(int dx, int dy) {
        return getNeighbor(dx, dy).getType().equals(CellType.BONFIRE);
    }
}
