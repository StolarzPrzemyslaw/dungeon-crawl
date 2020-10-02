package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private final Game game;

    public SidePanel(Game game) {
        this.game = game;
    }

    public VBox createSidePanel(Label healthLabel, Label strengthLabel, Label weaponLabel, Label heroName) {
        VBox sidePanel = new VBox();
        styleSidePanel(sidePanel);

        HBox hero = setHeroLabel(heroName);

        sidePanel.getChildren().add(hero);
        sidePanel.getChildren().add(generateHeroDescription(healthLabel, strengthLabel, weaponLabel));
        return sidePanel;
    }

    private void styleSidePanel(VBox sidePanel) {
        sidePanel.setPrefWidth(300);
        sidePanel.setSpacing(20);
        sidePanel.setPadding(new Insets(10));
    }

    private HBox setHeroLabel(Label heroName) {
        HBox hero = new HBox();
        styleHBox(hero);

        heroName.setTextFill(Color.web("#472D3C"));

        hero.getChildren().add(heroName);
        return hero;
    }

    private void styleHBox(HBox hero) {
        hero.setPrefWidth(300);
        hero.setAlignment(Pos.BASELINE_CENTER);
    }

    private GridPane generateHeroDescription(Label healthLabel, Label strengthLabel, Label weaponLabel) {
        GridPane heroStatistic = new GridPane();
        styleGridPane(heroStatistic, "-fx-background-color:#472D3C; ");

        createLabel("HERO STATISTICS", heroStatistic, new Label());
        createLabel("Health: ", heroStatistic, healthLabel);
        createLabel("Strength: ", heroStatistic, strengthLabel);
        createLabel("Weapon: ", heroStatistic, weaponLabel);

        return heroStatistic;
    }

    private void createLabel(String title, GridPane container, Label labelValue) {
        Label label = new Label(title);
        label.setTextFill(Color.BEIGE);
        labelValue.setTextFill(Color.BEIGE);
        container.add(label, PARAMETERS_POSITION, heroStatsRowNumber);
        container.add(labelValue, 1, heroStatsRowNumber);
        heroStatsRowNumber++;
    }

    public GridPane generateInventory(Label inventoryLabel) {
        GridPane inventory = new GridPane();
        styleGridPane(inventory, "-fx-background-color:#472D3C;");

        Label inventoryDescription = new Label("INVENTORY");
        inventoryDescription.setTextFill(Color.BEIGE);
        inventoryLabel.setTextFill(Color.BEIGE);

        inventory.add(inventoryDescription, PARAMETERS_POSITION, PARAMETERS_POSITION);
        inventory.add(inventoryLabel, PARAMETERS_POSITION, inventoryItemRowNumber);
        inventoryItemRowNumber++;
        return inventory;
    }

    private void styleGridPane(GridPane pane, String s) {
        pane.setStyle(s);
        pane.setPadding(new Insets(10));
    }

    public VBox createUserInterface(Button pickUpButton, ChoiceBox<String> itemsList, GameMap map, Button chooseItem, Button openDoor) {
        VBox UIContainer = new VBox();
        UIContainer.setStyle("-fx-border-color:#472D3C; -fx-padding: 15;");
        UIContainer.setPadding(new Insets(20));

        Label UIName = new Label("MANAGE PLAYER OPTION");
        UIName.setTextFill(Color.web("#472D3C"));
        UIName.setPadding(new Insets(10));

        HBox chooseItems = generateChooseSection(itemsList, map);
        HBox useItem = createUseItemPanel(chooseItem, map, itemsList);
        HBox buttonPanel = generateButtonPanel(pickUpButton, map, openDoor, itemsList);

        UIContainer.getChildren().addAll(UIName, chooseItems, useItem, buttonPanel);
        return UIContainer;
    }

    private HBox generateButtonPanel(Button pickUpButton, GameMap map, Button openDoor, ChoiceBox<String> itemLists) {
        HBox buttonPanel = new HBox();
        styleHBox(buttonPanel);
        setPickUpButton(pickUpButton, map, itemLists);
        setOpenDoorButton(openDoor, map);
        buttonPanel.getChildren().addAll(openDoor, pickUpButton);
        return buttonPanel;
    }

    private HBox createUseItemPanel(Button useItemButton, GameMap map, ChoiceBox<String> itemsList) {
        HBox useItemPanel = new HBox(useItemButton);
        setUseItemPanel(useItemPanel);
        setUseItemButton(useItemButton, itemsList, map);
        return useItemPanel;
    }

    private void setUseItemPanel(HBox useItemPanel) {
        useItemPanel.setPrefWidth(300);
        useItemPanel.setPadding(new Insets(10));
        useItemPanel.setAlignment(Pos.BASELINE_CENTER);
    }

    private void setUseItemButton(Button useItemButton, ChoiceBox<String> itemsList, GameMap map) {
        useItemButton.setText("Use selected item");
        useItemButton.setOnAction(event -> handleUseItemButton(map, itemsList));
    }

    private void handleUseItemButton(GameMap map, ChoiceBox<String> itemsList) {
        Item item = getSelectedItem(map, itemsList);
        if (isType(Usable.class, item)) useItem(item, map);
        else if (isType(Consumable.class, item)) consumeItem(item, map, itemsList);
        finalizeUseItemEvent(itemsList);
    }

    private void finalizeUseItemEvent(ChoiceBox<String> itemsList) {
        itemsList.getSelectionModel().clearSelection();
        game.refresh();
    }

<<<<<<< HEAD
    private void useItem(Item item, GameMap map) {
        ((Usable) item).use(map.getPlayer());
=======
    private void armPlayerWithItem(GameMap map, ChoiceBox itemsList, Item item) {
        if (item instanceof Weapon) {
            map.getPlayer().setWeapon((Weapon) item);
            itemsList.getItems().remove(item.getName());
            map.getPlayer().getInventory().removeItemByName(item.getName());
            if (!map.getPlayer().getInventory().contains("Basic dagger")) {
                itemsList.getItems().add("Basic dagger");
            }
        } else if (item instanceof Potion) {
            System.out.println("Potion");
        }
>>>>>>> 56c2797c9aad9510b85ea5fd87cdf77ef0465072
    }

    private void consumeItem(Item item, GameMap map, ChoiceBox<String> itemsList) {
        itemsList.getItems().remove(item.getName());
        ((Consumable) item).consume(map.getPlayer());
    }

    private Item getSelectedItem(GameMap map, ChoiceBox<String> itemsList) {
        return map.getPlayer().getInventory().getItemByName(itemsList.getValue());
    }

    private HBox generateChooseSection(ChoiceBox<String> itemsList, GameMap map) {
        HBox chooseItems = new HBox();
        Label itemLabel = new Label("Choose item: ");
        itemsList.setItems(getUsableItemsNames(map));
        itemsList.setPrefWidth(150);
        itemLabel.setTextFill(Color.web("#472D3C"));
        itemLabel.setStyle("-fx-padding: 4,0,0,0;");
        chooseItems.getChildren().addAll(itemLabel, itemsList);
        return chooseItems;
    }

    private void setPickUpButton(Button pickUpButton, GameMap map, ChoiceBox<String> itemsList) {
        pickUpButton.setText("Pick up item!");
        pickUpButton.setOnAction(event -> handlePickUpButtonPress(map, pickUpButton, itemsList));
        pickUpButton.setDisable(true);
    }

    private void setOpenDoorButton(Button openDoorButton, GameMap map) {
        openDoorButton.setText("Open Door");
        openDoorButton.setDisable(true);
        openDoorButton.setOnAction(event -> handleOpenDoorButton(map, openDoorButton));
        openDoorButton.setDisable(true);
    }

    private void handleOpenDoorButton(GameMap map, Button openDoorButton) {
        map.getPlayer().getCell().openDoorNextToPlayer();
        game.displayLog("You have opened a door!");
        openDoorButton.setDisable(true);
        game.refresh();
    }

    private void handlePickUpButtonPress(GameMap map, Button pickUpButton, ChoiceBox<String> itemsList) {
        Item item = (Item) map.getPlayer().getBackgroundCellActor();
        if (isType(Usable.class, item) || isType(Consumable.class, item)) {
            itemsList.getItems().add(item.toString());
        }
        item.showObtainMessage(game);
        map.getPlayer().getInventory().addItemToInventory(item);
        map.getPlayer().setBackgroundCellActor(null);
        pickUpButton.setDisable(true);
        game.refresh();
    }

    private ObservableList<String> getUsableItemsNames(GameMap map) {
        ObservableList<String> usableItemsNames = FXCollections.observableArrayList();
        map.getPlayer().getInventory().getItems().forEach(item -> {
            if (isType(Usable.class, item)) usableItemsNames.add(item.getName());
        });
        return usableItemsNames;
    }

    private boolean isType(Class<?> itemClass, Item item) {
        return itemClass.isInstance(item);
    }
}
