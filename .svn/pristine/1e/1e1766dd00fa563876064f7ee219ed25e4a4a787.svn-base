<%-- 
    Document   : baseLayout2
    Created on : Feb 25, 2016, 9:00:20 PM
    Author     : BYE
--%>
<%@ taglib prefix = "s" uri="/struts-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title><s:text name="system.name"/></title>
        <link rel="icon" sizes="192x192" href="<%=request.getContextPath()%>/props/img/logo_03.png"> 
        <s:include value="inc2/head.jsp"/>
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <!-- sidebar menu -->
                        <tiles:insertAttribute name="menu"/>
                        <!-- /sidebar menu -->
                    </div>
                </div>

                <!-- top navigation -->
                <tiles:insertAttribute name="top"/>                
                <!-- /top navigation -->


                <!-- page content -->
                <div class="right_col" role="main">

                    <!-- top tiles -->
                    <!-- /top tiles -->
                    <tiles:insertAttribute name="body"/>   
                    <!-- /footer content -->
                </div>
                <!-- /page content -->

            </div>

        </div>

        <div id="custom_notifications" class="custom-notifications dsp_none">
            <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
            </ul>
            <div class="clearfix"></div>
            <div id="notif-group" class="tabbed_notifications"></div>
        </div>
        <s:include value="inc2/footer.jsp"/>
    </body>

</html>


