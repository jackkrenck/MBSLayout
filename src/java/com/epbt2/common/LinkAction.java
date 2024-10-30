/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ghani
 */
public class LinkAction extends ActionSupport {

    private String directory;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String execute() {
        setDirectory(ServletActionContext.getActionMapping().getNamespace());

        return SUCCESS;
    }
}
