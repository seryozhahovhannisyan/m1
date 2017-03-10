<%@ page import="com.connectto.wallet.merchant.web.util.Initializer" %>
<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--main header start--%>

<link href="<%=request.getContextPath()%>/css/general/main.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/libs/css/isteven-multi-select.css" rel="stylesheet" type="text/css">
<link href="https://cdn.rawgit.com/summernote/summernote/v0.8.1/dist/summernote.css" type="text/css" rel="stylesheet">


<script type="text/javascript"
        src="<%=request.getContextPath()%>/libs/js/angular/angular-ui-router.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/summernote.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-summernote.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular.scroll.min.0.6.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-parallax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/generated/lcp/lcp/Country.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/home-controller.js"></script>



<div ng-controller="mainContentCtrl as mainContent">
    <div class="main_header">
        <div du-parallax y="mainContent.background " class=" merchant_main_header_background_img">

        </div>
        <%--<img src="<%=request.getContextPath()%>/img/main/merchant_main_header_background.png"--%>
        <%--alt="<s:text name="page.home.alt.Merchant.Header.Background">Merchant Header Background</s:text>"--%>
        <%--du-parallax y="mainContent.background" class=" merchant_main_header_background_img" />--%>

        <h1 class="main-heading-text">
            <s:text name="page.home.heading.text">wellcome to Merchant</s:text>
        </h1>
        <div class="main-under-heading-text">
            <s:text name="page.home.heading.content">The Merchant is the best money transfer application in the world :)</s:text>
        </div>
        <div class="main_header_send_request_form">
            <a href="#main_request_form_block_heading_text" class="main_header_go_to_send_request_form">
                <s:text name="page.home.send.request.form">Send request form</s:text>
            </a>
        </div>
        <div class="main_header_arrows_bottom">
            <a href="#main_request_form_block_heading_text" class="main_header_go_to_send_request_form">
                <img src="<%=request.getContextPath()%>/img/main/merchant_main_header_arrows_bottom.png"
                     alt="<s:text name="page.home.alt.Go.To.Bottom">Go To Bottom</s:text>"/>
            </a>
        </div>
    </div>
    <%--main header end--%>
    <%--main first block start--%>
    <div class="main_first_block">
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
        <div class="first_block_content_margin col-lg-4 col-md-4 col-sm-4 col-xs-12">
            <div class="first_block_title">
                <s:text name="page.main.first.block.title">first block title</s:text>
            </div>
            <div class="first_block_text">
                <s:text name="page.main.first.block.text">first block text</s:text>
            </div>
        </div>
    </div>
    <%--main first block end--%>
    <div class="clear_both"></div>
    <%--main second block start--%>
    <div class="main_second_block">
        <img src="<%=request.getContextPath()%>/img/main/merchant_main_white_blocks_background.png"
             alt="<s:text name="page.home.alt.First.Block.Background">First Block Background</s:text>"
             du-parallax y="mainContent.background" class="merchant_main_second_block_background_img"/>
        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
            <img class="img-responsive" src="<%=request.getContextPath()%>/img/main/merchant_main_phone_desktope.png"
                 alt="<s:text name="page.home.alt.Phone.Desktop">Phone Desktop</s:text>"/>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
            <div class="second_block_title">
                <s:text name="page.main.second.block.title">second block title</s:text>
            </div>
            <div class="second_block_text">
                <s:text name="page.main.second.block.text">second block text</s:text>
            </div>
        </div>
        <div class="go_to_top">
            <a href="#header_section">
                <img src="<%=request.getContextPath()%>/img/main/go_to_top.png"
                     alt="<s:text name="page.home.alt.Go.To.Top">Go To Top</s:text>"
                />
            </a>
        </div>
    </div>
    <%--main second block end--%>
    <div class="clear_both"></div>
    <%--main request(third) block start--%>
    <%--<div class="main_third_block" ng-controller="mainFormValidateCtrl as mainFormValidate">--%>
    <div class="main_third_block" ng-controller="profileFormValidateCtrl as profileFormValidate">
        <img src="<%=request.getContextPath()%>/img/main/merchant_main_white_blocks_background.png"
             alt="<s:text name="page.home.alt.Third.Block.Background">Third Block Background</s:text>"
             du-parallax y="mainContent.background" class=" merchant_main_third_block_background_img"/>
        <div class="col-lg-3 col-md-6 col-sm-8 col-xs-12 main_request_form_block">
            <div class="main_request_form_block_heading_text" id="main_request_form_block_heading_text">
                <h1><s:text name="page.main.third.block.heading.text">third block heading text</s:text></h1>
            </div>
            <div class="main-request-form-message">
                <s:actionmessage/>
                <s:actionerror/>
                <!-- message box -->
                <div class="box-info"
                     <s:if test="%{#session.info == null || #session.info == ''}">style="display: none"</s:if>>
                    <s:property value="#session.info"/>
                    <s:set var="info" scope="session" value=""/>
                </div>

                <div class="box-hint"
                     <s:if test="%{#session.message == null || #session.message == ''}">style="display: none"</s:if>>
                    <s:property value="#session.message"/>
                    <s:set var="message" scope="session" value=""/>
                </div>
            </div>

            <form id="rf" name="mainRequestForm" action="do-company-form-request.htm" method="post" ng-submit='profileFormValidate.submit(mainRequestForm)' novalidate="novalidate">
                <%--<form id="rf" name="mainRequestForm" action="do-company-form-request.htm"  ng-submit="mainRequestForm.$valid && submit(mainRequestForm)" novalidate>--%>
                <div class="main-request-form">
                    <div class="request-form-group">
                        <input autocomplete="off" type="text" class="request-form-control" name="companyName"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Name">Company Name</s:text>"
                               required="required"
                               ng-model="profileFormValidate.company.name"/>
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.companyName.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.companyName.$dirty">
                            <s:fielderror name="companyName" fieldName="companyName"/>
                        </div>
                    </div>
                    <div class="request-form-group">

                        <input autocomplete="off" type="text" class="request-form-control" name="companyAddress"
                               cssClass="custom" escape="true"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Address">Company Address</s:text>"
                               required="required"
                               ng-model="profileFormValidate.company.address"/>
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.companyAddress.$error.required">
                           <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.companyAddress.$dirty">
                            <s:fielderror name="companyAddress" fieldName="companyAddress"/>
                        </div>
                    </div>
                    <div class="request-form-group">
                        <input autocomplete="off" type="email" class="request-form-control" name="companyEmail"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Email">Company E-mail</s:text>"
                               required="required" ng-model="mainFormValidate.company.companyEmail"
                               ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>
                    </div>
                    <div role="alert" class="request_form_error_div">
                    <span class="request_form_error_span" ng-cloak
                          ng-show="mainRequestForm.$submitted && mainRequestForm.companyEmail.$error.required">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
                    <span class="request_form_error_span" ng-cloak
                          ng-show="mainRequestForm.companyEmail.$touched && mainRequestForm.companyEmail.$error.pattern">
                    <s:text name="page.main.third.block.message.email.invalid">Your email address is invalid!</s:text>
                    </span>
                        <div ng-hide="mainRequestForm.companyEmail.$dirty">
                            <s:fielderror name="companyEmail" fieldName="companyEmail"/>
                        </div>
                    </div>
                    <div class="request-form-group " ng-controller="requestFormPhoneCodeCtrl">
                        <div class="ist_mult" on-item-click="compPhoneCode()"
                             isteven-multi-select
                             input-model="modernBrowsers"
                             output-model="outputBrowsers"
                             button-label="icon code"
                             item-label="code icon country"
                             tick-property="ticked"
                             selection-mode="single"
                             helper-elements = "none"
                        >
                        </div>
                        <input type="hidden" name="companyPhoneCode" ng-value="phoneCode">
                        <input autocomplete="off" type="text" class="request-form-control request-form-phone-number"
                               name="companyPhone" only-digits
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>"
                               required="required"
                               ng-model="profileFormValidate.company.phone" ng-pattern= '/[0-9]/'>
                        <div style="clear: both"></div>
                    </div>

                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.companyPhone.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.companyPhone.$touched && mainRequestForm.companyPhone.$error.pattern">
                        <s:text name="page.main.third.block.message.only.numbers">Only Numbers Allowed!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.companyPhone.$dirty">
                            <s:fielderror name="companyPhone" fieldName="companyPhone"/>
                        </div>
                    </div>
                    <div ng-controller="requestFormNumberPickerCtrl as requestFormNumberPicker">
                        <div class="request-form-group">
                            <%--todo min 1 key pres implementation--%>
                            <input type="text" class="request-form-control request_form_number_input"
                                   name="countOfBranches" autocomplete="off"  ng-pattern= '/[0-9]/'
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Number.of.Branches">Number of Branches</s:text>"
                                   ng-model="requestFormNumberPicker.numberInput"
                                   only-digits
                                   ng-change="requestFormNumberPicker.checkMinOne(requestFormNumberPicker.numberInput)"
                                   />
                            <div class="request_form_number_picker_block">
                                <img class="number_picker_increment"
                                     alt="<s:text name="page.home.alt.company.request.form.Increment">Increment</s:text>"
                                     src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_top.png"
                                     ng-click="requestFormNumberPicker.requestFormNumberIncrement(requestFormNumberPicker.numberInput)"/>
                                <img class="number_picker_decrement"
                                     alt="<s:text name="page.home.alt.company.request.form.Decrement">Decrement</s:text>"
                                     src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_bottom.png"
                                     ng-click="requestFormNumberPicker.requestFormNumberDecrement(requestFormNumberPicker.numberInput)"/>
                            </div>
                        </div>
                        <div role="alert" class="request_form_error_div">
                            <span class="request_form_error_span" ng-cloak
                                  ng-show="mainRequestForm.$submitted && mainRequestForm.countOfBranches.$error.required">
                                <s:text name="page.main.third.block.message.required">Required!</s:text>
                            </span>
                            <span class="request_form_error_span" ng-cloak
                                  ng-show="mainRequestForm.countOfBranches.$touched && mainRequestForm.countOfBranches.$error.pattern">
                            <s:text name="page.main.third.block.message.only.numbers">Only Numbers Allowed!</s:text>
                            </span>
                            <div ng-hide="mainRequestForm.countOfBranches.$dirty">
                                <s:fielderror name="countOfBranches" fieldName="countOfBranches"/>
                            </div>
                        </div>
                    </div>
                    <div ng-controller="requestFormNumberPickerCtrl as requestFormNumberPicker">
                        <div class="request-form-group">

                            <input type="text" class="request-form-control request_form_number_input"
                                   required="required"  ng-pattern= '/[0-9]/'
                                   name="countOfWorkers" autocomplete="off"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Number.of.Employees">Number of Employees</s:text>"
                                   ng-model="requestFormNumberPicker.numberInput"
                                   only-digits
                                   ng-change="requestFormNumberPicker.checkMinOne(requestFormNumberPicker.numberInput)"
                                   />
                            <div class="request_form_number_picker_block">
                                <img class="number_picker_increment"
                                     alt="<s:text name="page.home.alt.company.request.form.Increment">Increment</s:text>"
                                     src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_top.png"
                                     ng-click="requestFormNumberPicker.requestFormNumberIncrement(requestFormNumberPicker.numberInput)"/>
                                <img class="number_picker_decrement"
                                     alt="<s:text name="page.home.alt.company.request.form.Decrement">Decrement</s:text>"
                                     src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_bottom.png"
                                     ng-click="requestFormNumberPicker.requestFormNumberDecrement(requestFormNumberPicker.numberInput)"/>
                            </div>
                        </div>
                        <div role="alert" class="request_form_error_div">
                            <span class="request_form_error_span" ng-cloak
                                  ng-show="mainRequestForm.$submitted && mainRequestForm.countOfWorkers.$error.required">
                                <s:text name="page.main.third.block.message.required">Required!</s:text>
                            </span>
                            <span class="request_form_error_span" ng-cloak
                            ng-show="mainRequestForm.countOfWorkers.$touched && mainRequestForm.countOfWorkers.$error.pattern">
                            <s:text name="page.main.third.block.message.only.numbers">Only Numbers Allowed!</s:text>
                            </span>
                            <div ng-hide="mainRequestForm.countOfWorkers.$dirty">
                                <s:fielderror name="countOfWorkers" fieldName="countOfWorkers"/>
                            </div>
                        </div>
                    </div>
                    <div class="request-form-group">
                        <input autocomplete="off" type="text" class="request-form-control" name="contactName"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Name">Contact Name</s:text>"
                               ng-model="profileFormValidate.company.contactName"
                               required="required"

                        />
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.contactName.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.contactName.$dirty">
                            <s:fielderror name="contactName" fieldName="contactName"/>
                        </div>
                    </div>
                    <div class="request-form-group">
                        <input autocomplete="off" type="text" class="request-form-control" name="contactLastName"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Surname">Contact Surname</s:text>"
                               required="required"
                               ng-model="profileFormValidate.company.contactSurname"/>
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.contactLastName.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.contactLastName.$dirty">
                            <s:fielderror name="contactLastName" fieldName="contactLastName"/>
                        </div>
                    </div>
                    <div class="request-form-group">
                        <input autocomplete="off" type="email" class="request-form-control" name="contactEmail"
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Email">Contact E-mail</s:text>"
                               ng-model="profileFormValidate.company.contactEmail"
                               required="required"
                               ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.contactEmail.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.contactEmail.$touched && mainRequestForm.contactEmail.$error.pattern">
                            <s:text name="page.main.third.block.message.email.invalid">Your email address is invalid!</s:text>
                        </span>
                        <div ng-hide="mainRequestForm.contactEmail.$dirty">
                            <s:fielderror name="contactEmail" fieldName="contactEmail"/>
                        </div>
                    </div>
                    <div class="request-form-group" ng-controller="requestFormPhoneCodeCtrl">
                        <div class="ist_mult" on-item-click="ctPhoneCode()"
                             isteven-multi-select
                             input-model="modernBrowsers"
                             output-model="outputBrowsers"
                             button-label="icon code"
                             item-label="code icon country"
                             tick-property="ticked"
                             selection-mode="single"
                             helper-elements = "none"
                        >
                        </div>
                        <input type="hidden" name="contactPhoneCode" ng-value="contactCode">
                        <input autocomplete="off" type="tel" class="request-form-control request-form-phone-number"
                               name="contactPhone" ng-pattern= '/[0-9]/'
                               placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Phone">Contact Phone</s:text>"
                               required="required" ng-model="profileFormValidate.company.contactPhone"
                               only-digits
                               />
                        <div style="clear: both"></div>
                    </div>
                    <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.$submitted && mainRequestForm.contactPhone.$error.required">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak
                              ng-show="mainRequestForm.contactPhone.$touched && mainRequestForm.contactPhone.$error.pattern">
                        <s:text name="page.main.third.block.message.only.numbers">Only Numbers Allowed!</s:text>
                        </span>

                        <div ng-hide="mainRequestForm.contactPhone.$dirty">
                            <s:fielderror name="contactPhone" fieldName="contactPhone"/>
                        </div>
                    </div>

                    <div ng-controller="CallbacksCtrl" class="main_text_editor">
                        <input type="hidden" ng-value ="summer_value" name="message">
                        <summernote name="message_summer" config="optionsSummernote"
                                    on-init="init()" on-enter="enter()" on-focus="focus(evt)"
                                    on-blur="blur(evt)" on-paste="paste(evt)" on-keyup="keyup(evt)"
                                    on-keydown="keydown(evt)"
                                    on-change="change(contents)"
                                    on-image-upload="imageUpload(files)"
                                    editable="editable" editor="editor">
                        </summernote>
                    </div>
                    <div class="g-recaptcha" data-theme="dark"
                         data-sitekey="<%=Initializer.getSetupInfo().recaptchaClientKey%>"></div>
                    <div class="request_form_send_request_form">

                        <button class="col-lg-10 col-md-10 col-sm-12 col-xs-12" type="submit"

                        <%--ng-disabled="mainRequestForm.$invalid"--%>
                        >


                            <s:text name="page.home.send.request.form">Send request form</s:text>
                        </button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<%--main second(third) block end--%>
<div class="clear_both"></div>
