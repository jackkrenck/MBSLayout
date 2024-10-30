/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.epbt2.common.dao.StdMenu;
import com.epbt2.common.dao.StdMenuDao;
import com.epbt2.common.dao.StdMenuDaoExt;
import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.epbt2.util.DbHelper;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ghani
 */
public class MenuAccordionPreparer extends ActionSupport implements UserAware, ModelDriven<User> {
//public class MenuAccordionPreparer extends ActionSupport  implements UserAware, ModelDriven<User>{//implements ViewPreparer {

    private Log log = LogFactory.getLog(this.getClass());
    private Connection conn;

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private StdMenuDao stdMenuDao = new StdMenuDao();
    private StdMenuDaoExt stdMenuDaoExt = new StdMenuDaoExt();
    private StdMenu stdMenu = new StdMenu();
    private List<StdMenu> menuLevel1List;
    private List<StdMenu> menuLevel2List;
    private List<StdMenu> menuLevel3List;

    private User userModel = new User();

    private String menuGroupLevel2;
    private String menuGroupLevel3 = "";

    public String execute() {
        try {
            conn = DbHelper.getConnectionDS();

            menuLevel1List = stdMenuDaoExt.loadGroupMenuLevel1(conn, stdMenu, userModel.getUserGroupId());
            menuLevel2List = stdMenuDaoExt.loadGroupMenuLevel2(conn, stdMenu, userModel.getUserGroupId());

        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
        return SUCCESS;
    }

    public List<StdMenu> getMenuLevel1List() {
        return menuLevel1List;
    }

    public void setMenuLevel1List(List<StdMenu> menuLevel1List) {
        this.menuLevel1List = menuLevel1List;
    }

    public List<StdMenu> getMenuLevel2List() {
        return menuLevel2List;
    }

    public void setMenuLevel2List(List<StdMenu> menuLevel2List) {
        this.menuLevel2List = menuLevel2List;
    }

    public StdMenu getStdMenu() {
        return stdMenu;
    }

    public void setStdMenu(StdMenu stdMenu) {
        this.stdMenu = stdMenu;
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
}
