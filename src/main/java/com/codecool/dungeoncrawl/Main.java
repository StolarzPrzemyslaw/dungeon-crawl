package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.logic.actors.obstacles.Door;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final int MAP_WIDTH = 20;
    private final int MAP_HEIGHT = 20;


    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            MAP_WIDTH * Tiles.TILE_WIDTH,
            MAP_HEIGHT * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Button pickUpButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);;
        ui.add(new Label(new String(new char[38]).replace("\0", " ")), 0, 0);

        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Inventory: "), 0, 1);
        ui.add(inventoryLabel, 0, 2);

        GridPane buttonPanel = new GridPane();
        buttonPanel.setPrefHeight(50);
        buttonPanel.setPadding(new Insets(10));

        setPickUpButton();
        buttonPanel.add(pickUpButton, 0, 0);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        borderPane.setBottom(buttonPanel);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void setPickUpButton() {
        pickUpButton = new Button();
        pickUpButton.setText("Pick up item!");
        pickUpButton.setOnAction(event -> handlePickUpButtonPress());
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setVisible(false);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
    }

    private void handlePickUpButtonPress() {
        Item itemToRemoveFromMap = null;
        for (Item item : map.getItemsOnMap()) {
            if (item.getX() == map.getPlayer().getX() && item.getY() == map.getPlayer().getY()) {
                map.getPlayer().setBackgroundCellActor(null);
                itemToRemoveFromMap = addItemToInventoryFromGround(item);
                refresh();
            }
        }
        removeItemFromMapAndHideButton(itemToRemoveFromMap);
    }

    private void removeItemFromMapAndHideButton(Item itemToRemoveFromMap) {
        if (itemToRemoveFromMap != null) {
            map.getItemsOnMap().remove(itemToRemoveFromMap);
        }
        pickUpButton.setVisible(false);
    }

    private Item addItemToInventoryFromGround(Item item) {
        Item itemToRemove;
        map.getCell(item.getX(), item.getY()).setType(CellType.FLOOR);
        map.getPlayer().getItemFromTheFloor(item);
        itemToRemove = item;
        return itemToRemove;
    }

    private boolean isPlayerStandingOnItem() {
        for (Item item : map.getItemsOnMap()) {
            if (item.getX() == map.getPlayer().getX() && item.getY() == map.getPlayer().getY()) {
                return true;
            }
        }
        return false;
    }

    private void refresh() {
        StringBuilder inventoryText = new StringBuilder();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawAllTilesWithShift();
        pickUpButton.setVisible(isPlayerStandingOnItem());
        createInventoryText(inventoryText);
        inventoryLabel.setText(inventoryText.toString());
        healthLabel.setText("" + map.getPlayer().getHealth() + "\n");
    }

    private void drawAllTilesWithShift() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int xPositionWithShift = x + map.getPlayer().getX() - (MAP_WIDTH / 2);
                int yPositionWithShift = y + map.getPlayer().getY() - (MAP_HEIGHT / 2);

                if (isCellInsideMap(xPositionWithShift, yPositionWithShift)) {
                    Cell cell = map.getCell(xPositionWithShift, yPositionWithShift);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                } else {
                    Tiles.drawTile(context, null, x, y);
                }
            }
        }
    }

    private boolean isCellInsideMap(int xPositionWithShift, int yPositionWithShift) {
        return xPositionWithShift >= 0
                && yPositionWithShift >= 0
                && xPositionWithShift < map.getWidth()
                && yPositionWithShift < map.getHeight();
    }

    private void createInventoryText(StringBuilder inventoryText) {
        for (String itemName : map.getPlayer().getInventory().getAllItemNames()) {
            inventoryText.append(itemName).append("\n");
        }
    }
}
