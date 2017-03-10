<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="<%=request.getContextPath()%>/css/general/area-company.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular.scroll.min.0.6.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-parallax.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/area-company-controller.js"></script>


<div ng-controller="companyAreaContentCtrl as companyAreaContent">

    <div class="area_company_header">
        <img src="<%=request.getContextPath()%>/img/main/merchant_main_header_background.png" alt="<s:text name="page.home.alt.Merchant.Header.Background">Merchant Header Background</s:text>" du-parallax y="companyAreaContent.background" class="merchant_main_header_background_img" />

        <div class="container">
            <div class="row">
                <div class="centered col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="img_logo_div centered">
                        <img src="<%=request.getContextPath()%>/img/area-company/company-area-logo-center.png"
                             alt="logo"/>
                    </div>

                    <div class="row">
                        <div class="centered  text_company_area col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <s:text name="page.area.header.Company.Area">Company Area</s:text>
                        </div>
                        <div class="centered  new_to_wallet_merchant  col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <s:text name="page.area.company.New.to.merchant">New to Merchant? Please Read Instruction</s:text>
                        </div>
                    </div>
                </div>
            </div>
            <div style="clear: both; height: 60px"></div>
            <div class="row">

                <div class="right col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="centered">
                        <s:text name="page.area.company.admin.user">If you have a Company Administrator account</s:text>
                    </div>
                    <div class="centered">
                        <button class="btn" onclick="goToAction('entry-view.htm')">
                            <s:text name="button.login">Log in</s:text>
                        </button>
                    </div>

                    <div class="centered">
                        <a class="scroll" href="#get_str" >
                            <s:text name="page.area.company.dont.have.Administration">Don’t have Company administrator account?</s:text>
                        </a>
                    </div>
                </div>

                <div class="left col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="centered">
                        <s:text name="page.area.company.branch.user">If you have a Company branch administrator account</s:text>
                    </div>
                    <div class="centered">
                        <button class="btn" onclick="goToAction('entry-view.htm')">
                            <s:text name="button.login">Log in</s:text>
                        </button>
                    </div>
                    <div class="centered">
                        <a class="scroll1" href="#scr_btm">
                            <s:text name="page.area.company.dont.have.branch">Don’t have Company branch administrator account?</s:text>
                        </a>
                    </div>
                </div>

                <div style="clear: both;height:216px"></div>
                <div class="centered text-center work_vshoo_div">
                    <s:text name="page.area.company.work.with.merchant">work with ConnectToMerchant</s:text>
                    <div class="line_work centered"></div>
                </div>


            </div>

        </div>
    </div>
    <div class="row">
        <div class="container">
            <div class="row">
                <div class="centered work_vshoo_text_div">
                    <div style="clear: both;height:50px"></div>
                    <div class="centered">
                        <s:text name="page.area.company.work.with.merchant.text">
                            Welcome to ConnectToMerchant. With the help of this site you can easily turn cash into online money and online money into cash.
                        </s:text>
                    </div>

                    <div style="clear: both;height:80px"></div>
                </div>
                <div  id="get_str" class="scrol_div" style="visibility: hidden"></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="container">
            <div class="row">
                <div class="centered get_started_div">
                    <div style="clear: both;height:110px"></div>
                    <div class="centered get_started  " >
                        <div class="centered">
                            <s:text name="page.area.company.get.started">get started</s:text>
                            <div class="line_get centered"></div>
                        </div>
                    </div>
                    <div style="clear: both;height:50px"></div>

                    <%--first--%>
                    <div class="centered get_started1 col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <div>
                            .1
                        </div>
                        <div>
                            <a href="start.htm#main_request_form_block_heading_text">
                                <s:text name="page.area.company.request.form.company">Company Request Form</s:text>
                            </a>
                        </div>

                        <div style="clear: both;height:50px"></div>
                        <div>
                            <s:text name="page.area.company.request.form.first.text">
                                In order to become a ConnectToMerchant service provider and benefit from the services that it offers, please fill the following list and press the button send the request form.
                            </s:text>
                        </div>
                        <div style="clear: both;"></div>
                    </div>

                    <%--second--%>
                    <div style="clear: both;height:50px"></div>
                    <div class="centered get_started1 col-lg-8 col-md-8 col-sm-8  col-xs-12">
                        <div>
                            .2
                        </div>
                        <div>
                            <a href="company-signup.htm">
                                <s:text name="page.area.company.create.company.account">Create a Partner Company Administrator Account</s:text>
                            </a>
                        </div>

                        <div style="clear: both;height:50px"></div>
                        <div>
                            <s:text name="page.area.company.request.form.second.text">
                                Once your company is created, you need to create your account.  Within a short period of time ConnectToMerchant administration will approve your new account and you will be able to log in.
                            </s:text>
                        </div>
                        <div style="clear: both;"></div>
                    </div>


                    <%--third--%>
                    <div style="clear: both;height:50px"></div>
                    <div class="centered get_started1 col-lg-8 col-md-8 col-sm-8  col-xs-12">
                        <div>
                            .3
                        </div>
                        <div>
                            <a href="branch-signup.htm">
                                <s:text name="page.area.company.create.partner.branch.account">Create a Partner branch Administrator Account</s:text>
                            </a>
                        </div>

                        <div style="clear: both;height:50px"></div>
                        <div>
                            <s:text name="page.area.company.request.form.third.text">
                                Once ConnectToMerchant has activated your account, and you are able to log in, you will be able to create your company location.  Every company has to have at least one and can have as many locations as it is necessary for you to manage your business.  Each company location has its cashier who is in charge of the monetary transactions within the company location.
                            </s:text>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div style="clear: both;height:80px"></div>
                </div>

                <div class="scrol_div1" style="visibility: hidden"></div>
            </div>
        </div>
    </div>


    <div class="row" id = "scr_btm">
        <div class="container">
            <div class="row">
                <div class="centered create_loc_div">
                    <div style="clear: both;height:110px"></div>
                    <div class="centered create_location col-lg-8 col-md-8 col-sm-8 col-xs-12 ">
                        <div class="centered">
                            <s:text name="pages.login.partner.if.you.dont.branch">If you do not have Partner Company Branch Administrator Account</s:text>
                        </div>
                        <div class="centered">
                            <s:text name="pages.login.partner.if.you.dont.branch.text">
                                You can create one by choosing from the drop down list the company your location belongs to, than from the listed locations,
                                you can choose your location and create an account to access your location portal. Once you crated the location account,
                                it has to be approved by the company management for you to have access.
                            </s:text>
                        </div>
                        <div class="centered">
                            <button class="btn" onclick="goToAction('/branch-signup.htm')">
                                <s:text name="button.create.account">create account</s:text>
                            </button>
                        </div>
                    </div>
                    <div style="clear: both;height:80px"></div>
                </div>

            </div>
        </div>

    </div>

</div>

