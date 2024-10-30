/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.dao.StdSysParamDaoExt;
import com.epbt2.util.DbHelper;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Baizura
 */
public class TimeoutJsonAction extends ActionSupport {

    private Log log = LogFactory.getLog(this.getClass());

    private Connection conn;
    
    private StdSysParamDaoExt paramDaoExt = new StdSysParamDaoExt();
    
    private String paramValue;

    public String data() {

        try {
            conn = DbHelper.getConnectionDS();
            
            paramValue = paramDaoExt.sysParamValue(conn, "97");

        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());

        } finally {
            DbHelper.closeConnection(conn);
        }

        return SUCCESS;
    }

    public String getJSON() throws Exception {
        return data();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    
}
