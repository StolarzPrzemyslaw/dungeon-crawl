package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
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

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    private Stage stage;
    private final Button startGameButton = new Button();
    private final Button importGameStateButton = new Button();
    private final TextField inputNameOfCharacter = new TextField();
    private final ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private final Label loadLabel = new Label();
    private Scene mainMenuScene;
    GameDatabaseManager dbManager;

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
        choiceBox.setItems(FXCollections.observableArrayList(dbManager.getAllSaveNames()));
        choiceBox.setOnAction(e -> loadGame());
        choiceBox.setDisable(false);
    }

    private void startNewGame() {
        if (validation()) {
            initializeNewGame();
            stage.show();
        }
    }

    private void loadGame() {
        List<GameState> gameStates = dbManager.getAllGameStates();
        for (GameState state : gameStates) {
//            if (state.getSaveName().equals(choiceBox.getValue())) {
//                prepareGameFromSaveState(state);
//            }
        }
    }

    private void prepareGameFromSaveState(GameState state) {
        Game game = new Game(this);
        GameLogic gameLogic = new GameLogic(game, state.getPlayer().getPlayerName(), dbManager);
        game.setUpReferenceLogicForGetDataFromGame(gameLogic);

        gameLogic.loadMapFromState(state.getCurrentMap());

        Player player = dbManager.getPlayerBasedOnModel(state.getPlayer());
        gameLogic.loadPlayerFromState(player);

        InventoryModel inventoryModel = dbManager.getInventoryModelForPlayer(state.getPlayer().getInventoryId());
        Inventory inventory = dbManager.getInventoryBasedOnModel(inventoryModel);
        gameLogic.loadInventoryFromState(inventory);

        stage = game.generateUI(stage);
    }

    private void initializeNewGame() {
        Game game = new Game(this);
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
