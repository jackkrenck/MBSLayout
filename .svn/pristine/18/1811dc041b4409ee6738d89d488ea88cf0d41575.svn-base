<%-- 
    Document   : top
    Created on : Feb 25, 2016, 10:01:34 PM
    Author     : BYE
--%>

<%@taglib prefix = "s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="top_nav">

    <div class="nav_menu">
        <nav class="" role="navigation">
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <s:if test="rekodWujud == 0">
                        <img src="<%=request.getContextPath()%>/props2/images/profile.png" alt="">
                        </s:if>
                        <s:else>
                            <img src="<s:url namespace="/Image" action='stafImage?imageId=%{userModel.userId}'/>"/>
                        </s:else>
                        <s:property value="%{userModel.userName}"/>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">               
                        <li><a href="/MBS/logOutUser"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                        </li>
                    </ul>
                </li>                
        </nav>
    </div>

</div>