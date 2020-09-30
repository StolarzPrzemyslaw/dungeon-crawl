package com.codecool.dungeoncrawl.logic.actors.characters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.items.Weapon;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Player extends Person {

    private Inventory inventory;
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

    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public void actionAfterDefeat(Actor actorWhichDefeatedPlayer) {
        Alert loseScreen = new Alert(Alert.AlertType.INFORMATION);
        loseScreen.setContentText("You lose! You are defeated by " + actorWhichDefeatedPlayer.getName());
        loseScreen.showAndWait();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getNextCell(dx, dy);
        if (isNextFieldEmpty(nextCell) && isEncounterDone(nextCell)) {
            updatePosition(nextCell);
        }
        checkDoorCondition(nextCell);
    }

    private void checkDoorCondition(Cell nextCell) {
        if (isNextFieldClosedDoor(nextCell)) {
            if (isKeyInInventory()) {
                openDoor(nextCell);
                removeKey();
            }
        }
    }

    private void openDoor(Cell nextCell) {
        ((Door) nextCell.getActor()).open();
    }

    private void removeKey() {
        inventory.removeItemFromInventory(inventory.getItemByName("Key"));
    }

    private boolean isKeyInInventory() {
        return inventory.getAllItemNames().contains("Key");
    }
}
