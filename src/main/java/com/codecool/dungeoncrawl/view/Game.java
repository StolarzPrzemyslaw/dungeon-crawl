package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Game {
    private final int MAP_WIDTH_TO_DISPLAY = 25;
    private final int MAP_HEIGHT_TO_DISPLAY = 17;

    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            MAP_WIDTH_TO_DISPLAY * Tiles.TILE_WIDTH,
            MAP_HEIGHT_TO_DISPLAY * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    Label heroName = new Label();
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label weaponLabel = new Label();
    Label inventoryLabel = new Label();
    Button pickUpButton = new Button();
    Button chooseItem = new Button();
    Button openDoor = new Button();
    ChoiceBox itemsList = new ChoiceBox();

    public Stage generateGame(Stage primaryStage) {
        SidePanel sidePanel = new SidePanel(this);
        VBox descriptionContainer = sidePanel.createSidePanel(healthLabel, strengthLabel, weaponLabel, heroName);
        descriptionContainer.getChildren().add(sidePanel.createUserInterface(pickUpButton, itemsList, map, chooseItem, openDoor));
        descriptionContainer.getChildren().add(sidePanel.generateInventory(inventoryLabel));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(descriptionContainer);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        centerStage(primaryStage, borderPane);
        primaryStage.setTitle("Dungeon Crawl");
        return primaryStage;
    }

    public void setPlayerName(String name) {
        map.getPlayer().setPlayerName(name);
    }

    private void centerStage(Stage stage, BorderPane borderPane) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - borderPane.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - borderPane.getHeight()) / 2);
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

    private boolean isPlayerStandingOnItem() {
        return map.getPlayer().getBackgroundCellActor() instanceof Item;
    }

    public void refresh() {
        StringBuilder inventoryText = new StringBuilder();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawAllTilesWithShift();

        heroName.setText("" + map.getPlayer().getName().toUpperCase() + "\n");
        pickUpButton.setDisable(!isPlayerStandingOnItem());
        createInventoryText(inventoryText);
        inventoryLabel.setText(inventoryText.toString());
        healthLabel.setText("" + map.getPlayer().getHealth() + "\n");
        strengthLabel.setText("" + map.getPlayer().getStrength() + "\n");
        String weaponName = map.getPlayer().getWeapon() == null ? "Basic sword" : map.getPlayer().getWeapon().getName();
        weaponLabel.setText("" + weaponName + "\n");
    }

    private void drawAllTilesWithShift() {
        for (int x = 0; x < MAP_WIDTH_TO_DISPLAY; x++) {
            for (int y = 0; y < MAP_HEIGHT_TO_DISPLAY; y++) {
                drawTileWithShift(x, y);
            }
        }
    }

    private void drawTileWithShift(int x, int y) {
        int xPositionWithShift = x + map.getPlayer().getX() - (MAP_WIDTH_TO_DISPLAY / 2);
        int yPositionWithShift = y + map.getPlayer().getY() - (MAP_HEIGHT_TO_DISPLAY / 2);

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

    private boolean isCellInsideMap(int xPositionWithShift, int yPositionWithShift) {
        return isCellXCoordinateValid(xPositionWithShift) && isCellYCoordinateValid(yPositionWithShift);
    }

    private boolean isCellXCoordinateValid(int xPositionWithShift) {
        return xPositionWithShift >= 0 && xPositionWithShift < map.getWidth();
    }

    private boolean isCellYCoordinateValid(int yPositionWithShift) {
        return yPositionWithShift >= 0 && yPositionWithShift < map.getHeight();
    }

    private void createInventoryText(StringBuilder inventoryText) {
        for (String itemName : map.getPlayer().getInventory().getAllItemNames()) {
            inventoryText.append(itemName).append("\n");
        }
    }
}
