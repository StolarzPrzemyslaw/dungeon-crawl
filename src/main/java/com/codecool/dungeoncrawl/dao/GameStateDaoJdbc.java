package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Map;
import com.codecool.dungeoncrawl.model.GameStateModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    DataSource dataSource;
    PlayerDao playerDao;

    public GameStateDaoJdbc(DataSource dataSource, PlayerDao playerDao) {
        this.dataSource = dataSource;
        this.playerDao = playerDao;
    }

    @Override
    public void add(GameStateModel state) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (saved_at, player_id, current_map, save_name) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, state.getSavedAt());
            statement.setInt(2, state.getPlayer().getId());
            statement.setInt(3, state.getCurrentMap().getId());
            statement.setString(4, state.getSaveName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException exception) {
            throw new RuntimeException("Error while adding Game State", exception);
        }
    }

    @Override
    public void update(GameStateModel state) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET saved_at = ?, player_id = ?, current_map = ?, save_name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, state.getSavedAt());
            statement.setInt(2, state.getPlayer().getId());
            statement.setInt(3, state.getCurrentMap().getId());
            statement.setString(4, state.getSaveName());
            statement.setInt(5, state.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error while updating Game State with id: " + state.getId(), exception);
        }
    }

    @Override
    public GameStateModel get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT saved_at, player_id, current_map, save_name FROM game_state WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            GameStateModel gameState = getGameStateModel(resultSet);
            gameState.setId(id);
            return gameState;
        } catch (SQLException exception) {
            throw new RuntimeException("Error while retrieving Game State with id: " + id);
        }
    }

    @Override
    public List<GameStateModel> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT saved_at, player_id, current_map, save_name, id FROM game_state";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<GameStateModel> result = new ArrayList<>();
            while (resultSet.next()) {
                GameStateModel gameState = getGameStateModel(resultSet);
                gameState.setId(resultSet.getInt(5));
                result.add(gameState);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error while retrieving all Game States");
        }
    }

    private GameStateModel getGameStateModel(ResultSet resultSet) throws SQLException {
        Date savedAt = resultSet.getDate(1);
        int playerId = resultSet.getInt(2);
        int currentMapId = resultSet.getInt(3);
        String saveName = resultSet.getString(4);
        Map currentMap = Arrays.stream(Map.values()).
                            filter(mapEntry -> mapEntry.getId() == currentMapId).
                            findFirst().
                            orElseThrow(() -> new RuntimeException("Error while retrieving map with id: " + currentMapId));
        PlayerModel playerModel = playerDao.get(playerId);
        return new GameStateModel(currentMap, savedAt, playerModel, saveName);
    }
}
