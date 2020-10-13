package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {

    private final DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
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
            inventory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(InventoryModel inventory) {
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
            return createInventoryModelBasedOnData(result);
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
        InventoryModel inventory = new InventoryModel();
        inventory.setId(inventoryId);
        return inventory;
    }

    private int getLastIndexInInventory() {
        try (Connection conn = dataSource.getConnection()) {
            String sqlQuery = "SELECT id FROM inventory WHERE id = (SELECT MAX(id) FROM inventory)";
            PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
