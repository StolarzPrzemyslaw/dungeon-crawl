package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private ItemDao itemDao;
    private InventoryDao inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
    }

    public void saveGameState(GameMap map) {
        // How to use InventoryModel return values
//        InventoryModel inventory = saveInventoryReturnInventoryId(map.getPlayer().getInventory());
//        System.out.println("Inventory id: " + inventory.getId());
//        inventory.getItems().forEach(item -> System.out.println(item.getName() + ": " + item.getId()));
        // ------------------------------------ //
    }

    public void savePlayer(Player player) {
        InventoryModel inventory = saveInventoryReturnInventoryId(player.getInventory());
        PlayerModel model = new PlayerModel(player);
        model.setInventoryId(inventory.getId());
        model.setWeaponId(getWeaponInUseId(player, inventory));
        playerDao.add(model);
    }

    private int getWeaponInUseId(Player player, InventoryModel inventory) {
        int weaponId = 1;
        for (ItemModel item: inventory.getItems()) {
            if (item.getName().equals(player.getWeapon().getName())) {
                weaponId = item.getId();
            }
        }
        return weaponId;
    }

    public InventoryModel saveInventoryReturnInventoryId(Inventory inventory) {
        InventoryModel model = new InventoryModel(inventory);
        inventoryDao.add(model);
        setUpItemsInInventoryModel(inventory, model);
        saveAllItemsInDatabase(model, model.getId());
        return model;
    }

    private void saveAllItemsInDatabase(InventoryModel model, int inventoryId) {
        for (ItemModel item : model.getItems()) {
            itemDao.add(item, inventoryId);
        }
    }

    private void setUpItemsInInventoryModel(Inventory inventory, InventoryModel model) {
        List<ItemModel> items = new ArrayList<>();
        inventory.getItems().forEach(item -> items.add(new ItemModel(item)));
        model.setItems(items);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
