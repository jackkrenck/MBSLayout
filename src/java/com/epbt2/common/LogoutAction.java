/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.epbt2.util.DbHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author ghani
 */
public class LogoutAction extends ActionSupport implements UserAware,  ModelDriven<User>,ServletRequestAware, ServletResponseAware {// implements SessionAware {//implements ServletResponseAware {

    private Log log = LogFactory.getLog(this.getClass());

    /* BYE 14/02/2017 */
    private Connection conn;

    private User userModel = new User();
    
    private PakejAkses aksesPakej = new PakejAkses();

  
    public String execute() throws Exception {
        log.debug("userModel:"+userModel.toString());
        log.debug("User " + userModel.getUserId() + " will Logout from MBS Web!");

         HttpSession session = request.getSession(false);
        if (session != null) {
          
        //if (session.containsKey("USER")) {
            //   session.clear();

            Cookie user = new Cookie("USER",  userModel.getUserId());
            user.setPath("/");
            user.setMaxAge(0);
            response.addCookie(user);
            /* BYE 14/02/2017 */

            conn = DbHelper.getConnectionDS();
            try {
                User userS = (User) session.getAttribute("USER");
                aksesPakej.logoutUser(conn, userS.getUserId());
            } catch (Exception e) {
                log.error(e);
                //addActionError(getText("error.login"));
            } finally {
                DbHelper.closeConnection(conn);
            }
            /* BYE 14/02/2017 */
              session.invalidate();
            addActionMessage(getText("message.logout"));

            return SUCCESS;
        } else {

            addActionMessage(getText("message.sessionExpired"));
            return LOGIN;
        }

    }

    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
    private HttpServletResponse response;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

/* USER */

    public User getUserModel() {
        return userModel;
    }

 
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
