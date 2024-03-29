package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.serializer.SerializerManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    private Stage stage;
    private final Game game = new Game(this);
    private final Button startGameButton = new Button();
    private final Button importGameStateButton = new Button();
    private final TextField inputNameOfCharacter = new TextField();
    private final ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private final Label loadLabel = new Label();
    private Scene mainMenuScene;
    GameDatabaseManager dbManager;
    private final boolean isImportAction = true;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        setupDbManager();
        this.stage = stage;
        mainMenuScene = new Scene(getMainMenuContainer(), 400, 300);
        setMainMenuScene();
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    public void setMainMenuScene() {
        stage.setScene(mainMenuScene);
        stage.setTitle("Dungeon Crawl");
        stage.show();
    }

    private VBox getMainMenuContainer() {
        VBox UIContainer = setUpMainMenuContainer();
        setUpLoadLabel();
        setUpChoiceBox();
        setUpImportGameStateButton();
        UIContainer.getChildren().addAll(inputNameOfCharacter, startGameButton, loadLabel, choiceBox, importGameStateButton);
        return UIContainer;
    }

    private VBox setUpMainMenuContainer() {
        VBox UIContainer = new VBox();
        setUpMainMenuContainerAttributes(UIContainer);
        setUpStartGameButton();
        setUpInputFieldForName();
        createAndSetUpWindowName();
        return UIContainer;
    }

    private void setUpLoadLabel() {
        loadLabel.setText("Or do you want to load a game?");
    }

    private void createAndSetUpWindowName() {
        Label UIName = new Label("DUNGEON CRAWL");
        UIName.setTextFill(Color.web("#472D3C"));
        UIName.setPadding(new Insets(10));
    }

    private void setUpImportGameStateButton() {
        importGameStateButton.setText("Import Game State");
        importGameStateButton.setOnAction(e -> {
            try {
                new FileChooserWindow(stage, isImportAction, game).importExportFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void setUpMainMenuContainerAttributes(VBox UIContainer) {
        UIContainer.setAlignment(Pos.CENTER);
        UIContainer.setSpacing(10);
    }

    private void setUpStartGameButton() {
        startGameButton.setText("New game");
        startGameButton.setOnAction(e -> startNewGame());
        startGameButton.setDisable(false);
    }

    private void setUpChoiceBox() {
        refreshChoiceBox();
        choiceBox.setOnAction(e -> loadGame());
        choiceBox.setDisable(false);
    }

    public void refreshChoiceBox() {
        choiceBox.setItems(FXCollections.observableArrayList(dbManager.getAllSaveNames()));
    }

    private void startNewGame() {
        if (validation()) {
            initializeNewGame();
            stage.show();
        }
    }

    private void loadGame() {
        List<GameStateModel> gameStates = dbManager.getAllGameStates();
        for (GameStateModel state : gameStates) {
            if (state.getSaveName().equals(choiceBox.getValue())) {
                choiceBox.getSelectionModel().clearSelection();
                prepareGameFromSaveState(state);
            }
        }
    }

    public void prepareGameFromSerializedSave(File file) throws FileNotFoundException {
        GameStateModel state = SerializerManager.deserializeGameStateGson(file);
        setUpGameGuidelines(state);
    }

    private void setUpGameGuidelines(GameStateModel state) {
        GameLogic gameLogic = new GameLogic(game, state.getPlayer().getPlayerName(), dbManager);
        game.setUpReferenceLogicForGetDataFromGame(gameLogic);
        gameLogic.loadGameFromState(state);
        game.setUpNewPlayerPosition();

        stage = game.generateUI(stage);
    }

    private void prepareGameFromSaveState(GameStateModel state) {
        setUpGameGuidelines(state);
    }

    private void initializeNewGame() {
        String playerName = inputNameOfCharacter.getText();
        GameLogic gameLogic = new GameLogic(game, playerName, dbManager);
        game.setUpReferenceLogicForGetDataFromGame(gameLogic);

        stage = game.generateUI(stage);
    }

    private void setUpInputFieldForName() {
        inputNameOfCharacter.setText("Player name");
        inputNameOfCharacter.setMaxWidth(200);
    }

    private boolean validation() {
        if (inputNameOfCharacter.getText().trim().isEmpty()) {
            Dialog<Optional<ButtonType>> dialog = new Dialog<>();
            setUpDialogPane(dialog);
            setUpConfirmValidationButton(dialog);
            dialog.showAndWait();
            return false;
        }
        return true;
    }

    private void setUpConfirmValidationButton(Dialog<Optional<ButtonType>> dialog) {
        ButtonType confirmButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButton);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(confirmButton);
        okButton.setAlignment(Pos.CENTER);
    }

    private void setUpDialogPane(Dialog<Optional<ButtonType>> dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #fff;");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialogPane.setContentText("Invalid player name!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
