package com.codecool.dungeoncrawl.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class SaveGameModal {

    public void show() {
        Stage stage = new Stage();
        VBox mainContainer = new VBox();
        Scene scene = new Scene(mainContainer, 300, 200);
        stage.setTitle("Save game");
        HBox buttonContainer = new HBox();
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        buttonContainer.getChildren().addAll(saveButton, cancelButton);
        TextField textInput = new TextField();
        textInput.setText("Name");
        mainContainer.getChildren().addAll(textInput, buttonContainer);
        setupMainContainerAttributes(mainContainer);
        stage.setScene(scene);
        stage.show();
    }

    private void setupMainContainerAttributes(VBox UIContainer) {
        UIContainer.setAlignment(Pos.CENTER);
        UIContainer.setSpacing(10);
    }
}
