<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="<%=request.getContextPath()%>/css/layot/header_area.css" rel="stylesheet">
<script>
    generalControllers.mainLoginFormCtrl = ['$scope', function ($scope) {
        var vm = this;
        vm.mainPasswordShowHideIconSrc = context + '/img/general/icon/login/dont_show.png';
        vm.inputType = 'password';
        var showPasswordPng = context + '/img/general/icon/login/show_password.png';
        var hidePasswordPng = context + '/img/general/icon/login/dont_show.png';
        var showHide = false;

        function mainPasswordShowHide(text) {
            vm.inputType = text;
        };
        function mainPasswordShowHideIconSrc(src) {
            vm.mainPasswordShowHideIconSrc = src;
        };
        vm.mainPasswordShowHideAndIconSrc = function () {

            if (!showHide) {
                mainPasswordShowHide('text');
                mainPasswordShowHideIconSrc(showPasswordPng);
            } else {
                mainPasswordShowHide('password');
                mainPasswordShowHideIconSrc(hidePasswordPng);
            }
            showHide = !showHide;

        }

        var mainLoginForm = false;
        vm.mainLoginFormDisplay = {
            "display": 'none'
        };
        vm.fullPageFixedDiv = {
            "position": 'initial'
        }
        function mainLoginFormShowHideByDisplay(Display) {
            vm.mainLoginFormDisplay = Display;
        }

        function fullPageFixedDiv(position) {
            vm.fullPageFixedDiv = position;
        }


        vm.mainLoginFormShowHide = function () {
            if (!mainLoginForm) {

                mainLoginFormShowHideByDisplay({
                    "display": 'block'
                });
                fullPageFixedDiv({
                    "position": 'fixed'
                });
            } else {
                mainLoginFormShowHideByDisplay({
                    "display": 'none'
                });
                fullPageFixedDiv({
                    "position": 'initial'
                });
            }
            mainLoginForm = !mainLoginForm;
        };

        vm.closeLoginFormOnClickBody = function closeLoginFormOnClickBody() {
            mainLoginFormShowHideByDisplay({
                "display": 'none'
            });

            fullPageFixedDiv({
                "position": 'initial'
            });
            mainLoginForm = !mainLoginForm;
        }

    }];
</script>
<section class="header_section" ng-controller="mainLoginFormCtrl as login" id="header_section">
    <div class="full_page_fixed_div" ng-style="login.fullPageFixedDiv" ng-click="login.closeLoginFormOnClickBody()"></div>
    <div class="main-header-padding-top">
        <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <a href="start.htm">
                <img src="<%=request.getContextPath()%>/img/main/merchant_main_logo.png" alt="<s:text name="alt.main.Logo">Logo</s:text>" />
            </a>
        </div>
        <div class=" col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right main_header_right_block">
            <div class="main_right_menu_block">
                <div class="branch_area">
                    <p onclick="goToAction('/company-area.htm')">
                        <s:text name="page.area.header.Company.Area">Company Area</s:text>
                    </p>
                    <div class="text_hover"></div>
                </div>
                <div class="user_area ">
                    <p onclick="goToAction('/cashier-area.htm')">
                        <s:text name="page.area.header.Cashier.Area">Cashier Area</s:text>
                    </p>
                    <div class="text_hover"></div>
                </div>
            </div>
            <img src="<%=request.getContextPath()%>/img/main/merchant_header_right_icon.png"
                 alt="<s:text name="page.area.header.alt.Login.Icon">Login</s:text>"
                 ng-click="login.mainLoginFormShowHide()" class="merchant_header_right_icon" />
                <%--todo if logged in show block below hide img above--%>
            <%--<img src="<%=request.getContextPath()%>/img/main/merchant_header_right_icon.png"--%>
                 <%--alt="<s:text name="page.area.header.alt.Login.Icon">Login</s:text>"--%>
                  <%--class="merchant_header_right_icon" />--%>
            <%--<div class="logged_in_name_surname">--%>
                <%--<span>--%>
                    <%--Name Surname--%>
                <%--</span>--%>
            <%--</div>--%>
            <%--todo if logged in show block above--%>
        </div>
    </div>
    <form action="cashier-entry.htm" method="post" autocomplete="off" du-parallax y="mainContent.background">
        <input type="password" style="display: none;"/>
        <div class="main-form-login-message"></div>
        <div class="main-login-form pull-right col-lg-4 col-md-6 col-sm-6 col-xs-8" ng-style="login.mainLoginFormDisplay">
            <div class="main_login_form_title">
                <s:text name="page.login.cashier.login">cashier login</s:text>
            </div>
            <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
            <div class="form-group-username col-lg-8 col-md-10 col-sm-10 col-xs-10">
                <input type="text" class="form-control main_login_form_login" name="username"
                       placeholder="<s:text name="page.area.header.placeholder.Username">Username</s:text>"
                       autocomplete="off"/>
            </div>
            <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
            <div class="form-group-password col-lg-8 col-md-10 col-sm-10 col-xs-10">
                <img ng-click="login.mainPasswordShowHideAndIconSrc()" class="main_show_hide_password" ng-src="{{login.mainPasswordShowHideIconSrc}}"
                      alt="<s:text name="page.area.header.alt.Show.Hide.Password">Show/Hide Password</s:text>"/>
                <input type="{{login.inputType}}" class="form-control main_login_form_password" name="password"
                       placeholder="<s:text name="page.area.header.placeholder.Password">Password</s:text>"
                       autocomplete="new-password"/>
            </div>
            <div class="main_login_form_forgot_password col-lg-8 col-md-10 col-sm-10 col-xs-10">
                <a href="forgot-password.htm">
                    <s:text name="page.login.Forgot.Password">Forgot Password ?</s:text>
                </a>
            </div>
            <button type="submit" class="main_login_button col-lg-8 col-md-10 col-sm-10 col-xs-10">
                <s:text name="page.login.login.button">login</s:text>
            </button>
            <div class="main_login_form_dont_have_account">
                <s:text name="dont.have.an.account">Don't have an account ?</s:text>
                <a href="signup.htm">
                    <s:text name="page.login.sign.up">sign up</s:text>
                </a>
            </div>
        </div>
    </form>
</section>
