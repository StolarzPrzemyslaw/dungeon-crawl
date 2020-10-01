package com.codecool.dungeoncrawl.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomPanel {
    private Game game;

    public BottomPanel(Game game) {
        this.game = game;
    }

    public HBox createLogContainer(Label logMessage) {
        HBox logsMsg = new HBox(logMessage);
        styleLogContainer(logsMsg);
        styleLogMessage(logMessage);
        return logsMsg;
    }

    private void styleLogContainer(HBox logsMsg) {
        logsMsg.setStyle("-fx-background-color: #472D3C");
        logsMsg.setPadding(new Insets(20));
        logsMsg.setAlignment(Pos.BASELINE_CENTER);
    }

    private void styleLogMessage(Label logMessage) {
        logMessage.setText("diała \ndobrze! asdasdasdasd\ndsadasdasda\nererere");
        logMessage.setContentDisplay(ContentDisplay.CENTER);
        logMessage.setStyle("-fx-text-alignment: center; -fx-text-fill: #CFC6B8;");
    }
}
