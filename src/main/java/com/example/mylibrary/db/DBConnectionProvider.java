package com.example.mylibrary.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final DBConnectionProvider INSTANCE = new DBConnectionProvider();
    private Connection connection;
    private static final String DB_URl = "jdbc:mysql://localhost:3306/library?useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionProvider getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URl, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }
}
