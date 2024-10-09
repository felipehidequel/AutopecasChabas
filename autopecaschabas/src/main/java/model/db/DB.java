package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException {
        try {
            String jdbcUrl = DatabaseConfig.getDbUrl();
            String username = DatabaseConfig.getDbUsername();
            String password = DatabaseConfig.getDbPassword();


            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
