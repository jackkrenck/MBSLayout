/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epbt2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ghani
 */
public class DbHelper {
    
    //conctructor
    public DbHelper() {
        
    }
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "spbt", "spbt");
        if (conn == null) {
            throw new Exception("Could not connect to DB!");
        }
        return conn;
    }

    public static Connection getConnectionDS() throws Exception {
        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            throw new Exception("Uh oh -- no context!");
        }
        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mbsDB");
        if (ds == null) {
            throw new Exception("Data source not found!");
        }
        return ds.getConnection();
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
