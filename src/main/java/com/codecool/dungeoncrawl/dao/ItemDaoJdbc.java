package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoJdbc implements ItemDao {

    private final DataSource dataSource;

    public ItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ItemModel item, int inventory_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sqlQuery = "INSERT INTO item (player_inventory_id, name, statistic, type) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventory_id);
            statement.setString(2, item.getName());
            statement.setInt(3, item.getStatistic());
            statement.setString(4, getType(item.getItemType()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            item.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getType(ItemModel.Type itemType) {
        if (itemType == ItemModel.Type.USABLE) {
            return "usable";
        } else if (itemType == ItemModel.Type.CONSUMABLE) {
            return "consumable";
        } else {
            return "none";
        }
    }

    @Override
    public void update(ItemModel item) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "UPDATE item SET name = ?, statistic = ?, type = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getStatistic());
            statement.setString(3, getType(item.getItemType()));
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemModel get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT player_inventory_id, name, statistic, type FROM item WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return !result.next() ? null : createItemModelBasedOnData(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemModel> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            List<ItemModel> items = new ArrayList<>();
            String sqlQuery = "SELECT id, name, statistic, type FROM item";
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);
            while (result.next()) {
                ItemModel item = createItemModelBasedOnData(result);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemModel> getAll(int inventory_id) {
        try (Connection connection = dataSource.getConnection()) {
            List<ItemModel> items = new ArrayList<>();
            String sqlQuery = "SELECT id, name, statistic, type FROM item WHERE player_inventory_id = ?";
            ResultSet result = connection.createStatement().executeQuery(sqlQuery);
            while (result.next()) {
                ItemModel item = createItemModelBasedOnData(result);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ItemModel createItemModelBasedOnData(ResultSet result) throws SQLException {
        int itemId = result.getInt("id");
        String name = result.getString("name");
        int statistic = result.getInt("statistic");
        String type = result.getString("type");
        return new ItemModel(itemId, name, statistic, type);
    }
}