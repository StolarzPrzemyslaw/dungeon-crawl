package com.codecool.dungeoncrawl.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Button startGameButton = new Button();
    private TextField inputNameOfCharacter = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Scene mainMenuScene = new Scene(getMainMenuContainer(), 400, 300);
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
            Game game = new Game();
            game.setPlayerName(inputNameOfCharacter.getText());
            stage = game.generateGame(stage);
            stage.show();
        }
    }

    private void setUpInputFieldForName() {
        inputNameOfCharacter.setText("Player name");
        inputNameOfCharacter.setMaxWidth(200);
    }

    private boolean validation() {
        if (inputNameOfCharacter.getText().trim().isEmpty()) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Invalid player name");
            fail.setContentText("Please type something not blank!");
            fail.showAndWait();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
