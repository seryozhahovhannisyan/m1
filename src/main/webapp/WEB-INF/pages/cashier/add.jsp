<%@ page import = "com.connectto.wallet.merchant.web.util.Initializer" %><%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel = "stylesheet" href = "<%=request.getContextPath()%>/css/cashier/cashier_add.css">
<%--<link href="<%=request.getContextPath()%>/libs/css/isteven-multi-select.css" rel="stylesheet"/>--%>
<%--<link href="<%=request.getContextPath()%>/libs/css/datePicker/datePicker.css" rel="stylesheet"/>--%>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/datePicker/ui-bootstrap-tpls-1.3.3.js"></script>--%>
<script type = "text/javascript" src = "<%=request.getContextPath()%>/js/cashier/add_new_cashier.js"></script>

<script type="text/javascript">

    var captchaSiteKey = "<%=Initializer.getSetupInfo().recaptchaClientKey%>";
    var companyCountryId = '<s:property value="#session.cashier.company.country.id"/>';

</script>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/add-controller.js"></script>--%>


<script type = "text/ng-template" id = "add">


    <div class = "row" ng-controller = "listController">

        <div class = "col-md-12 col-sm-12 col-xs-12" ng-controller = "addBranchtCtrl">

            <div class = "close_popup" ng-if = "open_close_popup">
                <h2>Are you sure you want to close it?</h2>
                <button class = "btn btn-success" ng-click = "ctrl.cancel()">Yes</button>
                <button class = "btn btn-danger" ng-click = "ctrl.no_cancel()">NO</button>
            </div>

            <div class = "modal-header">
                <button type = "button" class = "close" ng-click = "ctrl.open_popup()">
                    <i class = "fa fa-times-circle" aria-hidden = "true"></i>
                </button>

                <button type = "button" ng-show = "ctrl.show_full_lg" class = "close"
                        ng-click = "ctrl.full_screen_large( )">
                    <i class = "fa fa-clone" aria-hidden = "true"></i>
                </button>

                <button type = "button" ng-show = " ctrl.show_full_sm" class = "close"
                        ng-click = "ctrl.full_screen_large( )">
                    <i class = "fa fa-square-o" aria-hidden = "true"></i>
                </button>

                <button type = "button" class = "close" ng-click = "ctrl.minimize()">
                    <i class = "fa fa-minus-square-o" aria-hidden = "true"></i>
                </button>

                <h3 class = "modal-title" id = "modal-title">
                    <s:text name = "page.profile.branch.add.new">Add new branch</s:text>
                </h3>
            </div>
            <div class = "modal-body">
                <div class = "add_modal_form_block" ng-controller = "branchIstevenCtrl">
                    <form method = "post" action = "cashier-add.htm" name = "branchActivationForm" novalidate ng-init="renderRecaptcha()" ng-submit="submity('branchActivationForm')">



                        <section class = "section1 animate-show-hide" ng-show = "section1">
                            <div class = "form_div_conteiner">
                                <div class = "company_details">
                                    <h1><s:text
                                            name = "page.home.placeholder.company.request.form.cashier.details"> Cashier details </s:text></h1>
                                </div>
                                <div>
                                    <input type = "text" class = "company_name_input" name = "nameCashier"
                                           ng-model = "cashier.nameCashier"
                                           placeholder = "<s:text name="page.home.placeholder.company.request.form.Name">Name</s:text>"
                                           required = "required"/>

                                    <span class = "input_error" ng-cloak
                                          ng-show = "cashier_name_show">
                    <s:text name = "page.main.third.block.message.required">Required!</s:text>
                    </span>
                                </div>
                                <div>
                                    <input type = "text" class = "company_addres_input" name = "surnameCashier"
                                           ng-model = "cashier.surnameCashier"
                                           placeholder = "<s:text name="page.home.surname">Surname</s:text>"
                                           required = "required"/>

                                    <span class = "input_error" ng-cloak
                                          ng-show = "cashier_surname_show">
                    <s:text name = "page.main.third.block.message.required">Required!</s:text>
                    </span>
                                </div>

                                <div>
                                    <input type = "email" class = "company_email_input" name = "emailCashier"
                                           ng-model = "cashier.emailCashier"
                                           placeholder = "<s:text name="page.home.placeholder.company.request.form.Email">E-mail</s:text>"
                                           required = "required" ng-pattern = "/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>

                                    <span class = "input_error" ng-cloak
                                          ng-show = "cashier_e_mail_show">
                    <s:text name = "page.main.third.block.message.required">Required!</s:text>
                    </span>

                                    <span class = "input_error" ng-cloak
                                          ng-show = "branchActivationForm.emailCashier.$touched && branchActivationForm.emailCashier.$error.pattern">
                    <s:text name = "page.main.third.block.message.email.invalid">Your email address is invalid!</s:text>
                    </span>
                                </div>

                                <div class = "ist_mult_parent">
                                    <isteven-multi-select class = "ist_mult ist_mult_role_phone_code"
                                                          data-casheirPhoneCode = {{cashierPphoneCode}}
                                                          on-item-click = "cashierPhoneCodeSet()"
                                                          input-model = "cashierPhoneCodes"
                                                          output-model = "cashierPhoneCodesOut"
                                                          button-label = "icon code"
                                                          item-label = "code icon country"
                                                          tick-property = "ticked"
                                                          selection-mode = "single"
                                                          helper-elements = "none"
                                    >
                                    </isteven-multi-select>

                                    <input type="hidden" name="phoneCodeCashier" ng-value="cashierPphoneCode">

                                    <input type = "text" class = "company_phone_input" name = "phoneCashier"
                                           ng-model = "cashier.phoneCashier"
                                           placeholder = "<s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>"
                                           required = "required" only-digits/>

                                    <span class = "input_error" ng-cloak
                                          ng-show = "cashier_phone_number_show">
                    <s:text name = "page.main.third.block.message.required">Required!</s:text>
                    </span>
                                </div>

                                <div>


                                    <button type="button" ng-click = "uiSerfCashier()" class = "btn btn-block btn-info"
                                            ng-disabled="branchActivationForm.emailCashier.$error.pattern">
                                        <s:text name = "page.home.placeholder.company.request.form.next">Next</s:text>
                                        &nbsp
                                        <i class = "fa fa-hand-o-right" aria-hidden = "true"></i>
                                    </button>

                                </div>
                            </div>
                        </section>

                        <section class = "section2 animate-show-hide" ng-show = "section2">
                            <div>
                                <div class = "company_upload_parent">
                                    <h1><s:text
                                            name = "page.home.company.request.upload.cashier.photo">Upload Cashier Photo</s:text></h1>
                                    <div class = "company_upload_inpar">

                                        <label for = "cashier_logo" class = "first_label"
                                               style = "background-image: url({{cashier_img}})">
                                            <input type = "file" data-file = "cashier" data-img_url= "cashier_img"   data-scope_name= "cashier_scope" id = "cashier_logo"
                                                   class = "company_files_input"
                                                   accept = "image/*"
                                                   file-model = "fileInfo"/>
                                        </label>
                                        <label ng-show = "drag_true" for = "cashier_logo"
                                               class = "second_label"></label>
                                        <div class = "remove" ng-show = "remove_true"></div>
                                    </div>
                                    <div>
                                        <h3>{{cashier_scope.name}}</h3>
                                        <h3 ng-show = "upload_success" class = "upload_success"><s:text
                                                name = "page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                                        <h3 ng-show = "upload_error" class = "upload_error"><s:text
                                                name = "page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                                        <h3 ng-show = "upload_remove_succsses" class = "upload_remove_succsses"><s:text
                                                name = "page.home.company.request.upload.remove.success">Your file removed successfully</s:text></h3>
                                    </div>
                                </div>


                            </div>

                            <button type="button" class = "btn btn-block btn-info" ng-click = "backCashier()">
                                <s:text name="page.home.placeholder.company.request.form.back">Back</s:text>
                                &nbsp
                                <i class="fa fa-hand-o-left" aria-hidden="true"></i>
                            </button>
                            <button  type="button" class = "btn btn-block btn-info  " ng-click = "toRole()">
                                <s:text name = "page.home.placeholder.company.request.form.next">Next</s:text>
                                &nbsp
                                <i class = "fa fa-hand-o-right" aria-hidden = "true"></i>
                            </button>
                        </section>

                        <section class = "section3   animate-show-hide" ng-show="section3" >
                            <div class = "form_div_conteiner">



                                <div class = "company_details">
                                    <h1><s:text
                                            name = "page.home.placeholder.company.request.form.role.details"> Role details </s:text></h1>
                                </div>

                                <div style = "height: 40px" ng-show="roleIstevenCount">
                                    <div class = "ist_mult_parent">
                                        <isteven-multi-select class = "ist_mult ist_mult_role"
                                        <%--data-currency={{roleCurrencyTyRate}}--%>
                                                              on-item-click = "curRole()"
                                                              input-model = "roleAllowedList"
                                                              output-model = "roleAllowedListOut"
                                                              button-label = "name"
                                                              item-label = " name"
                                                              tick-property = "ticked"
                                                              helper-elements = "filter"
                                                              selection-mode = "single"

                                        >
                                        </isteven-multi-select>

                                        <input type="hidden" name="roleId" ng-value="roleAllowedListback"/>

                                    </div>
                                </div>
                                <div class = "setup_info_div">
                                    <div class = "info_span_setup"><h3>
                                        <s:text name = "page.profile.placeholder.role.name"> Roll name </s:text>
                                    </h3></div>
                                    <em> <span class = "info_span_name info_din_span"> {{roleName}}</span></em>
                                </div>

                                <div class = "setup_info_div">
                                    <div class = "info_span_setup"><h3>
                                        <s:text name = "page.profile.placeholder.role.description"> Roll description </s:text>
                                    </h3></div>
                                    <em> <span class = "info_span_name info_din_span"> {{description}} </span></em>
                                </div>

                                <div class = "setup_info_div">
                                    <div class = "info_span_setup"><h3>
                                        <s:text name = "page.profile.placeholder.role.transaction.Min.Role"> Roll min transaction </s:text>
                                    </h3></div>
                                    <em> <span class = "info_span_name info_din_span"> {{transactionMin}} </span></em>
                                </div>

                                <div class = "setup_info_div">
                                    <div class = "info_span_setup"><h3>
                                        <s:text name = "page.profile.placeholder.role.transaction.Max.Role"> Roll max transaction </s:text>
                                    </h3></div>
                                    <em> <span class = "info_span_name info_din_span"> {{transactionMax}}</span></em>
                                </div>


                                <div style = "height: 40px" ng-show="availableRateEmpty">
                                    <div class = "ist_mult_parent">
                                        <isteven-multi-select class = "ist_mult list_mult_currency"
                                        <%--data-currency={{roleCurrencyTyRate}}--%>
                                                              on-item-click = "curType()"
                                                              input-model = "roleCurrencyTyRateValues"
                                                              output-model = "roleCurrencyTypeOut"
                                                              button-label = "code"
                                                              item-label = " name"
                                                              tick-property = "ticked"
                                                              selection-mode = "multiple"
                                                              helper-elements = "filter"
                                                              translation = "currency_type"
                                        >
                                        </isteven-multi-select>

                                        <%--<input type="hidden" name="availableRateValuesRole" ng-value="roleCurrencyTyRate"/>--%>

                                    </div>
                                </div>

                                <span class = "input_error" ng-show = "setup_currencyType">
                                    <s:text name = "page.main.third.block.message.required">Required!</s:text>
                                </span>


                                <div>

                                    <button  type="button" class = "btn btn-block btn-info"
                                             ng-click = "backCashierUpload()">
                                        <s:text name="page.home.placeholder.company.request.form.back">Back</s:text>
                                        &nbsp
                                        <i class="fa fa-hand-o-left" aria-hidden="true"></i>
                                    </button>


                                    <button type="button" ng-click = "uiSerfRole()" class = "btn btn-block btn-info">
                                        <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
                                        &nbsp
                                        <i class="fa fa-hand-o-right" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </div>
                        </section>


                        <section class="section4    animate-show-hide" ng-show="section4" >

                            <div class="form_div_conteiner">
                                <div class="company_details">
                                    <h1><s:text name="label.verification.text">Verification</s:text></h1>
                                </div>

                                <div class="ng_div">
                                    <input type="password" class="company_name_input" ng-cut="$event.preventDefault()"
                                           ng-copy="$event.preventDefault()" ng-paste="$event.preventDefault()"
                                           ng-keyup="verificat_fields()" name="passwordCashier" ng-model="verification.passwordCashier"
                                           ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%\^\&*\)\(+=._-]{6,}$/"
                                           placeholder="<s:text name="label.password">Password</s:text>"
                                           required="required"/>

                                    <span class="input_error" ng-cloak
                                          ng-show="branchActivationForm.passwordCashier.$error.pattern">
                    <s:text name="pages.registration.password.lengt.6">Your password must be at least 6 characters long: one number, one lowercase and one uppercase letter.</s:text>
                    </span>
                                </div>
                                <div class="ng_div">
                                    <input type="password" class="company_addres_input" ng-cut="$event.preventDefault()"
                                           ng-copy="$event.preventDefault()" ng-paste="$event.preventDefault()"
                                           ng-keyup="verificat_fields()"name="passwordCashierRepeat"
                                           ng-model="verification.passwordCashierRepeat"
                                           placeholder="<s:text name="label.password.pereat">Repeat password</s:text>"
                                           required="required"/>

                                    <span class="input_error" ng-cloak
                                          ng-show="branchActivationForm.passwordCashierRepeat.$error.required && branchActivationForm.passwordCashierRepeat.$touched">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                                    <span class="input_error" ng-cloak
                                          ng-show="password_match">
                    <s:text name="pages.registration.password.type.match">Your password must be the same in both fields!</s:text>
                    </span>

                                </div>





                                <div id="recaptcha" class="g-recaptcha ng_div"> </div>
                                <span class="input_error" ng-cloak
                                      ng-show="captcha_invalid">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>


                                <div>
                                    <button type="button"   class="btn btn-block btn-info" ng-click ="toRole()">
                                        <s:text name="page.home.placeholder.company.request.form.back">Back</s:text>
                                        &nbsp
                                        <i class="fa fa-hand-o-left" aria-hidden="true"></i>
                                    </button>
                                    <button   type="submit"  class="btn btn-block btn-info" >
                                        <s:text name="button.submit">Submit</s:text>
                                    </button>
                                    <%--<div ng-show="show_form" class="main_form">--%>
                                    <%--<form action="activate-company.htm" method="post">--%>


                                    <%--<input type="text" ng-repeat="(key, value) in inputs"--%>
                                    <%--name={{key}} ng-model=value--%>
                                    <%--/>--%>

                                    <%--<button type="submit" class="btn btn-block  btn-info">--%>
                                    <%--submit--%>
                                    <%--</button>--%>
                                    <%--</form>--%>
                                    <%--</div>--%>

                                </div>

                            </div>
                        </section>
                    </form>
                </div>
            </div>

            <div class = "modal-footer">
                <div>


                </div>
            </div>
        </div>
    </div>


</script>
