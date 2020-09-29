package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Item;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class SidePanel {

    private int heroStatsRowNumber = 0;
    private final int PARAMETERS_POSITION = 0;
    private int inventoryItemRowNumber = 1;
    private Main main = new Main();

    public VBox createSidePanel(Label healthLabel, Label strengthLabel, Label weaponLabel) {
        VBox sidePanel = new VBox();
        sidePanel.setPrefWidth(300);
        sidePanel.setPadding(new Insets(10));

        sidePanel.getChildren().add(generateHeroDescription(healthLabel, strengthLabel, weaponLabel));
        return sidePanel;
    }

    private GridPane generateHeroDescription(Label healthLabel, Label strengthLabel, Label weaponLabel) {
        GridPane heroStatistic = new GridPane();
        heroStatistic.setStyle("-fx-background-color:#472D3C; -fx-padding: 5;");

        createLabel("HERO STATISTICS", heroStatistic, new Label());
        createLabel("Health: ", heroStatistic, healthLabel);
        createLabel("Strength: ", heroStatistic, strengthLabel);
        createLabel("Weapon: ", heroStatistic, weaponLabel);

        return heroStatistic;
    }

    private void createLabel(String title, GridPane container, Label args) {
        Label label = new Label(title);
        label.setTextFill(Color.BEIGE);
        args.setTextFill(Color.BEIGE);
        container.add(label, PARAMETERS_POSITION, heroStatsRowNumber);
        container.add(args, 1, heroStatsRowNumber);
        heroStatsRowNumber++;
    }

    public GridPane generateInventory(Label inventoryLabel) {
        GridPane inventory = new GridPane();
        Label inventoryDescription = new Label("INVENTORY");
        inventoryDescription.setTextFill(Color.BEIGE);
        inventoryLabel.setTextFill(Color.BEIGE);
        inventory.setStyle("-fx-background-color:#472D3C; -fx-padding: 5;");
        inventory.add(inventoryDescription, PARAMETERS_POSITION, PARAMETERS_POSITION);
        inventory.add(inventoryLabel, PARAMETERS_POSITION, inventoryItemRowNumber);
        inventoryItemRowNumber++;
        return inventory;
    }

    public VBox createUserInterface(Button pickUpButton, ChoiceBox itemsList, GameMap map, Button chooseItem, Button openDoor) {
        VBox UIContainer = new VBox();
        UIContainer.setPadding(new Insets(20));
        UIContainer.setStyle("-fx-border-color:#472D3C; -fx-padding: 15;");

        Label UIName = new Label("MANAGE PLAYER OPTION");
        UIName.setTextFill(Color.web("#472D3C"));

        HBox chooseItems = new HBox();
        Label itemLabel = new Label("Choose item: ");
        itemLabel.setTextFill(Color.web("#472D3C"));
        itemLabel.setStyle("-fx-padding: 4,0,0,0;");
        chooseItems.getChildren().addAll(itemLabel, itemsList);

        chooseItem.setText("Choose Selected Item");

        VBox buttonPanel = new VBox();
        setPickUpButton(pickUpButton, map);
        openDoor.setText("Open Door");
        buttonPanel.getChildren().addAll(openDoor, pickUpButton);

        UIContainer.getChildren().addAll(UIName, chooseItems, chooseItem, buttonPanel);
        return UIContainer;
    }

    private void setPickUpButton(Button pickUpButton, GameMap map) {
        pickUpButton.setText("Pick up item!");
        pickUpButton.setOnAction(event -> handlePickUpButtonPress(map, pickUpButton));
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setVisible(false);
    }

    private void handlePickUpButtonPress(GameMap map, Button pickUpButton) {
        Item itemToRemoveFromMap = null;
        for (Item item : map.getItemsOnMap()) {
            if (item.getX() == map.getPlayer().getX() && item.getY() == map.getPlayer().getY()) {
                itemToRemoveFromMap = addItemToInventoryFromGround(item, map);
                main.refresh();
            }
        }
        removeItemFromMapAndHideButton(itemToRemoveFromMap, map, pickUpButton);
    }

    private void removeItemFromMapAndHideButton(Item itemToRemoveFromMap, GameMap map, Button pickUpButton) {
        if (itemToRemoveFromMap != null) {
            map.getItemsOnMap().remove(itemToRemoveFromMap);
        }
        pickUpButton.setVisible(false);
    }

    private Item addItemToInventoryFromGround(Item item, GameMap map) {
        Item itemToRemove;
        map.getCell(item.getX(), item.getY()).setType(CellType.FLOOR);
        map.getPlayer().getItemFromTheFloor(item);
        itemToRemove = item;
        return itemToRemove;
    }
}
