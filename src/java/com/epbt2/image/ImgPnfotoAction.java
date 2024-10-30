/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.image;

import com.epbt2.sspg.ref.dao.Pnfoto;
import com.epbt2.sspg.ref.dao.PnfotoDao;
import com.epbt2.sspg.ref.dao.PnfotoDaoExt;
import com.epbt2.util.CreateDirectory;
import com.epbt2.util.DbHelper;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 *
 * @author otasoft01
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImgPnfotoAction extends ActionSupport implements ServletRequestAware {

    private final Log log = LogFactory.getLog(this.getClass());

    private Connection conn;

    PnfotoDaoExt photoDaoExt = new PnfotoDaoExt();
    PnfotoDao photoDao = new PnfotoDao();
    Pnfoto photo = new Pnfoto();

    byte[] imageInByte;
    String imageId;
    String imgDir;

    InputStream orinImg;

    private HttpServletRequest servletRequest;

    CreateDirectory mkdir = new CreateDirectory();
    ImageUtil imageUtil = new ImageUtil();

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /*public ImageAction() {
     System.out.println("ImageAction");
     }*/
    public String execute() throws Exception {

        conn = DbHelper.getConnectionDS();
        try {
            imgDir = mkdir.createDirectoryImg(conn);

            //imgDir = paramDaoExt.sysParamValue(conn, "94");
        } catch (Exception e) {
            addActionError("Error : " + e);
        } finally {
            DbHelper.closeConnection(conn);
        }
        return SUCCESS;
    }

    public byte[] imgFromDbase() throws Exception {
        conn = DbHelper.getConnectionDS();

        try {
            photo = photoDao.getObject(conn, imageId);
            imageInByte = photo.getPnfGfotoByte();

        } catch (Exception e) {
            addActionError("Error : " + e);
        } finally {
            DbHelper.closeConnection(conn);
        }
        return imageInByte;
    }

    public void imgFromDbaseIS() throws Exception {
        FileOutputStream file = null;
        conn = DbHelper.getConnectionDS();
        try {
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("SELECT PNF_GFOTO FROM SSPG.PNFOTO WHERE (PNF_NOPEK = '" + imageId + "' ) ");

            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                InputStream orinImg = rset.getBinaryStream(1);

                try {
                    file = new FileOutputStream(imgDir + imageId + ".tiff");
                    int chunk;
                    while ((chunk = orinImg.read()) != -1) {
                        file.write(chunk);
                    }
                    
                    imageUtil.convertImage(imgDir, imageId);
                    /*SeekableStream s = new FileSeekableStream(imgDir + imageId + ".tiff");
                     TIFFDecodeParam param = null;
                     ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
                     RenderedImage op = dec.decodeAsRenderedImage(0);

                     FileOutputStream fos = new FileOutputStream(imgDir + imageId + ".png");
                     JPEGEncodeParam jpgparam = new JPEGEncodeParam();
                     jpgparam.setQuality(67);
                     ImageEncoder en = ImageCodec.createImageEncoder("jpeg", fos, jpgparam);
                     en.encode(op);
                     fos.flush();
                     fos.close();*/

                    File fileImg = new File(imgDir + imageId + ".png");

                    saveConvertImage(fileImg);
                } catch (Exception e) {
                    String err = e.toString();
                    System.out.println(err);
                } finally {
                    if (file != null) {
                        file.close();
                    }
                }
            }

        } catch (Exception e) {
            addActionError("Error : " + e);
        } finally {
            DbHelper.closeConnection(conn);
        }
        //return orinImg;
    }

    public String saveConvertImage(File fileImg) throws Exception {
        conn = DbHelper.getConnectionDS();

        try {
            photoDaoExt.updateFoto(conn, photo, fileImg);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
            //imgPng.delete();
        }
        return INPUT;

    }

    public String getCustomContentType() {
        return "image/png";
    }

    public String getCustomContentDisposition() {
        return "anyname.jpg";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;

    }

    public InputStream getOrinImg() {
        return orinImg;
    }

    public void setOrinImg(InputStream orinImg) {
        this.orinImg = orinImg;
    }

    public byte[] getImageInByte() {
        return imageInByte;
    }

    public void setImageInByte(byte[] imageInByte) {
        this.imageInByte = imageInByte;
    }

}
