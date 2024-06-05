package com.example.timetablesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/timetablesystem";
    private static final String USER = "Omer Jawaid";
    private static final String PASSWORD = "Hina@1976";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

