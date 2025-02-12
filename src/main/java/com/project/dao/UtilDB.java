package com.project.dao;

import java.sql.*;

public class UtilDB {
    private static Connection connection;

    public static void openBank() {
        try {
            Class.forName("org.sqlite.JDBC");
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

    public static void execQuery(String query) throws Exception {
        Connection dbConnection = getConnection();
        Statement stm = dbConnection.createStatement();
        stm.executeUpdate(query);
        stm.close();
    }
}
