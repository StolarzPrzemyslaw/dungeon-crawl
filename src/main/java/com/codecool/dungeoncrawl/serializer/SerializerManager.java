package com.codecool.dungeoncrawl.serializer;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.items.Item;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SerializerManager {
    private InventoryModel inventory;
    private PlayerModel playerModel;
    private ItemModel itemModel;

    public static String serializeGameStateGson(GameMap map) {
        // TODO add gameState to serialization
        InventoryModel inventoryModel = new InventoryModel();
        List<ItemModel> items = new ArrayList<>();
        for (Item item: map.getPlayer().getInventory().getItems()) {
            items.add(new ItemModel(item));
        }
        inventoryModel.setItems(items);
        PlayerModel playerModel = new PlayerModel(map.getPlayer(), inventoryModel, new ItemModel(map.getPlayer().getWeapon()));

        String serializePlayerModel = new Gson().toJson(playerModel);
        return serializePlayerModel;
    }

    public static void deserializeGameStateGson(String jsonFile) {
        // TODO deserialize json file
    }

}
