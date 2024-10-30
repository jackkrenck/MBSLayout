/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.spbt.ref.dao;

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
public class FImageDaoExt extends FImageDao {
    
  
  public int rekodWujud(Connection conn, String indP) throws SQLException {

        String sql = "SELECT count(*) FROM SPBT.FIMAGE WHERE (FIM_DFTAR = '" + indP + "' ) ";

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

    public synchronized void insertFoto(Connection conn, FImage valueObject, File img, String imgFileName) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO SPBT.FIMAGE ( FIM_DFTAR, FIM_KETER, FIM_LOGOM) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            FileInputStream fis = new FileInputStream(img);//InputStream

            stmt.setString(1, valueObject.getFimDftar());
            stmt.setString(2, valueObject.getFimKeter());
            stmt.setBinaryStream(3, fis, (int) img.length());
          
            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } catch (FileNotFoundException fe) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateFoto(Connection conn, FImage valueObject, File img, String imgFileName)
            throws NotFoundException, SQLException {

        String sql = "UPDATE SPBT.FIMAGE SET FIM_KETER = ? ,FIM_LOGOM = ? WHERE (FIM_DFTAR = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);

            FileInputStream fis = new FileInputStream(img);
            
            stmt.setString(1, valueObject.getFimKeter());
            stmt.setBinaryStream(2, fis, (int) img.length());
            
            stmt.setString(3, valueObject.getFimDftar());

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
    
    public int countFoto(Connection conn,String idno) throws SQLException {

          String sql = "SELECT count(*) FROM SPBT.FIMAGE WHERE FIM_DFTAR = '"+idno+"'  ";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
          return allRows;
    }
    
    public void convertFoto(Connection conn, FImage valueObject, File img)
            throws NotFoundException, SQLException {

        String sql = "UPDATE SPBT.FIMAGE SET FIM_LOGOM = ? WHERE (FIM_DFTAR = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);

            FileInputStream fis = new FileInputStream(img);
                        
            stmt.setBinaryStream(1, fis, (int) img.length());
            
            stmt.setString(2, valueObject.getFimDftar());

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
