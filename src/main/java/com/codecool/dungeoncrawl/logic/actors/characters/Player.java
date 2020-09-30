package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;

public class Player extends Person {

    private final Inventory inventory;
    private Weapon chosenWeapon;

    public Player(Cell cell) {
        super(cell);
        this.name = "Hero Name";
        this.strength = 5;
        this.health = 10;
        this.inventory = new Inventory();
    }

    public Player(Cell cell, Inventory inventory) {
        super(cell);
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void getItemFromTheFloor(Item obtainedItem) {
        inventory.addItemToInventory(obtainedItem);
    }

    public String getTileName() {
        return "player";
    }

    public void setWeapon(Weapon chosenWeapon) {
        this.chosenWeapon = chosenWeapon;
    }

    public Weapon getWeapon() {
        return chosenWeapon;
    }

    public int getStrengthBasedOnWeapon() {
        if (chosenWeapon != null) {
            return chosenWeapon.getStatistic() + strength;
        }
        return strength;
    }

    @Override
    public void actionAfterDefeat(Actor actorWhichDefeatedPlayer) {
        // show message after defeat
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getNextCell(dx, dy);
        if (nextCell.isEmptyField() && isEncounterDone(nextCell)) {
            updatePosition(nextCell);
        }
        checkDoorCondition(nextCell);
    }

    private void checkDoorCondition(Cell nextCell) {
        if (nextCell.isClosedDoor()) {
            if (isItemInInventory("Key")) {
                openDoor(nextCell);
                removeItemFromInventory("Key");
            }
        }
    }

    private void openDoor(Cell nextCell) {
        ((Door) nextCell.getActor()).open();
    }

    private void removeItemFromInventory(String itemName) {
        inventory.removeItemByName(itemName);
    }

    private boolean isItemInInventory(String itemName) {
        return inventory.containsItem(itemName);
    }
}
