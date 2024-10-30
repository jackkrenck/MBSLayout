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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ghani
 */
public class StdMenuDaoExt extends StdMenuDao {

    public List loadGroupMenuLevel1(Connection conn, StdMenu valueObject, String userGroup) throws SQLException {

        /*String sql = "SELECT distinct MENU_MAIN_ID, MENU_MAIN_DESC, MENU_MAIN_SEQ, MENU_GROUP_ID FROM ( "
         + "SELECT c.MENU_MAIN_ID, c.MENU_MAIN_DESC, c.MENU_MAIN_SEQ, b.MENU_GROUP_ID "
         + "FROM SPBT.STD_MENU_CTRL a, SPBT.STD_MENU b, SPBT.STD_MAIN_WEB_GRP c "
         + "WHERE a.USER_GROUP_ID = '" + userGroup + "' AND a.USER_ACCESS = 'Y' "
         + "AND a.MENU_ID = b.MENU_ID  AND B.MENU_MAIN_GROUP IS NOT NULL "
         + "AND b.MENU_ICON_STS = 'Y' AND b.MENU_MAIN_GROUP = c.MENU_MAIN_ID) "
         + "ORDER BY MENU_MAIN_SEQ";*/
        String sql = "SELECT distinct c.MENU_MAIN_ID, c.MENU_MAIN_DESC, c.MENU_MAIN_SEQ, b.MENU_GROUP_ID, c.MENU_ICON "
                + "FROM SPBT.STD_MAIN_WEB_GRP c, SPBT.STD_MENU b, SPBT.STD_MENU_CTRL A "
                + "WHERE c.MENU_MAIN_ID = b.MENU_MAIN_GROUP AND b.MENU_ID = A.MENU_ID "
                + "AND B.MENU_MAIN_GROUP IS NOT NULL AND b.MENU_ICON_STS = 'Y' "
                + "AND A .USER_GROUP_ID = '" + userGroup + "' "
                + "AND A .USER_ACCESS = 'Y' AND b.WEB_CONTROL = 'Y' AND a.MENU_GROUP_ID = 'MENU0' "
                //+ "and menu_main_id = '1' "
                + "ORDER BY MENU_MAIN_SEQ ";
        System.out.println(sql);
        List searchResults = listMenuGroupQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    protected List listMenuGroupQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                StdMenu temp = createValueObject();

                temp.setMenuMainDesc(result.getString("MENU_MAIN_DESC"));
                temp.setMenuMainId(result.getString("MENU_MAIN_ID"));
                temp.setMenuGroupId(result.getString("MENU_GROUP_ID"));
                temp.setMenuIcon(result.getString("MENU_ICON"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List) searchResults;
    }

    public List loadGroupMenuLevel2(Connection conn, StdMenu valueObject, String userGroup) throws SQLException {

        String sql = "SELECT b.MENU_DESC, b.MENU_ICON, b.MENU_MAIN_GROUP, b.PROJECT_NAME, b.NAME_SPACE,  b.ACTION_LINK, b.WEB_MENU_CALL MENU_CALL, b.WEB_APPL_TYPE, (SELECT count(*) FROM SPBT.WVNOTFY WHERE NTF_MODUL = REPLACE(b.PROJECT_NAME, '/MBS_') AND NTF_STATS = 'A') notify, "
                + "CASE WHEN (b.WEB_APPL_TYPE = 'A') THEN b.WEB_MENU_CALL  ELSE b.MENU_GROUP_ID END MENU_GROUP_ID "
                + "FROM SPBT.STD_MENU_CTRL a, SPBT.STD_MENU b "
                + "WHERE a.USER_GROUP_ID = '" + userGroup + "' AND a.USER_ACCESS = 'Y' AND b.WEB_CONTROL = 'Y' "
                + "AND a.MENU_ID = b.MENU_ID  AND b.MENU_MAIN_GROUP IS NOT NULL "
                //+ "AND b.MENU_CALL != 'MENU4' "
                + "AND b.MENU_ICON_STS = 'Y' "
                + "AND WEB_APPL_TYPE != 'M' "
                + "ORDER BY b.MENU_MAIN_GROUP, b.WEB_SEQ";
        System.out.println(sql);
        List searchResults = listMenuQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    protected List listMenuQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                StdMenu temp = createValueObject();

                // temp.setMenuId(result.getString("MENU_ID"));
                // temp.setMenuGroupId(result.getString("MENU_GROUP_ID"));
                temp.setMenuDesc(result.getString("MENU_DESC"));
                temp.setMenuMainGroup(result.getString("MENU_MAIN_GROUP"));
                temp.setMenuIcon(result.getString("MENU_ICON"));
                temp.setMenuCall(result.getString("MENU_CALL"));
                temp.setWebApplType(result.getString("WEB_APPL_TYPE"));
                temp.setProjectName(result.getString("PROJECT_NAME"));
                temp.setActionLink(result.getString("ACTION_LINK"));
                temp.setBilNotify(result.getInt("notify"));
                /*temp.setMenuSeq(result.getString("MENU_SEQ"));
                 temp.setWebControl(result.getString("WEB_CONTROL"));*/
                 temp.setNameSpace(result.getString("NAME_SPACE"));
                temp.setMenuGroupId(result.getString("MENU_GROUP_ID"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List) searchResults;
    }

    public List loadIconAkses(Connection conn, String userGroup, String menuGroup) throws SQLException {

        String sql = "SELECT b.MENU_DESC, b.MENU_ICON, b.PROJECT_NAME, b.NAME_SPACE,  b.ACTION_LINK, "
                + "CASE WHEN (WEB_APPL_TYPE = 'G') THEN b.WEB_MENU_CALL "
                + "WHEN (WEB_APPL_TYPE = 'A') THEN b.WEB_MENU_CALL "
                + "WHEN (WEB_APPL_TYPE = 'S') THEN b.WEB_MENU_CALL "
                + "ELSE a.MENU_GROUP_ID END MENU_GROUP_ID, "
                + "(SELECT count(*) FROM SPBT.WVNOTFY WHERE NTF_MODUL = REPLACE(b.PROJECT_NAME, '/MBS_') AND NTF_STATS = 'A') notify "
                + "FROM SPBT.STD_MENU_CTRL a, SPBT.STD_MENU b "
                + "WHERE a.USER_GROUP_ID = '" + userGroup + "' AND a.USER_ACCESS = 'Y' "
                + "AND a.MENU_ID = b.MENU_ID AND a.MENU_GROUP_ID = '" + menuGroup + "' "
                + "AND b.MENU_ICON_STS = 'Y' "
                + "ORDER BY b.WEB_SEQ";
System.out.println(sql);
        List searchResults = listIconAksesQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    protected List listIconAksesQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                StdMenu temp = createValueObject();

                temp.setProjectName(result.getString("PROJECT_NAME"));
                temp.setMenuDesc(result.getString("MENU_DESC"));
                temp.setMenuIcon(result.getString("MENU_ICON"));
                temp.setNameSpace(result.getString("NAME_SPACE"));
                temp.setActionLink(result.getString("ACTION_LINK"));
                temp.setMenuGroupId(result.getString("MENU_GROUP_ID"));
                temp.setBilNotify(result.getInt("notify"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List) searchResults;
    }

    public List loadDashboard(Connection conn, StdMenu valueObject, String userGroup) throws SQLException {

        String sql = "SELECT b.MENU_DESC, b.MENU_ICON, b.MENU_MAIN_GROUP, b.PROJECT_NAME, b.NAME_SPACE,  b.ACTION_LINK, b.WEB_MENU_CALL MENU_CALL, b.WEB_APPL_TYPE, (SELECT count(*) FROM SPBT.WVNOTFY WHERE NTF_MODUL = REPLACE(b.PROJECT_NAME, '/MBS_') AND NTF_STATS = 'A') notify, "
                + "b.MENU_GROUP_ID "
                + "FROM SPBT.STD_MENU_CTRL a, SPBT.STD_MENU b "
                + "WHERE a.USER_GROUP_ID = '" + userGroup + "' AND a.USER_ACCESS = 'Y' AND b.WEB_CONTROL = 'Y' "
                + "AND a.MENU_ID = b.MENU_ID  AND b.MENU_MAIN_GROUP IS NOT NULL "
                //+ "AND b.MENU_CALL != 'MENU4' "
                + "AND b.MENU_ICON_STS = 'Y' "
                + "AND WEB_APPL_TYPE NOT IN ('G','M','S') "
                + "UNION "
                + "SELECT b.MENU_DESC, b.MENU_ICON, b.MENU_MAIN_GROUP, b.PROJECT_NAME, b.NAME_SPACE, b.ACTION_LINK, "
                + "b.WEB_MENU_CALL MENU_CALL, 'F', null, b.MENU_GROUP_ID "
                + "FROM SPBT.STD_MENU_CTRL A, SPBT.STD_MENU b "
                + "WHERE A .USER_GROUP_ID = '" + userGroup + "' "
                + "AND A .USER_ACCESS = 'Y' "
                + "AND b.WEB_CONTROL = 'Y' "
                + "AND A .MENU_ID = b.MENU_ID "
                + "AND b.MENU_MAIN_GROUP IS NOT NULL "
                + "AND b.MENU_ICON_STS = 'Y' "
                + "AND A .MENU_ID  = '5' "
                + "order by 3 ";
        System.out.println(sql);
        List searchResults = listMenuQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }
}
