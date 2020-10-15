package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.logic.actors.components.Inventory;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private ItemDao itemDao;
    private InventoryDao inventoryDao;
    private GameStateDao gameStateDao;
    private GameStateModel gameState;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        itemDao = new ItemDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource, itemDao);
        playerDao = new PlayerDaoJdbc(dataSource, inventoryDao, itemDao);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
    }

    public void prepareLoadedGameState(GameMap loadedMap, String saveName) {
        Map currentMap = Arrays.stream(Map.values()).
                filter(mapEntry -> mapEntry.getId() == loadedMap.getLevelId()).
                findFirst().
                orElseThrow(() -> new RuntimeException("Error while retrieving map with id: " + loadedMap.getLevelId()));

        PlayerModel player = savePlayer(loadedMap.getPlayer());

        gameState = new GameStateModel(currentMap, actualDate(), player, saveName);
        List<GameStateModel> gameStates = gameStateDao.getAll();
        gameStates.stream()
                .filter(state -> state.getSaveName().equals(saveName))
                .findFirst()
                .ifPresent(gameStateModel -> gameState.setId(gameStateModel.getId()));
    }

    public void saveGameState() {
        gameStateDao.add(gameState);
    }

    public void updateGameState() {
        gameStateDao.update(gameState);
    }

    public List<String> getAllSaveNames() {
        return gameStateDao.getAll().stream()
                .map(GameStateModel::getSaveName)
                .collect(Collectors.toList());
    }

    public List<GameStateModel> getAllGameStates() {
        return gameStateDao.getAll();
    }

    public PlayerModel savePlayer(Player player) {
        InventoryModel inventory = saveInventoryReturnInventoryId(player.getInventory());
        PlayerModel model = new PlayerModel(player);
        model.setInventoryId(inventory.getId());
        model.setWeaponId(getWeaponInUseId(player, inventory));
        playerDao.add(model);
        return model;
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

    public boolean checkValidSaveName(String saveName) {
        List<GameStateModel> gameStates = gameStateDao.getAll();
        return gameStates.stream()
                .filter(gameState -> gameState.getSaveName().equals(saveName))
                .findFirst()
                .isEmpty();
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String host = System.getenv("DB_HOST");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(host);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    private Date actualDate() {
        long millis=System.currentTimeMillis();
        return new java.sql.Date(millis);
    }
}
