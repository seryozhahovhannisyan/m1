<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %><%--
  Created by IntelliJ IDEA.
  User: David
  Date: 7/26/2016
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<%=request.getContextPath()%>/css/general/cashier-entry.css" rel="stylesheet">

<script type="text/javascript">
    merchantApp = angular.module('merchantApp', []);
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/cashier-entry-controller.js"></script>

<div ng-controller="loginFormCtrl as login">
    <div class="login_forward">
        <img src="<%=request.getContextPath()%>/img/main/merchant_main_header_background.png"
             alt="<s:text name="page.forgotPassword.alt.Cover.Image">Cover Image</s:text>"
             class=" merchant_background_img"></a>

        <form action="user-entry.htm" method="post" autocomplete="off" >
            <input type="password" style="display: none;"/>
            <div class="form-login-message"></div>
            <div class="login-form col-lg-4 col-md-6 col-sm-6 col-xs-8" >
                <div class="login_form_title">
                    <s:text name="page.forgotPassword.reset.password">RESET PASSWORD</s:text>
                </div>
                <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                <div class="form-group-username form-group-username-margin col-lg-8 col-md-10 col-sm-10 col-xs-10">
                    <input type="text" class="form-control login_form_login" name="forgot_password"
                           placeholder="<s:text name="page.forgotPassword.placeholder.Username">Username</s:text>"
                           autocomplete="off" />
                </div>

                <button type="submit" class="login_button col-lg-8 col-md-10 col-sm-10 col-xs-10">
                    <s:text name="page.forgotPassword.button.send">SEND</s:text>
                </button>
                <div class="login_form_dont_have_account col-lg-8 col-md-10 col-sm-10 col-xs-10">
                    <s:text name="dont.have.an.account">Don't have an account ?</s:text>
                    <a href="signup.htm">
                        <s:text name="page.login.sign.up">sign up</s:text>
                    </a>
                </div>
            </div>
        </form>
         </div>
</div>