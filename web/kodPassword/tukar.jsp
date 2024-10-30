<%-- 
    Document   : tukar
    Created on : Aug 23, 2018, 2:19:12 PM
    Author     : Bai
--%>


<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="../inc2/head_form.jsp"/>
<div class="row">
    <div class="col-lg-12">
        <div class="alert alert-success" id="infoMessage" style="display: none;"></div>
        <s:if test="hasActionMessages() || hasActionErrors()">
            <div id="panelUmum" class="panel-body">   
                <!-- messages & error -->
                <s:if test="hasActionMessages()">
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert">×</a>
                        <s:actionmessage/>
                    </div>
                </s:if>
                <s:if test="hasActionErrors()">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert">×</a>
                        <s:actionerror/>                                
                    </div>
                </s:if>                                      
                <!-- end messages & error -->    
            </div>
        </s:if>
        <!--main content starts here-->  
        <div class="panel panel-default">
            <div class="panel-heading">
                <span class="pull-right panel-options">
                    <i class="fa" id="toggle"></i>
                </span>
                <h3 class="panel-title">Penyelenggaraan Kata Laluan</h3>
            </div>
            <div id="panel" class="panel-body">
                <s:form cssClass="form-horizontal" namespace="/password" action="savePasswordLink2" method="post"> 
                    <div class="form-group">
                        <label class="col-sm-2 control-label">ID Pengguna :</label> 
                        <div class="col-sm-4 form-control-static">
                            <b><s:property value="pengguna.userId"/></b>
                            <s:hidden id="userId" name="pengguna.userId" /> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Nama Pengguna :</label> 
                        <div class="col-sm-4 form-control-static">
                            <b><s:property value="pengguna.userName"/></b>
                            <s:hidden id="userName" name="pengguna.userName"/> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Kata Laluan :</label> 
                        <div class="col-sm-4 form-control-static">
                            <s:password cssClass="form-control input-sm" id="userPassword" name="pengguna.userPassword"  maxLength="32" cssErrorClass="decoratedErrorField" onblur="checking()"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Pengesahan Kata Laluan :</label> 
                        <div class="col-sm-4 form-control-static">
                            <s:password cssClass="form-control input-sm" id="chkPassword" name="pengguna.chkPassword"  maxLength="32" cssErrorClass="decoratedErrorField" onblur="checking()"/>
                            <div id="info" style="display: none;"></div>     
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="operation-button"> 
                            <button type="submit" class="simpan confirm" data-msg="<s:text name="mesej.simpan"/>"></button>
                            <button type="reset" class="isisemula" ></button>
                        </div>
                    </div>        
                </s:form>
            </div>
        </div>
    </div>
</div> 
<script src="<%=request.getContextPath()%>/jscript/common.js"></script>   
<s:include value="../inc2/footer_form.jsp"/>