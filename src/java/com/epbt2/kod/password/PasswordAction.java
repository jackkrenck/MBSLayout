/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.kod.password;

import com.epbt2.common.dao.StdUser;
import com.epbt2.common.dao.StdUserDao;
import com.epbt2.common.dao.StdUserDaoExt;
import com.epbt2.common.model.User;
import com.epbt2.interceptors.UserAware;
import com.epbt2.util.DbHelper;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Bai
 */
public class PasswordAction extends ActionSupport implements UserAware, ModelDriven<User> {

    private Log log = LogFactory.getLog(this.getClass());

    private Connection conn;
    private User userModel = new User();

    private StdUser pengguna = new StdUser();
    private StdUserDao penggunaDao = new StdUserDao();
    private StdUserDaoExt penggunaDaoExt = new StdUserDaoExt();

    public String execute() throws Exception {
        conn = DbHelper.getConnectionDS();
        try {
            pengguna = penggunaDaoExt.maklUser(conn, userModel.getUserId());
        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        log.debug("Inside save()");

        conn = DbHelper.getConnectionDS();
        try {
            // show msg
            String[] msgArgs = {pengguna.getUserId()};

            penggunaDao.save(conn, pengguna);

            pengguna.setUserPassword(null);
            pengguna.setChkPassword(null);
            
            
            addActionMessage(getText("message.pwd", msgArgs));
            
        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
            log.debug("REDIRECT:"+userModel.getUserId()+":"+userModel.getUserName()+":"+userModel.getUserGroupId());
        return SUCCESS;
    }

    public StdUser getPengguna() {
        return pengguna;
    }

    public void setPengguna(StdUser pengguna) {
        this.pengguna = pengguna;
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
