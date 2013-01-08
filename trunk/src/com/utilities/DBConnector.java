package com.utilities;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * External database credentials and connection methods
 * @author Zbyszko
 */
public class DBConnector {

    //DO NOT CREATE SETTERS AND/OR GETTERS FOR THESE FIELDS!
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/ws";
    private String uid = "root";
    private String pwd = "lamora";
    //---------------------------------------------------------
    private Connection connection;

    /**
     * Checks if database connection is possible
     * @return 
     */
    public boolean isConnection() {
        
        try {
            Class.forName(driverName);
            connection = (Connection) DriverManager.getConnection(url, uid, pwd);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(name, password, availableStorageSpace) VALUES(?,?,?)");
            ps.setString(1, "mea");
            ps.setString(2, "mea");
            ps.setLong(3, 33333);
            int statement = ps.executeUpdate();  
           ps.close(); 
           connection.close();        
        } catch (ClassNotFoundException exc) {  // brak klasy sterownika
            System.out.println("No class driver");
            System.out.println(exc);
            return false;
        } catch (SQLException exc) {  // nieudane po³¹czenie
            System.out.println("Connection failed, url: " + url);
            System.out.println(exc);
            return false;
        }

        return true;
    }

    /**
     * Connects to database.
     * @return Connection Object.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        connection = (Connection) DriverManager.getConnection(url, uid, pwd);
        return connection;
    }

    /**
     * Closes the database connection.
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
