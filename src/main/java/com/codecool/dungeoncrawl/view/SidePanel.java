package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.view.Main;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
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
    private Main main;

    public SidePanel(Main main) {
        this.main = main;
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

    public VBox createUserInterface(Button pickUpButton, ChoiceBox itemsList, GameMap map, Button chooseItem, Button openDoor) {
        VBox UIContainer = new VBox();
        UIContainer.setStyle("-fx-border-color:#472D3C; -fx-padding: 15;");
        UIContainer.setPadding(new Insets(20));

        Label UIName = new Label("MANAGE PLAYER OPTION");
        UIName.setTextFill(Color.web("#472D3C"));
        UIName.setPadding(new Insets(10));

        HBox chooseItems = generateChooseSection(itemsList);
        HBox useItem = generateChooseButton(chooseItem);
        HBox buttonPanel = generateButtonPanel(pickUpButton, map, openDoor);

        UIContainer.getChildren().addAll(UIName, chooseItems, useItem, buttonPanel);
        return UIContainer;
    }

    private HBox generateButtonPanel(Button pickUpButton, GameMap map, Button openDoor) {
        HBox buttonPanel = new HBox();
        styleHBox(buttonPanel);
        setPickUpButton(pickUpButton, map);
        openDoor.setText("Open Door");
        openDoor.setDisable(true);
        buttonPanel.getChildren().addAll(openDoor, pickUpButton);
        return buttonPanel;
    }

    private HBox generateChooseButton(Button chooseItem) {
        HBox useItem = new HBox(chooseItem);
        chooseItem.setText("Use selected item");
        useItem.setPrefWidth(300);
        useItem.setPadding(new Insets(10));
        useItem.setAlignment(Pos.BASELINE_CENTER);
        return useItem;
    }

    private HBox generateChooseSection(ChoiceBox itemsList) {
        HBox chooseItems = new HBox();
        Label itemLabel = new Label("Choose item: ");
        itemsList.setPrefWidth(150);
        itemLabel.setTextFill(Color.web("#472D3C"));
        itemLabel.setStyle("-fx-padding: 4,0,0,0;");
        chooseItems.getChildren().addAll(itemLabel, itemsList);
        return chooseItems;
    }

    private void setPickUpButton(Button pickUpButton, GameMap map) {
        pickUpButton.setText("Pick up item!");
        pickUpButton.setOnAction(event -> handlePickUpButtonPress(map, pickUpButton));
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setDisable(true);
    }

    private void handlePickUpButtonPress(GameMap map, Button pickUpButton) {
        map.getPlayer().getItemFromTheFloor((Item) map.getPlayer().getBackgroundCellActor());
        map.getPlayer().setBackgroundCellActor(null);
        pickUpButton.setDisable(true);
        main.refresh();
    }
}
