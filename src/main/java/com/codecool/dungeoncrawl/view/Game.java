package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.characters.Person;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Game {
    private final int MAP_WIDTH_TO_DISPLAY = 25;
    private final int MAP_HEIGHT_TO_DISPLAY = 17;

    GameMap gameMap;
    GameLogic gameLogic;
    Scene scene;
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
    Label logMessage = new Label();
    ChoiceBox itemsList = new ChoiceBox();

    public void setUpReferenceLogicForGetDataFromGame(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gameMap = gameLogic.getGameMap();
    }

    public Stage generateUI(Stage primaryStage) {
        SidePanel sidePanel = new SidePanel(this);
        BottomPanel bottomPanel = new BottomPanel(this);
        VBox descriptionContainer = sidePanel.createSidePanel(healthLabel, strengthLabel, weaponLabel, heroName);
        descriptionContainer.getChildren().add(sidePanel.createUserInterface(pickUpButton, itemsList, gameMap, chooseItem, openDoor));
        descriptionContainer.getChildren().add(sidePanel.generateInventory(inventoryLabel));
        HBox logContainer = bottomPanel.createLogContainer(logMessage);
        BorderPane borderPane = new BorderPane();
        VBox mainView = new VBox();
        mainView.getChildren().addAll(canvas, logContainer);
        borderPane.setCenter(mainView);
        borderPane.setRight(descriptionContainer);
        scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(gameLogic::onKeyPressed);
        centerStage(primaryStage, borderPane);
        primaryStage.setTitle("Dungeon Crawl");
        refresh();
        return primaryStage;
    }

    private void centerStage(Stage stage, BorderPane borderPane) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - borderPane.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - borderPane.getHeight()) / 2);
    }

    public void generateLoseScreen(Player player, Person enemy) {
        Alert loseScreen = new Alert(Alert.AlertType.INFORMATION);
        loseScreen.setHeaderText("You lose!");
        loseScreen.setContentText("You are defeated by " + enemy.getName());
        loseScreen.showAndWait();
        Platform.exit();
        System.exit(0);
    }

    public void refresh() {
        StringBuilder inventoryText = new StringBuilder();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawAllTilesWithShift();

        heroName.setText("" + gameMap.getPlayer().getName().toUpperCase() + "\n");
        openDoor.setDisable(!gameLogic.isPlayerHoldingAKey() || !gameLogic.isPlayerNearDoor());
        pickUpButton.setDisable(!gameLogic.isPlayerStandingOnItem());
        createInventoryText(inventoryText);
        inventoryLabel.setText(inventoryText.toString());
        healthLabel.setText("" + gameMap.getPlayer().getCurrentHealth() + "\n");
        strengthLabel.setText("" + gameMap.getPlayer().getStrength() + "\n");
        String weaponName = gameMap.getPlayer().getWeapon() == null ? "Basic dagger" : gameMap.getPlayer().getWeapon().getName();
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
        int xPositionWithShift = x + gameMap.getPlayer().getX() - (MAP_WIDTH_TO_DISPLAY / 2);
        int yPositionWithShift = y + gameMap.getPlayer().getY() - (MAP_HEIGHT_TO_DISPLAY / 2);

        if (isCellInsideMap(xPositionWithShift, yPositionWithShift)) {
            Cell cell = gameMap.getCell(xPositionWithShift, yPositionWithShift);
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
        return xPositionWithShift >= 0 && xPositionWithShift < gameMap.getWidth();
    }

    private boolean isCellYCoordinateValid(int yPositionWithShift) {
        return yPositionWithShift >= 0 && yPositionWithShift < gameMap.getHeight();
    }

    private void createInventoryText(StringBuilder inventoryText) {
        for (String itemName : gameMap.getPlayer().getInventory().getAllItemNames()) {
            inventoryText.append(itemName).append("\n");
        }
    }
}
