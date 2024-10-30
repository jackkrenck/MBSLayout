/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epbt2.common.model;

/**
 *
 * @author ghani
 */
public class User {
    private String userId;
    private String userPassword;
    private String userGroupId;
    private String userName;
    private String userCreated;
    private String userStatus;
    
    private String userMacAddress;
    private String userPcName;
    
    /* BYE 30/10/2015 */
    private String userJbKod;
    private String userJbNama;
    /* BYE 30/10/2015 */
    
    private String userIpAddr;
        
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    
    public String getUserMacAddress() {
        return userMacAddress;
    }

    public void setUserMacAddress(String userMacAddress) {
        this.userMacAddress = userMacAddress;
    }

    public String getUserPcName() {
        return userPcName;
    }

    public void setUserPcName(String userPcName) {
        this.userPcName = userPcName;
    }    

    /* BYE 30/10/2015 */
    public String getUserJbKod() {
        return userJbKod;
    }

    public void setUserJbKod(String userJbKod) {
        this.userJbKod = userJbKod;
    }

    public String getUserJbNama() {
        return userJbNama;
    }

    public void setUserJbNama(String userJbNama) {
        this.userJbNama = userJbNama;
    }
    /* BYE 30/10/2015 */

    public String getUserIpAddr() {
        return userIpAddr;
    }

    public void setUserIpAddr(String userIpAddr) {
        this.userIpAddr = userIpAddr;
    }
}
