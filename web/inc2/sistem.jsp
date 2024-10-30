<%-- 
    Document   : sistem
    Created on : Jul 17, 2014, 4:03:58 PM
    Author     : otasoft01
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <div class="col-lg-12">
        <div class="content-wrapper" style="min-height: 416px;">
            <div class="content">
                <div class="row">

                    <s:iterator value="iconList" var="icon" status="iconSts">
                        <s:if test="projectName != null">
                            <s:a href="%{projectName}/%{actionLink}?menuGroup=%{menuGroupId}&userId=%{userModel.userId}">                            
                                <div class="col-md-3 col-sm-4 col-xs-12">
                                    <div class="info-box">
                                        <s:if test="((#iconSts.index + 1) % 4) ==  0">
                                            <span class="info-box-icon bg-aqua" style="color:white">   
                                                <i class="<s:property value='menuIcon'/>"></i>
                                            </span>                            
                                                <s:if test="bilNotify != 0">
                                                    <span class="badge badge-popular">  
                                                        <s:property value="bilNotify"/>
                                                    </span>
                                                </s:if>
                                        </s:if>
                                        <s:elseif test="((#iconSts.index + 1) % 4) == 1">
                                            <span class="info-box-icon bg-red" style="color:white">   
                                                <i class="<s:property value='menuIcon'/>"></i>
                                            </span>                              
                                                <s:if test="bilNotify != 0">
                                                    <span class="badge badge-popular">     
                                                        <s:property value="bilNotify"/>
                                                    </span>
                                                </s:if>
                                        </s:elseif>
                                        <s:elseif test="((#iconSts.index + 1) % 4) == 2">
                                            <span class="info-box-icon bg-green" style="color:white">    
                                                <i class="<s:property value='menuIcon'/>"></i>
                                            </span>                               
                                                <s:if test="bilNotify != 0">
                                                    <span class="badge badge-popular">   
                                                        <s:property value="bilNotify"/>
                                                    </span>
                                                </s:if>
                                        </s:elseif>
                                        <s:elseif test="((#iconSts.index + 1) % 4) == 3">
                                            <span class="info-box-icon bg-yellow" style="color:white">     
                                                <i class="<s:property value='menuIcon'/>"></i>
                                            </span>                              
                                                <s:if test="bilNotify != 0">
                                                    <span class="badge badge-popular"> 
                                                        <s:property value="bilNotify"/>
                                                    </span>
                                                </s:if>
                                        </s:elseif>
                                        <div class="info-box-content">
                                            <span class="info-box-text"><h2><s:property value="menuDesc"/></h2></span>
                                            <%--span class="info-box-number">90<small>%</small></span--%>
                                        </div><!-- /.info-box-content -->
                                    </div><!-- /.info-box -->
                                </div><!-- /.col -->
                            </s:a>
                        </s:if>
                    </s:iterator>
                </div>
            </div>
        </div>       
    </div> <!-- end col-lg-12 -->
</div> <!-- end row -->


