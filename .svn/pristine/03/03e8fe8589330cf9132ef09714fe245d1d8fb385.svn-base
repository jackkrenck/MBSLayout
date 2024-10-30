/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.dao.StdMenu;
import com.epbt2.common.dao.StdMenuDao;
import com.epbt2.common.dao.StdMenuDaoExt;
import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.epbt2.util.DbHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author otasoft01
 */
public class SystemAccessAction extends ActionSupport implements UserAware, ModelDriven<User> {

    private Log log = LogFactory.getLog(this.getClass());
    private Connection conn;

    private User userModel = new User();

    private StdMenuDao stdMenuDao = new StdMenuDao();
    private StdMenuDaoExt stdMenuDaoExt = new StdMenuDaoExt();
    private StdMenu stdMenu = new StdMenu();

    private List<StdMenu> iconList;
    private String menuGroup;
    
    public String execute() {
        try {
            conn = DbHelper.getConnectionDS();
            if (menuGroup == null) {
                menuGroup = "MENU2";
            }
          
            iconList = stdMenuDaoExt.loadIconAkses(conn, userModel.getUserGroupId(), menuGroup);

        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
        return SUCCESS;
    }

    public List<StdMenu> getIconList() {
        return iconList;
    }

    public void setIconList(List<StdMenu> iconList) {
        this.iconList = iconList;
    }

    public StdMenu getStdMenu() {
        return stdMenu;
    }

    public void setStdMenu(StdMenu stdMenu) {
        this.stdMenu = stdMenu;
    }

    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    /* USER */
    public User getUserModel() {
        return userModel;
    }

    @Override
    public void setUser(User userModel) {
        this.userModel = userModel;
    }

    public User getUser(User userModel) {
        return this.userModel;
    }

    @Override
    public User getModel() {
        return this.userModel;
    }
    /* USER */
}
