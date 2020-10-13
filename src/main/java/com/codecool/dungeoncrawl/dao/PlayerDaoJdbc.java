package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.items.Weapon;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (health, current_health, strength, name, posX, posY, " +
                    "inventory_id, weapon_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, player.getHp());
            statement.setInt(2, player.getCurrentHp());
            statement.setInt(3, player.getStrength());
            statement.setString(4, player.getPlayerName());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setInt(7, player.getInventoryId());
            statement.setInt(8, player.getWeaponId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

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
            int health = resultSet.getInt(1);
            int currentHealth = resultSet.getInt(2);
            int strength = resultSet.getInt(3);
            String name = resultSet.getString(4);
            int posX = resultSet.getInt(5);
            int posY = resultSet.getInt(6);
            int inventoryId = resultSet.getInt(7);
            int weaponId = resultSet.getInt(8);

            PlayerModel playerModel = new PlayerModel(health, currentHealth, strength, name, posX, posY, inventoryId, weaponId);
            playerModel.setId(id);
            return playerModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Weapon getWeapon(int weaponId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name FROM item WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, weaponId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String weaponName = resultSet.getString(1);
            Weapon weapon = null;
            return weapon;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
