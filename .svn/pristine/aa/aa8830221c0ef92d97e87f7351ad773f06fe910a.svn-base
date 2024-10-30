<%-- 
    Document   : sidebar
    Created on : Feb 25, 2016, 10:03:32 PM
    Author     : BYE
--%>

<%@taglib prefix = "s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="navbar nav_title" style="border: 0;">
    <a href="#" class="site_title"><img src="<s:url namespace="/Image" action='logoImage?imageId=1'/>" height="60px" width="60px"/>
        <%--img src="<%=request.getContextPath()%>/props2/images/logo_03.png"/--%>
        <img src="<%=request.getContextPath()%>/props2/images/mds5.jpg"/>
        <%--i class="fa fa-paw"></i--%> <span><s:property value="%{moduleName}"/></span></a>
</div>
<div class="clearfix"></div>

<!-- menu prile quick info -->
<%--<div class="profile">
    <div class="profile_pic">
        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
    </div>
    <div class="profile_info">
        <span>Welcome,</span>
        <h2>Anthony Fernando</h2>
    </div>
</div>
--%>
<!-- /menu prile quick info -->

<br />

<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

    <div class="menu_section">
        <h3>&nbsp;</h3>
        <ul class="nav side-menu">

            <!--------------- MAIN PART ------------>
            <li><a onclick="javascript:location.href = '<%=request.getContextPath()%>/common/welcome'" ><i class="fa fa-laptop"></i> Laman Utama Sistem</a>
            </li>
            <s:iterator var="stdMenu" value="menuLevel1List">
                <s:set name="level1Node"><s:property value="%{menuMainId}"/></s:set>
                <s:set name="level1GroupId"><s:property value="%{menuGroupId}"/></s:set>
                    <li>        
                        <a>
                        <i class="<s:text name="%{menuIcon}"/>"></i> <span><s:text name="%{menuMainDesc}"/></span>
                        <span class="fa fa-chevron-down"></span>
                    </a>                   
                    <ul class="nav child_menu" style="display: none">
                        <s:iterator var="stdMenu" value="menuLevel2List">
                            <s:set name="level2Node"><s:property value="%{menuMainGroup}"/></s:set>
                            <s:set name="iconSts"><s:property value="%{menuIconSts}"/></s:set>            
                            <s:if test="#level1Node.equals(#level2Node)">

                                <s:url var="myUrl" action="sistem" namespace="/common">
                                    <s:param name="menuGroup"><s:property value="%{menuCall}"/></s:param>
                                </s:url>
                                <li>
                                    <s:if test="webApplType.equals(\"A\")">
                                        <s:if test="projectName != null">
                                            <s:a href="%{projectName}/%{actionLink}?menuGroup=%{menuGroupId}&userId=%{userModel.userId}"><s:property value="%{menuDesc}" escape="false"/>
                                                <s:if test="bilNotify != 0">&nbsp;&nbsp;&nbsp;&nbsp;<span class="label label-success"><s:property value="bilNotify"/></span></s:if></s:a>                           
                                        </s:if>

                                        <s:else>
                                            <s:a href="#"><s:property value="%{menuDesc}" escape="false"/></s:a>                           
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="webApplType.equals(\"G\")">
                                        <s:a href="%{projectName}/%{actionLink}?menuGroup=%{menuCall}&userId=%{userModel.userId}"><s:property value="%{menuDesc}" escape="false"/></s:a>      

                                    </s:elseif>
                                    <s:elseif test="webApplType.equals(\"L\")">
                                        <s:a namespace="/%{nameSpace}" action="%{actionLink}"><s:property value="%{menuDesc}" escape="false"/></s:a>  

                                    </s:elseif>
                                    <s:else>
                                        <s:a href="../common/sistem?menuGroup=%{menuCall}"><s:property value="%{menuDesc}" escape="false"/></s:a>      
                                    </s:else>
                                </li>
                            </s:if>
                        </s:iterator>
                    </ul>
                </li>
            </s:iterator>
        </ul>
    </div>
</div>
