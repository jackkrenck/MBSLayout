<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
    <div class="col-lg-12">
        <div class="content-wrapper" style="min-height: 416px;">
            <div class="content">
                <div class="row">
                    <s:iterator value="menuLevel2List" var="menuLevel2" status="menuLevel2Sts">

                        <div class="col-sm-2">
                            <div class="circle-tile">
                                
                                <s:if test="webApplType.equals(\"A\")">
                                <s:a href="%{projectName}/%{actionLink}?menuGroup=%{menuGroupId}&userId=%{userModel.userId}" cssStyle="text-decoration : none">
                                    <div class="circle-tile-heading gray">
                                        <i class="<s:property value="menuIcon"/> fa-fw fa-3x"></i>
                                    </div>
                                    <s:if test="((#menuLevel2Sts.index + 1) % 6) ==  0">
                                        <div class="circle-tile-content dark-blue">
                                        </s:if>     
                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  1">
                                            <div class="circle-tile-content green">
                                            </s:elseif>   
                                            <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  2">
                                                <div class="circle-tile-content orange">
                                                </s:elseif>   
                                                <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  3">
                                                    <div class="circle-tile-content blue">
                                                    </s:elseif> 
                                                    <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  4">
                                                        <div class="circle-tile-content red">
                                                        </s:elseif> 
                                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  5">
                                                            <div class="circle-tile-content purple">
                                                            </s:elseif>
                                                            <div class="circle-tile-description text-faded">
                                                                <s:property value="menuDesc"/>
                                                            </div>
                                                            <%--div class="circle-tile-number text-faded">
                                                                265
                                                                <span id="sparklineA"><canvas style="display: inline-block; width: 29px; height: 24px; vertical-align: top;" width="29" height="24"></canvas></span>
                                                            </div>
                                                            <a href="#" class="circle-tile-footer">More Info <i class="fa fa-chevron-circle-right"></i></a--%>
                                                        </div>
                                                    </s:a>
                                </s:if>
                                <s:elseif test="webApplType.equals(\"G\")">
                                <s:a href="%{projectName}/%{actionLink}?menuGroup=%{menuCall}&userId=%{userModel.userId}" cssStyle="text-decoration : none">                                    <div class="circle-tile-heading gray">
                                        <i class="<s:property value="menuIcon"/> fa-fw fa-3x"></i>
                                    </div>
                                    <s:if test="((#menuLevel2Sts.index + 1) % 6) ==  0">
                                        <div class="circle-tile-content dark-blue">
                                        </s:if>     
                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  1">
                                            <div class="circle-tile-content green">
                                            </s:elseif>   
                                            <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  2">
                                                <div class="circle-tile-content orange">
                                                </s:elseif>   
                                                <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  3">
                                                    <div class="circle-tile-content blue">
                                                    </s:elseif> 
                                                    <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  4">
                                                        <div class="circle-tile-content red">
                                                        </s:elseif> 
                                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  5">
                                                            <div class="circle-tile-content purple">
                                                            </s:elseif>
                                                            <div class="circle-tile-description text-faded">
                                                                <s:property value="menuDesc"/>
                                                            </div>
                                                            <%--div class="circle-tile-number text-faded">
                                                                265
                                                                <span id="sparklineA"><canvas style="display: inline-block; width: 29px; height: 24px; vertical-align: top;" width="29" height="24"></canvas></span>
                                                            </div>
                                                            <a href="#" class="circle-tile-footer">More Info <i class="fa fa-chevron-circle-right"></i></a--%>
                                                        </div>
                                                    </s:a>
                                </s:elseif>
                                <s:else>
                                <s:a href="common/sistem?menuGroup=%{menuCall}" cssStyle="text-decoration : none"> 
                                                    <div class="circle-tile-heading gray">
                                        <i class="<s:property value="menuIcon"/> fa-fw fa-3x"></i>
                                    </div>
                                    <s:if test="((#menuLevel2Sts.index + 1) % 6) ==  0">
                                        <div class="circle-tile-content dark-blue">
                                        </s:if>     
                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  1">
                                            <div class="circle-tile-content green">
                                            </s:elseif>   
                                            <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  2">
                                                <div class="circle-tile-content orange">
                                                </s:elseif>   
                                                <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  3">
                                                    <div class="circle-tile-content blue">
                                                    </s:elseif> 
                                                    <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  4">
                                                        <div class="circle-tile-content red">
                                                        </s:elseif> 
                                                        <s:elseif test="((#menuLevel2Sts.index + 1) % 6) ==  5">
                                                            <div class="circle-tile-content purple">
                                                            </s:elseif>
                                                            <div class="circle-tile-description text-faded">
                                                                <s:property value="menuDesc"/>
                                                            </div>
                                                            <%--div class="circle-tile-number text-faded">
                                                                265
                                                                <span id="sparklineA"><canvas style="display: inline-block; width: 29px; height: 24px; vertical-align: top;" width="29" height="24"></canvas></span>
                                                            </div>
                                                            <a href="#" class="circle-tile-footer">More Info <i class="fa fa-chevron-circle-right"></i></a--%>
                                                        </div>
                                                    </s:a>                                  
                                </s:else>
                                                </div>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>