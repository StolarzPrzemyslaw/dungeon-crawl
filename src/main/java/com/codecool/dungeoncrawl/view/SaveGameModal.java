package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class SaveGameModal {
    private final Stage stage;
    private final GameDatabaseManager gameDbManager;
    private final GameMap map;
    private TextField textInput;

    public SaveGameModal(GameDatabaseManager gameDbManager, GameMap map) {
        this.stage = getStage();
        this.gameDbManager = gameDbManager;
        this.map = map;
    }

    public void show() {
        stage.show();
    }

    private Stage getStage() {
        Stage stage = new Stage();
        setupStage(stage);
        return stage;
    }

    private void setupStage(Stage stage) {
        stage.setTitle("Save game");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(getScene());
    }

    private Scene getScene() {
        int sceneWidth = 300;
        int sceneHeight = 150;
        return new Scene(getMainContainer(), sceneWidth, sceneHeight);
    }

    private VBox getMainContainer() {
        VBox mainContainer = new VBox();
        setupMainContainer(mainContainer);
        return mainContainer;
    }

    private void setupMainContainer(VBox mainContainer) {
        setupMainContainerAttributes(mainContainer);
        mainContainer.getChildren().addAll(getTextInput(), getButtonContainer());
    }

    private void setupMainContainerAttributes(VBox mainContainer) {
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setSpacing(20);
    }

    private TextField getTextInput() {
        textInput = new TextField();
        setupTextInput(textInput);
        return textInput;
    }

    private void setupTextInput(TextField textInput) {
        textInput.setText("Name");
        textInput.setMaxWidth(150);
        textInput.setTooltip(new Tooltip("Please insert save name."));
    }

    private HBox getButtonContainer() {
        HBox buttonContainer = new HBox();
        setupButtonContainer(buttonContainer);
        return buttonContainer;
    }

    private void setupButtonContainer(HBox buttonContainer) {
        setupButtonContainerAttributes(buttonContainer);
        buttonContainer.getChildren().addAll(getSaveButton(), getCancelButton());
    }

    private void setupButtonContainerAttributes(HBox buttonContainer) {
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(20);
    }

    private Button getSaveButton() {
        Button saveButton = new Button();
        setupSaveButton(saveButton);
        return saveButton;
    }

    private void setupSaveButton(Button saveButton) {
        saveButton.setText("Save");
        saveButton.setTooltip(new Tooltip("Press here to save game."));
        saveButton.setOnAction(event -> handleSaveButton());
    }

    private Button getCancelButton() {
        Button cancelButton = new Button();
        setupCancelButton(cancelButton);
        return cancelButton;
    }

    private void setupCancelButton(Button cancelButton) {
        cancelButton.setText("Cancel");
        cancelButton.setTooltip(new Tooltip("Press here to cancel."));
        cancelButton.setOnAction(cancelEvent -> close());
    }

    private void close() {
        stage.close();
    }

    private void handleSaveButton() {
        String saveName = textInput.getText();
        if (gameDbManager.checkValidSaveName(saveName)) {
            gameDbManager.saveGameState(map, saveName);
        } else {
            new SaveGameConfirmModal(gameDbManager, map, saveName).show();
        }
        close();
    }
}
