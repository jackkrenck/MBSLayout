/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.dao.StdMenu;
import com.epbt2.common.dao.StdMenuDaoExt;
import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.epbt2.util.DbHelper;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Baizura
 */
public class DashboardAction extends ActionSupport implements UserAware, ModelDriven<User> {

    private Log log = LogFactory.getLog(this.getClass());
    private Connection conn;

    private User userModel = new User();

    private List<StdMenu> menuLevel2List;
    private StdMenu stdMenu = new StdMenu();
    private StdMenuDaoExt stdMenuDaoExt = new StdMenuDaoExt();

    public String execute() {
        try {
            conn = DbHelper.getConnectionDS();
            
            menuLevel2List = stdMenuDaoExt.loadDashboard(conn, stdMenu, userModel.getUserGroupId());
            
        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
        return SUCCESS;
    }

    public List<StdMenu> getMenuLevel2List() {
        return menuLevel2List;
    }

    public void setMenuLevel2List(List<StdMenu> menuLevel2List) {
        this.menuLevel2List = menuLevel2List;
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
