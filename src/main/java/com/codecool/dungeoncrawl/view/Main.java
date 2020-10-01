package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.logic.GameLogic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Button startGameButton = new Button();
    private TextField inputNameOfCharacter = new TextField();
    private Scene mainMenuScene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        mainMenuScene = new Scene(getMainMenuContainer(), 400, 300);
        setMainMenuScene();
    }

    public void setMainMenuScene() {
        stage.setScene(mainMenuScene);
        stage.setTitle("Dungeon Crawl");
        stage.show();
    }

    private VBox getMainMenuContainer() {
        VBox UIContainer = new VBox();
        UIContainer.setAlignment(Pos.CENTER);
        UIContainer.setSpacing(10);

        setUpStartGameButton();
        setUpInputFieldForName();

        Label UIName = new Label("DUNGEON CRAWL");
        UIName.setTextFill(Color.web("#472D3C"));
        UIName.setPadding(new Insets(10));

        UIContainer.getChildren().addAll(inputNameOfCharacter, startGameButton);
        return UIContainer;
    }

    private void setUpStartGameButton() {
        startGameButton.setText("New game");
        startGameButton.setOnAction(e -> {
            startNewGame();
        });
        startGameButton.setDisable(false);
    }

    private void startNewGame() {
        if (validation()) {
            Game game = new Game(this);
            String playerName = inputNameOfCharacter.getText();
            GameLogic gameLogic = new GameLogic(game, playerName);
            game.setUpReferenceLogicForGetDataFromGame(gameLogic);

            stage = game.generateUI(stage);
            stage.show();
        }
    }

    private void setUpInputFieldForName() {
        inputNameOfCharacter.setText("Player name");
        inputNameOfCharacter.setMaxWidth(200);
    }

    private boolean validation() {
        if (inputNameOfCharacter.getText().trim().isEmpty()) {
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.setStyle("-fx-background-color: #fff;");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialogPane.setContentText("Invalid player name!");

            ButtonType confirmButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(confirmButton);

            Button okButton = (Button) dialog.getDialogPane().lookupButton(confirmButton);
            okButton.setAlignment(Pos.CENTER);

            dialog.showAndWait();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
