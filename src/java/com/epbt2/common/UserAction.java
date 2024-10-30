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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ghani
 */
public class UserAction extends ActionSupport implements UserAware, ModelDriven<User> {

    private Log log = LogFactory.getLog(this.getClass());
    private User userModel = new User();

    public User getUserModel() {
        return userModel;
    }

    @Override
    public String execute() {
        return SUCCESS;
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
