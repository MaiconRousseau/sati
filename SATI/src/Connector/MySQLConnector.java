/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CollaborativeClouds Software Solutions
 */
public class MySQLConnector {
    
private static Connection connection;

    public MySQLConnector(){

        MySQLConnector.connection = null;
    }

    public static Connection connect() throws Exception {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.1.118:3306/sati","swing","Test4");
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error finding the driver!\n(" + ex.getMessage() + ")");
        } catch (SQLException e) {
            throw new Exception("Error in the connection!\n(" + e.getMessage() + ")");
        }
    }

    public static void disconnect() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Error disconnecting!\n(" + ex.getMessage() + ")");
        }
    }
}
