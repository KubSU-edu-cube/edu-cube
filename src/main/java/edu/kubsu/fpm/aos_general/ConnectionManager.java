package edu.kubsu.fpm.aos_general;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Admin
 */
public class ConnectionManager {

    private static ConnectionManager cm;
    private static Connection conn;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (cm == null) {
            cm = new ConnectionManager();
        }

        return cm;
    }

    public static Connection getConnection() {
        ConnectionManager manager = getInstance();
        try {
            if (ConnectionManager.conn == null || ConnectionManager.conn.isClosed()) {

                Class.forName("org.apache.derby.jdbc.ClientDriver");
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/FactsStore", "admin", "admin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    public static ResultSet executeQuery(String sql, Object... params) {
        Connection conn = ConnectionManager.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0 ; i < params.length; i++) {
                if (params[i] instanceof String) {
                    statement.setString(i+1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    statement.setInt(i+1, (Integer) params[i]);
                } else if (params[i] instanceof Date) {
                    statement.setDate(i+1, (Date) params[i]);
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    return null;
    }
}
