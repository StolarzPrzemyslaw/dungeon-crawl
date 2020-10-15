package com.codecool.dungeoncrawl.serializer;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.io.*;
import java.sql.Date;
import java.util.Arrays;

public class SerializerManager {

    public static void serializeGameStateToFile(GameMap map, File file) throws IOException {
        GameStateModel gameStateModel = generateGameStateModel(map, file);
        FileWriter writer = new FileWriter(getFileName(file));
        new Gson().toJson(gameStateModel, writer);
        writer.flush();
        writer.close();
    }

    private static File getFileName(File file) {
        File fileWithExtension;
        if (!file.getName().toLowerCase().endsWith(".json")) {
            fileWithExtension = new File(file.getAbsolutePath() + ".json");
        } else {
            fileWithExtension = new File(file.getAbsolutePath());
        }
        return fileWithExtension;
    }

    private static GameStateModel generateGameStateModel(GameMap map, File file) {
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        playerModel.getPlayer().setPlayerCell(map.getPlayer().getCell());
        Map currentMap = getCurrentMap(map);

        return new GameStateModel(currentMap, new Date(System.currentTimeMillis()), playerModel, file.getName());
    }

    private static Map getCurrentMap(GameMap map) {
        return Arrays.stream(Map.values()).
                filter(mapEntry -> mapEntry.getId() == map.getLevelId()).
                findFirst().
                orElseThrow(() -> new RuntimeException("Error while retrieving map with id: " + map.getLevelId()));
    }

    public static GameStateModel deserializeGameStateGson(File file) throws FileNotFoundException {
        FileReader reader = new FileReader(file.getAbsolutePath());
        return new Gson().fromJson(reader, GameStateModel.class);
    }

}
