package com.codecool.dungeoncrawl.serializer;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializerManager {

    public static void serializeGameStateToFile(GameMap map, String filePath) throws IOException {
        // TODO add gameState to serialization
        InventoryModel inventoryModel = new InventoryModel();
        List<ItemModel> items = new ArrayList<>();
        for (Item item: map.getPlayer().getInventory().getItems()) {
            items.add(new ItemModel(item));
        }
        inventoryModel.setItems(items);
        PlayerModel playerModel = new PlayerModel(map.getPlayer(), inventoryModel, new ItemModel(map.getPlayer().getWeapon()));

        new Gson().toJson(playerModel, new FileWriter(filePath));
    }

    public static GameStateModel deserializeGameStateGson(String filePath) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(filePath), GameStateModel.class);
    }

}
