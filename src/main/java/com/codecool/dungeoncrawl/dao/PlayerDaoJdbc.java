package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.characters.Player;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final DataSource dataSource;
    private final InventoryDao inventoryDao;
    private final ItemDao itemDao;

    public PlayerDaoJdbc(DataSource dataSource, InventoryDao inventoryDao, ItemDao itemDao) {
        this.dataSource = dataSource;
        this.inventoryDao = inventoryDao;
        this.itemDao = itemDao;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (health, current_health, strength, name, pos_x, pos_y, " +
                    "inventory_id, weapon_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            insertPlayerDetailsIntoModel(player, statement);

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertPlayerDetailsIntoModel(PlayerModel player, PreparedStatement statement) throws SQLException {
        statement.setInt(1, player.getHp());
        statement.setInt(2, player.getCurrentHp());
        statement.setInt(3, player.getStrength());
        statement.setString(4, player.getPlayerName());
        statement.setInt(5, player.getX());
        statement.setInt(6, player.getY());
        statement.setInt(7, player.getInventoryId());
        statement.setInt(8, player.getWeaponId());
        statement.executeUpdate();
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET health = ?, current_health = ?, strength = ?, name = ?, pos_x = ?," +
                    " pos_y = ?, inventory_id = ?, weapon_id = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            insertPlayerDetailsIntoModel(player, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            PlayerModel playerModel = createPlayerModel(resultSet);
            playerModel.setId(id);
            return playerModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PlayerModel createPlayerModel(ResultSet resultSet) throws SQLException {
        int health = resultSet.getInt(2);
        int currentHealth = resultSet.getInt(3);
        int strength = resultSet.getInt(4);
        String name = resultSet.getString(5);
        int posX = resultSet.getInt(6);
        int posY = resultSet.getInt(7);
        int inventoryId = resultSet.getInt(8);
        int weaponId = resultSet.getInt(9);
        InventoryModel inventory = inventoryDao.get(inventoryId);
        ItemModel weapon = itemDao.get(weaponId);
        PlayerModel player = new PlayerModel(health, currentHealth, strength, name, posX, posY, inventory, weapon);
        player.setInventoryId(inventoryId);
        player.setWeaponId(weaponId);
        return player;
    }

    @Override
    public List<PlayerModel> getAll() {
        List<PlayerModel> players = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                PlayerModel playerModel = createPlayerModel(resultSet);
                players.add(playerModel);
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
