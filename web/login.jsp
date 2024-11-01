<%-- 
    Document   : login
    Created on : Nov 19, 2014, 4:08:31 PM
    Author     : Uzzair Baharudin
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><s:text name="system.name"/></title>
        <link rel="icon" sizes="192x192" href="<%=request.getContextPath()%>/props2/images/logo_04.jpg"> 

        <link href="<%=request.getContextPath()%>/props2/css/login.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/props2/fonts/css/font-awesome.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/props2/js/jquery-1.7.1.js" charset="utf-8" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/props2/js/browser-validator.js" charset="utf-8" type="text/javascript"></script>
        <style>

            .login-wrap{
                width:100%;
                margin:auto;
                max-width:525px;
                //min-height:670px;
                min-height:100vh;
                position:relative;
                //background: url('props2/images/bg.jpg') no-repeat center;
                box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
            }
            .login-container{

                background: url('props2/images/mds2.jpg') no-repeat center;
                width:100%;
            }
        </style>


    </head>
    <body>

        <div class="login-container">
            <div class="login-wrap">
                <div class="login-html">
                    <div class="login-form">


                        <div class="col-sm-12 form-control-static" >
                            <s:if test="hasActionMessages()">
                                <div class="alert alert-warning">
                                    <span class="close"></span>
                                    <s:actionmessage/>
                                </div>              
                            </s:if>
                            <s:if test="hasActionErrors()">
                                <div class="alert alert-danger">
                                    <span class="close"></span>
                                    <s:actionerror/>                  
                                </div>                 
                            </s:if>
                        </div>
                        <div id="login" class="animate form" style="padding: 45px 0 0;">

                            <s:form cssClass="form-login" action="saveLogin" method="post">


                                <div class="col-sm-12 form-control-static" style="height:55px">                                                    
                                    <div class="group">
                                        <s:textfield name="user.userId" cssClass="input" placeholder="%{getText('user.userId')}" autofocus="true" oninput="this.value = this.value.toUpperCase()" onkeydown="return tab(this, event);"/>
                                        <span class="req"><s:fielderror> <s:param>user.userId</s:param> </s:fielderror></span>
                                            </div>

                                            <div class="group">
                                        <s:password name="user.userPassword" cssClass="input" placeholder="%{getText('user.userPassword')}" oninput="this.value = this.value.toUpperCase()" onkeydown="return tab(this, event);"/>
                                        <span class="req"><s:fielderror> <s:param>user.userPassword</s:param> </s:fielderror></span>
                                            </div>

                                            <div class="group">
                                        <s:submit cssClass="button" href="" value="Log Masuk"/>   
                                    </div>

                                </div>
                            </s:form>


                        </div>


                    </div>
                    <%--
                            <div class="col-sm-12 form-control-static" style="height:55px">   
                                                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


                                                <p style="font-size: 1; "> <small>::HakciptaÂ©2017 Jabatan Kerajaan Tempatan (JKT KPKT):: <br>
                                                        Paparan terbaik:  Mozilla Firefox dengan resolusi melebihi 1024x768px<br> </small> </p>
                            </div>
                    --%>
                </div>


            </div>
        </div>

    </div>


</body>
</html>
<script>
    /* $(document).ready(function () {
     var cookies = document.cookie.split("; ");
     for (var c = 0; c < cookies.length; c++) {
     var d = window.location.hostname.split(".");
     while (d.length > 0) {
     var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
     var p = location.pathname.split('/');
     document.cookie = cookieBase + '/';
     while (p.length > 0) {
     document.cookie = cookieBase + p.join('/');
     p.pop();
     }
     ;
     d.shift();
     }
     }
     });*/
    window.location.hash = "no-back-button";
    window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
    window.onhashchange = function () {
        window.location.hash = "#";
    }

    /*
     $(document).ready(function() {
     if ("false" ===  window.parent.$('.modal').attr('aria-hidden')) {
     window.parent.$(".modal").modal('hide');
     window.parent.location.href="../ePBT";
     } else {
     console.log("NO");
     }
     });*/

    function tab(field, event) {
        if (event.which === 13 /* IE9/Firefox/Chrome/Opera/Safari */ || event.keyCode === 13 /* IE8 and earlier */) {
            var focussableElements = 'a:not([disabled]), button:not([disabled]), input[type=text],[type=password]:not([disabled]),[type=checkbox], [tabindex]:not([disabled]):not([tabindex="-1"])';
            if (document.activeElement && document.activeElement.form) {
                var focussable = Array.prototype.filter.call(document.activeElement.form.querySelectorAll(focussableElements),
                        function (element) {
                            //check for visibility while always include the current activeElement 
                            return element.offsetWidth > 0 || element.offsetHeight > 0 || element === document.activeElement
                        });
                var index = focussable.indexOf(document.activeElement);
                focussable[index + 1].focus();
            }
            return false;
        }
        return true;
    }

    $(document).ready(function () {
        BrowserValidator.init({
           /* webkit: {
                majorVersionMax: 0,
                prompt: "Amaran, Sila gunakan browser Mozilla Firefox untuk mengelakkan masalah pada data anda! Masalah data akibat daripada penggunaan browser lain tidak akan dilayan!!"
            },*/
            msie: {
                majorVersionMax: 0,
                prompt: "Amaran, Sila gunakan browser Mozilla Firefox untuk mengelakkan masalah pada data anda! Masalah data akibat daripada penggunaan browser lain tidak akan dilayan!!"
            },
            opera: {
                majorVersionMax: 0,
                prompt: "Amaran, Sila gunakan browser Mozilla Firefox untuk mengelakkan masalah pada data anda! Masalah data akibat daripada penggunaan browser lain tidak akan dilayan!!"
            }
            
        }).validateBrowser();
    });
</script>
