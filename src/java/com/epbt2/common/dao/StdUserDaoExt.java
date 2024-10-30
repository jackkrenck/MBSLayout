/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common.dao;

import com.epbt2.util.NotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ghani
 */
public class StdUserDaoExt extends StdUserDao {

    public int existRecord(Connection conn, StdUser valueObject) throws SQLException {
        /*
         String sql = "SELECT * FROM STD_USER WHERE (USER_ID = ? AND spbt.epbt.retr_password(USER_PASSWORD) = ? ) ";
         PreparedStatement stmt = null;

         try {
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, valueObject.getUserId());
         stmt.setString(2, valueObject.getUserPassword());

         singleQuery(conn, stmt, valueObject);

         } finally {
         if (stmt != null) {
         stmt.close();
         }
         }*/
        String sql = "SELECT count(*) FROM STD_USER "
                + " WHERE (USER_ID = ? AND spbt.epbt.retr_password(USER_PASSWORD) = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int oneRows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getUserId());
            stmt.setString(2, valueObject.getUserPassword());
            result = stmt.executeQuery();

            if (result.next()) {
                oneRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return oneRows;
    }

    public StdUser maklJabatan(Connection conn, String noPek) throws NotFoundException, SQLException {

        StdUser valueObject = createValueObject();

        String sql = "SELECT PNE_KDPTJ||PNE_KDPK PNE_JBKOD, PTJ_PNAMA  "
                + "from sspg.PVNDUK "
                + "where pne_nopek = '" + noPek + "' ";
        System.out.println(sql);
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);

            maklJabatanQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return valueObject;
    }
    
    protected void maklJabatanQuery(Connection conn, PreparedStatement stmt, StdUser valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setPneJbkod(result.getString("PNE_JBKOD"));
                valueObject.setPtjPnama(result.getString("PTJ_PNAMA"));

            } else {
                //System.out.println("StdUser Object Not Found!");
                throw new NotFoundException("StdUser Object Not Found!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    
    public StdUser maklUser(Connection conn, String userId) throws NotFoundException, SQLException {

        StdUser valueObject = createValueObject();
        valueObject.setUserId(userId);

        if (valueObject.getUserId() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }


        String sql = "SELECT USER_ID, USER_NAME, '' USER_PASSWORD FROM STD_USER WHERE (USER_ID = ?) ";
        
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getUserId());

            maklUserQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return valueObject;
    }
   
    
    protected void maklUserQuery(Connection conn, PreparedStatement stmt, StdUser valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setUserId(result.getString("USER_ID"));
                valueObject.setUserName(result.getString("USER_NAME"));
                valueObject.setUserPassword(result.getString("USER_PASSWORD"));

            } else {
                //System.out.println("StdUser Object Not Found!");
                throw new NotFoundException("StdUser Object Not Found!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
