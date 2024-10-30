/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.image;

/**
 *
 * @author otasoft01
 */
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class StafImageBytesResult implements Result {

    public void execute(ActionInvocation invocation) throws Exception {

        ImgPnfotoAction action = (ImgPnfotoAction) invocation.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType(action.getCustomContentType());
        
        
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(action.imgFromDbase()));
        
        if (img == null) {
            
            action.imgFromDbaseIS();

            img = ImageIO.read(new ByteArrayInputStream(action.imgFromDbase()));
            
        }

        ImageIO.write(img, "png", response.getOutputStream());
        img.flush();
        
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }

}
