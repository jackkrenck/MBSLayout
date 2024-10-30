/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author otasoft01
 */
public class RedirectSystemAction extends ActionSupport implements UserAware, ModelDriven<User> {

    private Log log = LogFactory.getLog(this.getClass());
    private Connection conn;

    private User userModel = new User();

    private String url;
    private String link;

    public String execute() {
        url = link + "?user.userId="+userModel.getUserId();
        log.debug("REDIRECT:"+url);
        return "redirect";
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
   
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
