/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.sspg.ref.dao;

import com.epbt2.sspg.ref.dao.Pnfoto;
import com.epbt2.sspg.ref.dao.PnfotoDao;
import com.epbt2.util.NotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class PnfotoDaoExt extends PnfotoDao{
    
    
  public int rekodWujud(Connection conn, String indPnelia) throws SQLException {

        String sql = "SELECT count(*) FROM SSPG.PNFOTO WHERE (PNF_NOPEK = '" + indPnelia + "' ) ";
System.out.println(sql);
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRows;
    }


    public void updateFoto(Connection conn, Pnfoto valueObject, File img)
            throws NotFoundException, SQLException {

        String sql = "UPDATE SSPG.PNFOTO SET PNF_GFOTO = ? WHERE (PNF_NOPEK = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);

            FileInputStream fis = new FileInputStream(img);
            stmt.setBinaryStream(1, fis, (int) img.length());
            stmt.setString(2, valueObject.getPnfNopek());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }

        } catch (FileNotFoundException fe) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
