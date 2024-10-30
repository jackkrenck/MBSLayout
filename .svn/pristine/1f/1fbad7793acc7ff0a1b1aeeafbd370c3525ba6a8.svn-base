/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.image;

import com.epbt2.common.dao.StdSysParamDaoExt;
import com.epbt2.spbt.ref.dao.FImage;
import com.epbt2.spbt.ref.dao.FImageDao;
import com.epbt2.spbt.ref.dao.FImageDaoExt;
import com.epbt2.util.CreateDirectory;
import com.epbt2.util.DbHelper;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
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

/**
 *
 * @author SharifahNorzaiti
 */
public class ImgFimageAction extends ActionSupport implements ServletRequestAware {

    private final Log log = LogFactory.getLog(this.getClass());

    private Connection conn;

    private FImageDaoExt photoDaoExt = new FImageDaoExt();
    private FImageDao photoDao = new FImageDao();
    private FImage photo = new FImage();
    private StdSysParamDaoExt paramDaoExt = new StdSysParamDaoExt();

    byte[] imageInByte;
    String imageId;
    String imgDir;

    InputStream orinImg;

    private HttpServletRequest servletRequest;

    CreateDirectory mkdir = new CreateDirectory();
    ImageUtil imageUtil = new ImageUtil();

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
        FileOutputStream file = null;
        conn = DbHelper.getConnectionDS();
        try {
            photo = photoDao.getObject(conn, imageId);

            imageInByte = photo.getImageByte();

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

            stmt = conn.prepareStatement("SELECT FIM_LOGOM FROM SPBT.FIMAGE WHERE (FIM_DFTAR = '" + imageId + "') ");

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

                    /*BufferedImage source = ImageIO.read(new File("/ePBT/IMG/convert.png"));

                     int color = source.getRGB(0, 0);

                     Image image = imageUtil.makeColorTransparent(source, new Color(color));

                     BufferedImage transparent = imageUtil.imageToBufferedImage(image);*/
                    File fileImg = new File(imgDir + imageId + ".png");
                    //ImageIO.write(transparent, "PNG", fileImg);

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
            photoDaoExt.convertFoto(conn, photo, fileImg);
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public FImage getPhoto() {
        return photo;
    }

    public void setPhoto(FImage photo) {
        this.photo = photo;
    }

    public byte[] getImageInByte() {
        return imageInByte;
    }

    public void setImageInByte(byte[] imageInByte) {
        this.imageInByte = imageInByte;
    }

    public InputStream getOrinImg() {
        return orinImg;
    }

    public void setOrinImg(InputStream orinImg) {
        this.orinImg = orinImg;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getCustomContentType() {
        return "image/png";
    }

    public String getCustomContentDisposition() {
        return "anyname.png";
    }

}
