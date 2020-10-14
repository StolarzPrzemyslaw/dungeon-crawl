package com.codecool.dungeoncrawl.view;

import javafx.stage.Stage;

public class FileChooser {
    private Stage stage;
    private String operationName;

    public FileChooser(Stage stage, String operationName) {
        this.stage = stage;
        this.operationName = operationName;
    }

    public void show() {
        String name = operationName.equals("import") ? "import" : "export";
        System.out.println(name);
    }
}
