/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.dao.StdMenu;
import com.epbt2.common.dao.StdMenuDaoExt;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.epbt2.sspg.ref.dao.PnfotoDaoExt;
import com.epbt2.util.DbHelper;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author ghani
 */
public class InfoAction extends ActionSupport implements UserAware, ModelDriven<User>, ServletRequestAware {

    private final Log log = LogFactory.getLog(this.getClass());

    private Connection conn;

    private User userModel = new User();

    public HttpServletRequest request;

    /*private WvnotfyDaoExt notifyDaoExt = new WvnotfyDaoExt();
     private WvnotfyDao notifyDao = new WvnotfyDao();
     private Wvnotfy notify = new Wvnotfy();
    
     private List<Wvnotfy> listNotify;*/
    private int bilNotify;

    private StdMenuDaoExt stdMenuDaoExt = new StdMenuDaoExt();
    private StdMenu stdMenu = new StdMenu();
    private List<StdMenu> listSystem;

    private String systemName;
    private String moduleName;

    private PnfotoDaoExt fotoDaoExt = new PnfotoDaoExt();

    private int rekodWujud;

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

    public String execute() {
        try {
            conn = DbHelper.getConnectionDS();

            String contextPath = request.getContextPath();
            contextPath = contextPath.replace("/MBS_", "");

            rekodWujud = fotoDaoExt.rekodWujud(conn, userModel.getUserId());

            /*notify.setNtfModul(contextPath);
             notify.setNtfStats("A");
             listNotify = notifyDao.searchMatching(conn, notify);
            
             bilNotify = notifyDaoExt.rekodWujud(conn, contextPath);
            
             listSystem = stdMenuDaoExt.loadOtherSystem(conn, userModel.getUserGroupId(), userModel.getMenuGroup(), contextPath);
            
             systemName = stdMenuDaoExt.systemName(conn, userModel.getUserGroupId(), userModel.getMenuGroup());
             moduleName = stdMenuDaoExt.moduleName(conn, userModel.getUserGroupId(), userModel.getMenuGroup(), contextPath);
            
             System.out.println("INFO:"+userModel.getUserId()+":"+userModel.getUserName()+":"+userModel.getMenuGroup()+":"+userModel.getUserMacAddress()+":"+userModel.getUserPcName()+":"+moduleName);*/
        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    /*public List<Wvnotfy> getListNotify() {
     return listNotify;
     }

     public void setListNotify(List<Wvnotfy> listNotify) {
     this.listNotify = listNotify;
     }*/
    public int getBilNotify() {
        return bilNotify;
    }

    public void setBilNotify(int bilNotify) {
        this.bilNotify = bilNotify;
    }

    public List<StdMenu> getListSystem() {
        return listSystem;
    }

    public void setListSystem(List<StdMenu> listSystem) {
        this.listSystem = listSystem;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getRekodWujud() {
        return rekodWujud;
    }

    public void setRekodWujud(int rekodWujud) {
        this.rekodWujud = rekodWujud;
    }

}
