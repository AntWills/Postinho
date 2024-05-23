package dbEntities;

import java.sql.*;

public class UtilDB {
    private Connection connection;
    private String nameDB;

    public UtilDB(String name) {
        this.nameDB = name;
    }

    public void openBank() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data/ " + nameDB + ".sqlite");
        } catch (Exception e) {
            System.err.println("Error when opening the bank: " + e.getMessage());
        }
    }

    public void closeBank() {
        if (connection == null)
            return;
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (Exception e) {
            System.err.println("Error when closing the bank: " + e.getMessage());
        }
    }

    public Connection getConnection() {
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

}
