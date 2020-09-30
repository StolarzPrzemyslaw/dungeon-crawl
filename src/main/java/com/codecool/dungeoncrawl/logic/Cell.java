package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.characters.Enemy;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;

public class Cell implements Drawable {
    private boolean xDirection = true;
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
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
        return gameMap.getCell(validatePosition(dx, xDirection), validatePosition(dy, !xDirection));
    }

    private int validatePosition(int shift, boolean direction) {
        int currentPosition = direction ? x : y;
        int mapSizeDirection = direction ? gameMap.getWidth() : gameMap.getHeight();

        if (currentPosition + shift >= mapSizeDirection || currentPosition + shift < 0) {
            return  currentPosition;
        } else {
            return currentPosition + shift;
        }
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

    public boolean isEmptyField() {
        return isCellTypePassable() && !isClosedDoor();
    }

    public boolean isClosedDoor() {
        return getActor() instanceof Door && !((Door) getActor()).isOpen();
    }

    public boolean isCellTypePassable() {
        return getType().isPassable();
    }

    public boolean isOccupiedByEnemy() {
        return getActor() instanceof Enemy;
    }

    public boolean isOccupiedByItem() {
        return getActor() instanceof Item;
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
}
