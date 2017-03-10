
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<%=request.getContextPath()%>/css/general/cashier-entry.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/cashier-entry-controller.js"></script>

<div ng-controller="loginFormCtrl as loginForm">

    <div class="login_forward">
        <%--<img src="<%=request.getContextPath()%>/img/main/merchant_main_header_background.png"--%>
             <%--alt="<s:text name="page.cashierEntry.alt.Cover.Image">Cover Image</s:text>"--%>
             <%--class=" merchant_background_img">--%>
        <%--<div class="login-forward-header-padding-top">--%>
            <%--<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">--%>
                <%--<a href="<%=request.getContextPath()%>">--%>
                    <%--<img src="<%=request.getContextPath()%>/img/main/merchant_main_logo.png" alt="<s:text name="alt.main.Logo">Logo</s:text>"/>--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</div>--%>

        <form action="cashier-entry.htm" method="post" autocomplete="off" >
            <input type="password" style="display: none;" />
            <div class="form-login-message"></div>
            <div class="login-form col-lg-4 col-md-6 col-sm-6 col-xs-8" >
                <div class="login_form_title">
                    <s:text name="page.cashierEntry.user.login">USER LOGIN</s:text>
                </div>

                <div class="form-group-username ">

                    <input type="text" class="form-control login_form_login" name="username"
                           placeholder="<s:text name="page.cashierEntry.placeholder.Username">Username</s:text>"
                           autocomplete="off" />
                </div>

                <div class="form-group-password">
                    <img ng-click="loginForm.passwordShowHideAndIconSrc()" class="show_hide_password" ng-cloak ng-src="{{loginForm.passwordShowHideIconSrc}}"
                         alt="<s:text name="page.cashierEntry.alt.Show.Hide.Password">Show/Hide Password</s:text>"/>
                    <input type="{{loginForm.inputType}}" class="form-control login_form_password" name="password"
                           placeholder="<s:text name="page.cashierEntry.placeholder.Password">Password</s:text>"
                           autocomplete="new-password" />
                </div>
                <div class="login_form_forgot_password  ">
                    <a href="forgot-password.htm">
                        <s:text name="page.login.Forgot.Password">Forgot Password ?</s:text>
                    </a>
                </div>
                <button type="submit" class="login_button col-lg-8 col-md-10 col-sm-10 col-xs-10">
                    <s:text name="page.login.login.button">login</s:text>
                </button>
                <div class="login_form_dont_have_account  ">
                    <s:text name="dont.have.an.account">Don't have an account ?</s:text>
                    <a href="signup.htm">
                        <s:text name="page.login.sign.up">sign up</s:text>
                    </a>
                </div>
            </div>
        </form>
    </div>
    <div style="clear: both"></div>
</div>