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
            statement.setInt(7, getInventoryId());
            statement.setInt(8, getWeaponId(player.getWeapon()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getInventoryId() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_inventory_id FROM Inventory";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private int getWeaponId(Weapon weapon) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM item WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(weapon));
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(int id) {
        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
