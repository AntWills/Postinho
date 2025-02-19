package com.project.dao;

import java.io.File;
import java.sql.*;

public class DbConnect {
    private static Connection connection;

    public static void openBank() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir(); // Cria o diretório se ele não existir

        }
        try {
            // Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data/PostinhoDB.sqlite");
        } catch (Exception e) {
            System.err.println("Error when opening the bank: " + e.getMessage());
        }
    }

    public static void closeBank() {
        if (connection == null)
            return;
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (Exception e) {
            System.err.println("Error when closing the bank: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null)
                openBank();
            else if (connection.isClosed())
                openBank();
        } catch (Exception e) {
            System.err.println("Error when connecting to the bank: " + e.getMessage());
        }
        return connection;
    }

    public static void execQuery(String query) throws SQLException {
        Connection dbConnection = getConnection();
        Statement stm = dbConnection.createStatement();
        stm.executeUpdate(query);
        stm.close();
    }
}
