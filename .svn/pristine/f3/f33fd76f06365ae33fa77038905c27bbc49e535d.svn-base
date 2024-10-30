/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author otasoft01
 */
public class StdSysParamDaoExt {

    public String sysParamValue(Connection conn, String para_id) throws SQLException {

        String sql = "SELECT PARA_VALUE FROM SPBT.STD_SYS_PARAM WHERE (PARA_ID = '"+para_id+"') ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        String data = "";

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                data = result.getString(1);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return data;
    }

}
