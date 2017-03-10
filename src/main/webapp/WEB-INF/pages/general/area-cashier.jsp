<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="<%=request.getContextPath()%>/css/general/area-branch.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular.scroll.min.0.6.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-parallax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/area-cashier-controller.js"></script>


<div ng-controller="cashierAreaContentCtrl as cashierAreaContent">
    <div class="area_company_header">
        <img src="<%=request.getContextPath()%>/img/main/merchant_main_header_background.png" alt="<s:text name="page.home.alt.Merchant.Header.Background">Merchant Header Background</s:text>" du-parallax y="cashierAreaContent.background" class=" merchant_main_header_background_img" />
        <div class="container">
            <div class="row">
                <div class="centered col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="img_logo_div centered">
                        <img src="<%=request.getContextPath()%>/img/area-company/company-area-logo-center.png"
                             alt="logo"/>
                    </div>
                    <div class="row">
                        <div class="centered  text_branch_area col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <s:text name="page.login.cashier.area.Cashier.Area">Cashier Area</s:text>
                        </div>
                        <div class="centered  new_to_wallet_merchant  col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <s:text name="page.login.cashier.area.New.to.merchant">New to Merchant? Please Read Instruction</s:text>
                        </div>
                    </div>
                </div>
            </div>
            <div style="clear: both; height: 60px"></div>
            <div class="row">

                <div class="left centered col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="centered">
                        <s:text name="page.login.cashier.area.location.user">If you have cashier account with Merchant please login</s:text>
                    </div>
                    <div class="centered">
                        <button class="btn" onclick="goToAction('/entry-view.htm')">
                            <s:text name="button.login">Log in</s:text>
                        </button>
                    </div>
                    <div class="centered">
                        <a class="scroll" href="#cashier_account">
                            <s:text name="page.login.cashier.dont.have.partner">Don’t have Cashier account?</s:text>
                        </a>
                    </div>
                </div>

                <div style="clear: both; height: 60px"></div>
                <div class="scrol_div" style="visibility: hidden"></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="container">
            <div class="row">
                <div class="centered create_loc_div">
                    <div style="clear: both;height:110px"></div>
                    <div class="centered create_location col-lg-5 col-md-5 col-sm-5 col-xs-12 ">
                        <div class="centered" id = "cashier_account">
                            <s:text name="page.login.cashier.if.you.dont.cashier">If you don’t have a Cashier account</s:text>
                        </div>
                        <div class="centered ">
                            <s:text name="page.login.cashier.if.you.dont.cashier.text">
                                You can create an account by choosing the company you are working for and its specific location and crate your account.
                                Company location management needs to approve your account and activate it for you to be able to login to your account
                            </s:text>
                        </div>
                        <div class="centered">
                            <button class="btn" onclick="goToAction('/cashier-signup.htm')">
                                <s:text name="page.login.cashier.create.account.button">create account</s:text>
                            </button>
                        </div>
                    </div>
                    <div style="clear: both;height:80px"></div>
                </div>

            </div>
        </div>
    </div>

    <div class="row">
        <div class="container">
            <div class="row">
                <div class="centered independ_driver_div">
                    <div style="clear: both;height:110px"></div>
                    <div class="centered independ_driver">
                        <div class="centered">
                            <s:text name="page.login.cashier.independent.cashier.merchant">independent cashier?</s:text>
                            <div class="line_independ centered"></div>
                        </div>
                        <div class="centered independent_cashier_text">
                            <s:text name="page.login.cashier.independent.cashier.text">
                                You need first to create your own company, than location and only than your driver account. In this case you will have all three accounts to manage your business.
                                Please refer to Partner Area for detail instructions how to request to create your company and after that how you can create your location and finally create your driver login.
                            </s:text>
                        </div>
                    </div>
                    <div style="clear: both;height:80px"></div>
                </div>

            </div>
        </div>
    </div>

</div>

