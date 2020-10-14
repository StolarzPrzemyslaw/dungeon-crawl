package com.codecool.dungeoncrawl.dao;

import javax.sql.DataSource;
import java.sql.*;

public class MapDaoJdbc implements MapDao {
    private final DataSource dataSource;

    public MapDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlQuery = "SELECT file_name FROM map WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.getString("file_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
