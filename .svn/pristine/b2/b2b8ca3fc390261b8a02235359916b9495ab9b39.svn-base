/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.util;

import com.epbt2.common.dao.StdSysParam;
import com.epbt2.common.dao.StdSysParamDaoExt;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author BYE
 */
public class CreateDirectory {

    private StdSysParamDaoExt paramDaoExt = new StdSysParamDaoExt();
    private StdSysParam param = new StdSysParam();


    public String createDirectoryImg(Connection conn) throws SQLException {
        /*param.setParaId("7");
        folderKutipan = paramDaoExt.sysParamValue(conn, param, "mktn");

        File dirmktn = new File(folderKutipan);

        if (!dirmktn.exists()) {
            dirmktn.mkdir();
        }*/
        
                String mktnDir = paramDaoExt.sysParamValue(conn, "96");

                File fileDir = new File(mktnDir);
                if (!fileDir.exists()) {
                        fileDir.mkdirs();
                        System.out.println("Directory is created!");
                }
                return mktnDir;
    }   
                
}
