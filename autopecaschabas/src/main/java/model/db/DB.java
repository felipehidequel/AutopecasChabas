package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/autopecas", "postgres", "felipe421");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
