package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.serializer.SerializerManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class FileChooserWindow {
    private final Stage stage;
    private final boolean isImportAction;
    private final Game game;

    public FileChooserWindow(Stage stage, boolean isImportAction, Game game) {
        this.stage = stage;
        this.isImportAction = isImportAction;
        this.game = game;
    }

    public void importExportFile() throws IOException {
        String name = isImportAction ? "import" : "export";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(name);
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files",
                "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = isImportAction ? fileChooser.showOpenDialog(stage)
                : fileChooser.showSaveDialog(stage);

        if (file != null) {
            if (isImportAction) {
                game.getMain().prepareGameFromSerializedSave(file);
            } else {
                SerializerManager.serializeGameStateToFile(game.gameMap, file);
            }
        }
    }
}
