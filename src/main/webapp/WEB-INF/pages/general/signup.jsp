<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege" %>
<%@ page import="com.connectto.wallet.merchant.web.util.Constants" %>
<%@ page import="com.connectto.wallet.merchant.web.util.ScopeKeys" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<%=request.getContextPath()%>/css/cashier/cashier-sign-up.css" rel="stylesheet">

<script type="text/javascript" src="<%=request.getContextPath()%>/static/generated/lcp/Country.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/cashier-sign-up-controller.js"></script>

<%
    int privilege = (Integer) session.getAttribute(ScopeKeys.SIGN_UP_PRIVILEGE);
    session.removeAttribute(ScopeKeys.SIGN_UP_PRIVILEGE);
%>

<script type="text/javascript">

   var  company_data = [
        <s:iterator value="companies" var="company" >
       {
           company_name: '<s:property escapeJavaScript="true"  value="#company.name" />',
           company_branch: [
               <s:iterator value="#company.branches" var="branch" >
               {branch_name: '<s:property escapeJavaScript="true"  value="#branch.name" />'},
               </s:iterator>
           ]
       },
        </s:iterator>
    ];

</script>

<div ng-controller="signUpFormCtrl as signUp">
    <div class="sign_up">
        <form action="do-signup.htm" method="post" autocomplete="off">
            <input type="hidden" name="privilege" value='<s:property value="privilege"/>'/>
                <input type="password" style="display: none;"/>
                <div class="form-sign-up-message"></div>
                <div class="sign-up-form col-lg-4 col-md-6 col-sm-6 col-xs-8">
                    <div class="sign_up_form_title">
                        <s:text name="page.signup.button.Sign.Up">Sign Up</s:text>
                    </div>
                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <% if (Privilege.CRUD_COMPANY.getId() == privilege) {%>
                    <div class="istevens_parent_div" ng-controller ="campanyBranchCtrl">
                        <div class="isteven_company col-lg-8 col-md-10 col-sm-10 col-xs-10">
                            <div class="ist_mult_comp" on-item-click="companyBranch()"
                                 isteven-multi-select
                                 input-model="modernCompany"
                                 output-model="outputCompany"
                                 button-label="company "
                                 item-label="company "
                                 tick-property="ticked"
                                 selection-mode="single"
                                 helper-elements="filter"
                            >
                            </div>
                        </div>
                    </div>
                    <%} else if (Privilege.CRUD_BRANCH.getId() == privilege) {%>
                    <div class="istevens_parent_div" ng-controller ="campanyBranchCtrl">
                        <div class="isteven_company col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <div class="ist_mult_comp" on-item-click="companyBranch()"
                             isteven-multi-select
                             input-model="modernCompany"
                             output-model="outputCompany"
                             button-label="company "
                             item-label="company "
                             tick-property="ticked"
                             selection-mode="single"
                             helper-elements="filter"
                        >
                    </div>
                        </div>
                        <div class="isteven_branch col-lg-8 col-md-10 col-sm-10 col-xs-10">
                            <div class="ist_mult_branch"
                                 isteven-multi-select
                                 input-model="modernBranch"
                                 output-model="outputBranch"
                                 button-label="branch  "
                                 item-label="branch "
                                 tick-property="ticked"
                                 selection-mode="single"
                                 helper-elements="filter"
                            >
                            </div>
                        </div>
                    </div>
                    <%} else if (Privilege.CRUD_CASHIER.getId() == privilege) {%>
                    <div class="istevens_parent_div" ng-controller ="campanyBranchCtrl">
                        <div class="isteven_company col-lg-8 col-md-10 col-sm-10 col-xs-10">
                            <div class="ist_mult_comp" on-item-click="companyBranch()"
                                 isteven-multi-select
                                 input-model="modernCompany"
                                 output-model="outputCompany"
                                 button-label="company "
                                 item-label="company "
                                 tick-property="ticked"
                                 selection-mode="single"
                                 helper-elements="filter"
                            >
                            </div>
                        </div>

                        <div class="isteven_branch col-lg-8 col-md-10 col-sm-10 col-xs-10">
                            <div class="ist_mult_branch"
                                 isteven-multi-select
                                 input-model="modernBranch"
                                 output-model="outputBranch"
                                 button-label="branch  "
                                 item-label="branch "
                                 tick-property="ticked"
                                 selection-mode="single"
                                 helper-elements="filter"
                            >
                            </div>
                        </div>
                    </div>

                    <%} else if(Privilege.CASHIER.getId() == privilege){%>

                    <%} %>


                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="form-group-first-name col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <input type="text" class="form-control sign_up_form_first_name" name="first_name"
                               placeholder="<s:text name="page.signup.placeholder.First.Name">First Name</s:text>"
                               autocomplete="off"/>
                    </div>
                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="form-group-last-name col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <input type="text" class="form-control sign_up_form_last_name" name="last_name"
                               placeholder="<s:text name="page.signup.placeholder.Last.Name">Last Name</s:text>"
                               autocomplete="off"/>
                    </div>
                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="form-group-email col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <input type="text" class="form-control sign_up_form_email" name="email"
                               placeholder="<s:text name="page.signup.placeholder.email">E-mail</s:text>"
                               autocomplete="off"/>
                    </div>
                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="form-group-phone-number col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <div ng-controller="signUpFormPhoneCodeCtrl">

                            <div class="ist_mult" on-item-click="compPhoneCode()"
                                 isteven-multi-select
                                 input-model="modernBrowsers"
                                 output-model="outputBrowsers"
                                 button-label="icon code"
                                 item-label="code icon country"
                                 tick-property="ticked"
                                 selection-mode="single"
                                 helper-elements="none"
                            >
                            </div>
                            <input type="hidden" name="companyPhoneCode" ng-value="phoneCode">
                            <input type="text" class="form-control sign_up_form_phone_number" name="phone_number"
                                   placeholder="<s:text name="page.signup.placeholder.Phone.Number">Phone Number</s:text>"
                                   only-digits
                                   ng-model="profileFormValidate.company.phone" ng-pattern='/[0-9]/'
                                   autocomplete="off"/>
                            <div style="clear: both"></div>

                        </div>
                    </div>
                    <div class=" clear_both col-lg-2 col-md-1 col-sm-1 col-xs-1"></div>
                    <div class="form-group-password col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <img ng-click="signUp.passwordShowHideAndIconSrc()" class="show_hide_password" ng-cloak
                             ng-src="{{signUp.passwordShowHideIconSrc}}"
                             alt="<s:text name="page.signup.alt.Show.Hide.Password">Show/Hide Password</s:text>"/>
                        <input type="{{signUp.inputType}}" class="form-control sign_up_form_password"
                               name="sign_up_password"
                               placeholder="<s:text name="page.signup.placeholder.Password">Password</s:text>"
                               autocomplete="new-password"/>
                    </div>
                    <button type="submit" class="sign_up_button col-lg-8 col-md-10 col-sm-10 col-xs-10">
                        <s:text name="page.signup.button.Sign.Up">Sign Up</s:text>
                    </button>

                </div>
        </form>
    </div>
</div>