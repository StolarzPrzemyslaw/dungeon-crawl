package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {

    private final DataSource dataSource;
    private final ItemDao itemDao;

    public InventoryDaoJdbc(DataSource dataSource, ItemDao itemDao) {
        this.dataSource = dataSource;
        this.itemDao = itemDao;
    }

    @Override
    public void add(InventoryModel inventory) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlQuery = "INSERT INTO inventory (player_inventory_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, getLastIndexInInventory() + 1);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            inventory.setId(resultSet.getInt("player_inventory_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryModel inventory) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "DELETE FROM item WHERE player_inventory_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, inventory.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InventoryModel get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT player_inventory_id FROM inventory WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return !result.next() ? null : createInventoryModelBasedOnData(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InventoryModel> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            List<InventoryModel> inventories = new ArrayList<>();
            String sqlQuery = "SELECT player_inventory_id FROM inventory WHERE id = ?";
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);
            while (result.next()) {
                InventoryModel inventory = createInventoryModelBasedOnData(result);
                inventories.add(inventory);
            }
            return inventories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private InventoryModel createInventoryModelBasedOnData(ResultSet result) throws SQLException {
        int inventoryId = result.getInt("player_inventory_id");
        List<ItemModel> items = itemDao.getAll(inventoryId);
        InventoryModel inventory = new InventoryModel();
        inventory.setItems(items);
        inventory.setId(inventoryId);
        return inventory;
    }

    private int getLastIndexInInventory() {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT COUNT(id) as total FROM inventory";
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);
            if (result.next()) {
                return result.getInt("total");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
