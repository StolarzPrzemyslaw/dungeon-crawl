package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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

            insertPlayerDetails(player, statement);

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertPlayerDetails(PlayerModel player, PreparedStatement statement) throws SQLException {
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

        return new PlayerModel(health, currentHealth, strength, name, posX, posY, inventoryId, weaponId);
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
