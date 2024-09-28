package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException {
        try {
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var username = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();


            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
