package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
//    SidePanel sidePanel = new SidePanel();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label weaponLabel = new Label();
    Label inventoryLabel = new Label();
    Button pickUpButton = new Button();
    Button chooseItem = new Button();
    Button openDoor = new Button();
    ChoiceBox itemsList = new ChoiceBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SidePanel sidePanel = new SidePanel();
        VBox descriptionContainer = sidePanel.createSidePanel(healthLabel, strengthLabel, weaponLabel);
        descriptionContainer.getChildren().add(sidePanel.createUserInterface(pickUpButton, itemsList, map, chooseItem, openDoor));
        descriptionContainer.getChildren().add(sidePanel.generateInventory(inventoryLabel));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(descriptionContainer);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case D:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
    }

//    private void handlePickUpButtonPress() {
//        Item itemToRemoveFromMap = null;
//        for (Item item : map.getItemsOnMap()) {
//            if (item.getX() == map.getPlayer().getX() && item.getY() == map.getPlayer().getY()) {
//                itemToRemoveFromMap = addItemToInventoryFromGround(item);
//                refresh();
//            }
//        }
//        removeItemFromMapAndHideButton(itemToRemoveFromMap);
//    }

//    private void removeItemFromMapAndHideButton(Item itemToRemoveFromMap) {
//        if (itemToRemoveFromMap != null) {
//            map.getItemsOnMap().remove(itemToRemoveFromMap);
//        }
//        pickUpButton.setVisible(false);
//    }
//
//    private Item addItemToInventoryFromGround(Item item) {
//        Item itemToRemove;
//        map.getCell(item.getX(), item.getY()).setType(CellType.FLOOR);
//        map.getPlayer().getItemFromTheFloor(item);
//        itemToRemove = item;
//        return itemToRemove;
//    }

    private boolean isPlayerStandingOnItem() {
        for (Item item : map.getItemsOnMap()) {
            if (item.getX() == map.getPlayer().getX() && item.getY() == map.getPlayer().getY()) {
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        StringBuilder inventoryText = new StringBuilder();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

        pickUpButton.setVisible(isPlayerStandingOnItem());
        createInventoryText(inventoryText);
        inventoryLabel.setText(inventoryText.toString());
        healthLabel.setText("" + map.getPlayer().getHealth() + "\n");
        strengthLabel.setText("aaaa" + "\n");
        weaponLabel.setText("" + map.getPlayer().getWeapon() + "\n");
    }

    private void createInventoryText(StringBuilder inventoryText) {
        for (String itemName : map.getPlayer().getInventory().getAllItemNames()) {
            inventoryText.append(itemName).append("\n");
        }
    }
}
