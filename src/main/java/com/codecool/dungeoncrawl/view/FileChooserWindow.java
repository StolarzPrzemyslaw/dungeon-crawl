package com.codecool.dungeoncrawl.view;

import com.codecool.dungeoncrawl.serializer.SerializerManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("json files(*.json)",
                "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = isImportAction ? fileChooser.showOpenDialog(stage)
                : fileChooser.showSaveDialog(stage);

        if (file != null) {
            if (name.equals("import")) {
                SerializerManager.deserializeGameStateGson(file.getAbsolutePath());
            } else {
                SerializerManager.serializeGameStateToFile(game.gameMap, file.getAbsolutePath());
            }
        }
    }
}
