/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.common;

import com.epbt2.common.dao.StdSysParamDaoExt;
import com.epbt2.common.dao.StdUser;
import com.epbt2.common.dao.StdUserDao;
import com.epbt2.common.dao.StdUserDaoExt;
import com.epbt2.common.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import com.epbt2.util.DbHelper;
import com.epbt2.util.GetNetworkAddress;
import com.epbt2.util.PCDetail;
import com.opensymphony.xwork2.Preparable;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author ghani
 */
public class LoginAction extends ActionSupport implements SessionAware, ModelDriven<User>, ServletContextAware, ServletRequestAware, ServletResponseAware, Preparable {//, ServletResponseAware {

    private Log log = LogFactory.getLog(this.getClass());

    private StdUser user = new StdUser();
    private Connection conn;
    private StdUserDao userDao = new StdUserDao();
    private User userModel = new User();
    private Map<String, Object> session = null;
    private PCDetail pcDetail = new PCDetail();
    private GetNetworkAddress netAddress = new GetNetworkAddress();
    private String ipAddress;
    /* BYE 30/10/2015 */
    private StdUserDaoExt userDaoExt = new StdUserDaoExt();
    private StdUser maklJbtn = new StdUser();
    /* BYE 30/10/2015 */

    /* BYE 14/02/2017 */
    private StdSysParamDaoExt paramDaoExt = new StdSysParamDaoExt();
    private String namaMajlis;

    private PakejAkses aksesPakej = new PakejAkses();
    private String[] maklNetwork = new String[3];

    @Override
    public void prepare() throws Exception {
        conn = DbHelper.getConnectionDS();

        try {
            namaMajlis = paramDaoExt.sysParamValue(conn, "4").toLowerCase();
            log.debug("namaMajlis: " + namaMajlis);

        } catch (SQLException e) {
            addActionError("Error : " + e);
            log.error(e, e.fillInStackTrace());
        } finally {
            DbHelper.closeConnection(conn);
        }
    }

    public String save() throws Exception {
        if (user.getUserId() != null || user.getUserPassword() != null) {
            conn = DbHelper.getConnectionDS();
            try {

                user = userDao.getObject(conn, user.getUserId(), user.getUserPassword());
                if (user.getUserStatus().equals("T")) {
                    addActionError(getText("Kod Pengguna Yang Dimasukkan Tidak Wujud Atau Tidak Aktif!"));
                    return INPUT;
                } else {

                    maklNetwork = netAddress.getAddress();

                    log.debug("Ip by: " + maklNetwork[0]);
                    log.debug("Mac by : " + maklNetwork[1]);
                    log.debug("PcName by : " + maklNetwork[2]);
                    userModel.setUserId(user.getUserId() );
                    userModel.setUserName(user.getUserName());
                    userModel.setUserGroupId(user.getUserGroupId());

                    if (ServletActionContext.getRequest().getRemoteHost().equals("0:0:0:0:0:0:0:1")) {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } else {
                        ipAddress = ServletActionContext.getRequest().getRemoteHost();
                    }
                    log.debug("IP ADRESS :" + ipAddress);

                   // log.debug("Mac by Linux : " + pcDetail.MACAddress(ipAddress));
                    // log.debug("PcName by LINUx : " + pcDetail.compName(ipAddress));
                    userModel.setUserIpAddr(ipAddress);
                    /* MAC ADDRESS */
                    // userModel.setUserMacAddress(pcDetail.MACAddress(ipAddress));
                    userModel.setUserMacAddress(maklNetwork[1]);

                    /* MAC ADDRESS */
                    System.out.println("IP ADDRESS:" + ipAddress);
                    /* PC NAME */
                    //userModel.setUserPcName(pcDetail.pcName(conn));
                    //userModel.setUserPcName(pcDetail.compName(ipAddress));
                    userModel.setUserPcName(maklNetwork[2]);
                    if (!user.getUserGroupId().equals("ADMIN")) {
                        /* BYE 30/10/2015 */
                        maklJbtn = userDaoExt.maklJabatan(conn, user.getUserId());
                        userModel.setUserJbKod(maklJbtn.getPneJbkod());
                        userModel.setUserJbNama(maklJbtn.getPtjPnama());
                        //userModel.setUserJbKod(userDaoExt.userJbKod(conn, user.getUserId()));
                    } else {
                        userModel.setUserJbKod(user.getUserGroupId());
                        userModel.setUserJbNama(user.getUserGroupId());
                    }
                    /* BYE 30/10/2015 */
                    System.out.println("USERMODEL:" + userModel.getUserPcName() + "||MAC :" + userModel.getUserMacAddress() + "||JAB :" + userModel.getUserJbKod() + "||JABNAMA :" + userModel.getUserJbNama());
                //String ipAddress = java.net.InetAddress.getLocalHost().getHostName();
                    //System.out.println("PC NAME:"+ipAddress);
                /* PC NAME */
                    /*  HttpServletRequest request = CLIENT_CERT_AUTH ;
                     String remoteAddress = request.getRemoteAddr();
                     InetAddress inetAddress = InetAddress.getByName(remoteAddress);
                     System.out.println("inetAddress: " + inetAddress);
                     log.debug("computerName: " + pcDetail.getHostName(inetAddress));*/
                    //infoUser(conn, userModel.getUserId() + "|" + userModel.getUserMacAddress() + "|" + userModel.getUserPcName() + "|");
                    aksesPakej.infoUser(conn, userModel.getUserId() + "|" + userModel.getUserMacAddress() + "|" + userModel.getUserPcName() + "|");
                    log.debug(user.getUserId() + ":" + user.getUserName() + ":" + user.getUserGroupId() + ":" + userModel.getUserMacAddress() + ":" + userModel.getUserPcName() + ":" + userModel.getUserJbKod() + ":" + userModel.getUserJbNama() + ":" + request.getSession().getId());
                    aksesPakej.aksesUser(conn, user.getUserId(), user.getUserName(), user.getUserGroupId(), userModel.getUserMacAddress(), userModel.getUserPcName(), userModel.getUserJbKod(), userModel.getUserJbNama(), request.getSession().getId(), userModel.getUserIpAddr());
                    session.put("USER", userModel);

                    log.debug("request getsession :" + request.getSession().toString());
                    /*ServletContext siblingContext = request.getSession().getServletContext().getContext("/ePBT");

                     Map<String, User> data = new HashMap<String, User>();
                

                     System.out.println("EPBT:" + userModel.getUserId());
                     String id = request.getSession().getId();
                     data.put(id, userModel);
                     siblingContext.setAttribute("data", data);

                     Cookie user = new Cookie("user", userModel.getUserId());
                     user.setPath("/");
                     response.addCookie(user);*/
                    return "mainPage";
                }
            } catch (Exception e) {
                //log.error(e);
                //addActionError(getText("error.login"));
                e.printStackTrace();
            } finally {
                DbHelper.closeConnection(conn);
            }
        }
        return INPUT;
    }

    public void infoUser(Connection conn, String info) throws SQLException {
        String sql = "{ call sys.sp_sys.put_info('" + info + "') } ";
        System.out.println(sql);
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = conn.prepareCall(sql);

            result = stmt.executeQuery();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public StdUser getUser() {
        return user;
    }

    public void setUser(StdUser user) {
        this.user = user;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public User getUserModel() {
        return userModel;
    }

    public void setUserModel(User userModel) {
        this.userModel = userModel;
    }

    public String getNamaMajlis() {
        return namaMajlis;
    }

    public void setNamaMajlis(String namaMajlis) {
        this.namaMajlis = namaMajlis;
    }

    @Override
    public User getModel() {
        return userModel;
    }

    /* protected HttpServletResponse servletResponse;

     @Override
     public void setServletResponse(HttpServletResponse servletResponse) {
     this.servletResponse = servletResponse;
     }*/
    private HttpServletResponse response;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
    ServletContext context;

    public void setServletContext(ServletContext servletContext) {
        // servletContext.setAttribute("USER", value);  

        this.context = context;
    }
    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
