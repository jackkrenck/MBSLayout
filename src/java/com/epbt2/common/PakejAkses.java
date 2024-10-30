/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Baizura
 */
public class PakejAkses {

    public void infoUser(Connection conn, String info) throws SQLException {
        String sql = "{ call sys.sp_sys.put_info('" + info + "') } ";
        System.out.println(sql);
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = conn.prepareCall(sql);

            result = stmt.executeQuery();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void aksesUser(Connection conn, String userid, String usernme, String usergrp, String macid, String pcnme, String jbkod, String jbnme, String sessId, String ipaddr) throws SQLException {

        String sql = "{ call sp_akses.login_info('" + userid + "', '" + usernme.replaceAll("'", "\''") + "', '" + usergrp + "', '" + macid + "', '" + pcnme + "', '" + jbkod + "','" + jbnme.replaceAll("'", "\''") + "', '"+ sessId +"', '"+ ipaddr +"') } ";

        System.out.println(sql);
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = conn.prepareCall(sql);

            result = stmt.executeQuery();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void logoutUser(Connection conn, String userid) throws SQLException {

        String sql = "{ call sp_akses.logout_info('" + userid + "') } ";

        System.out.println(sql);
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = conn.prepareCall(sql);

            result = stmt.executeQuery();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
