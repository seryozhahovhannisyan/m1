<%@ page import="com.connectto.wallet.merchant.web.util.Initializer" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/general/company_request_activation.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-ui-router.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-animate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/generated/lcp/Country.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/generated/lcp/CountryRegion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/generated/lcp/CurrencyType.js"></script>
<script type="text/javascript">
    var availableRate = '<s:property value="availableRateValuesCompany"/>';
    availableRate = availableRate.split(",")
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/general/activate-request-form.js"></script>

<div class="content" ng-controller="companyReqCtrl">

    <div style="clear: both;height: 120px;"></div>

    <div class="container" ui-view>


    </div>

    <script type="text/ng-template" id="form">

        <form id="signup-form" name="comanyActivationForm" class="activation_form"
              novalidate="novalidate"
              ng-submit="submit(comanyActivationForm)">
            <div ng-controller="istevenCtrl" id="form-views" ui-view>

            </div>

        </form>
    </script>


    <script type="text/ng-template" id="companyInfo">
        <div class="form_div_conteiner">
            <div class="company_details">

                <h1><s:text name="page.home.placeholder.company.request.form.company.info"> Company info </s:text></h1>
            </div>
            <div class="contact_info"><h2><s:text
                    name="page.home.placeholder.company.info.according.request">Yours contact info according your request</s:text></h2>
            </div>
            <div class="company_info_name_div">
                <span class="info_span"><h3>
                    <s:text name="page.profile.placeholder.Company.Name">Compnay Name</s:text>
                    </h3></span>
                <em><span class="info_span_name info_din_span"> <s:property value="nameCompany"/> </span></em>
            </div>

            <div class="company_info_name_div">
                <span class="info_span"><h3>
                <s:text name="page.profile.placeholder.Company.Address">Company Address</s:text>
                </h3></span>
                <em><span class="info_span_address info_din_span"> <s:property value="addressCompany"/> </span></em>
            </div>

            <div class="company_info_name_div">
                <span class="info_span"><h3>
                <s:text name="placeholder.company.request.form.Company.Email">Company E-mail</s:text>
                </h3></span>
                <em><span class="info_span_email info_din_span"> <s:property value="emailCompany"/> </span></em>
            </div>

            <div class="company_info_name_div">
                <span class="info_span"><h3>
                <s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>
                </h3></span>
                <em><span class="info_span_email info_din_span"> <s:property value="phoneCodeCompany"/>  <s:property
                        value="phoneCompany"/> </span></em>
            </div>
            <br/>
            <div class="logos_div_parent">
                <div class="logo_img_connectto">
                    <img ng-src="<%=request.getContextPath()%>/img/general/logo_connectto.png" alt="avatar"/>
                </div>
                <div class="logo_img_div">
                    <img ng-src="<%=request.getContextPath()%>/img/general/logo_hollor.png" alt="avatar"/>
                </div>
                <div class="logo_img_div">
                    <img ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt="avatar"/>
                </div>
            </div>

            <br/>
            <div>
                <p class="company_info_fill_text"> *hargeli gorcynker, menq qnarkel enq dzer uxarkac haycadimumy, xndrum
                    enq sexmeq next button@ ev aveli manramasn lracnel anhrajesht tvyalner@ </p>
            </div>
            <div>
                <a ui-sref="form.company" class="btn btn-block btn-info  ">
                    <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
                    &nbsp
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>
                </a>
            </div>
        </div>
    </script>


    <script type="text/ng-template" id="company">
        <div class="form_div_conteiner">

            <div class="company_details">

                <h1><s:text
                        name="page.home.placeholder.company.request.form.company.details"> Company details </s:text></h1>
            </div>

            <div>
                <input type="text" class="company_city_input" name="cityCompany" ng-model="company.cityCompany"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.city">City</s:text>"
                       required="required"/>
                <span class="input_error"
                      ng-show="company_city_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_street_input" name="streetCompany" ng-model="company.streetCompany"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.street">Street</s:text>"
                       required="required"/>
                <span class="input_error"
                      ng-show="company_street_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_zipcode_input" name="zipCompany" ng-model="company.zipCompany"
                       placeholder="Zip Code"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.zsip.code">Zip Code</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="company_zip_code_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            </div>


            <div class="ist_mult_parent">
                <div class="ist_mult ist_mult_comp_policiy_phone" data-companyPolicyCode={{companyPolicyCode}}
                     on-item-click="compPolicCode()"
                     isteven-multi-select
                     input-model="companyPolicyyCodes"
                     output-model="$parent.companyPolicyPhoneOut"
                     button-label="icon code"
                     item-label="code icon country"
                     tick-property="ticked"
                     selection-mode="single"
                     helper-elements="none"
                >
                </div>

                <%--<input type="hidden" name="policyPhoneCodeCompany" ng-value="companyPolicyCode"/>--%>
                <input type="text" class="company_policphone_input" name="policyPhoneCompany"
                       ng-model="company.policyPhoneCompany"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Policy.Number">Policy phone number</s:text>"
                       required="required" only-digits/>
                <span class="input_error" ng-cloak
                      ng-show="company_policy_phone_number_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div class="isteven_parent_count_reg">
                <div style="height:40px">
                    <div class="ist_mult_parent">
                        <div class="ist_mult ist_mult_comp " data-country={{companyCountry}}
                             on-item-click="compCountry()"
                             isteven-multi-select
                             input-model="companyCountryArray"
                             output-model="$parent.companyCountryOut"
                             button-label="icon name"
                             item-label=" icon name"
                             tick-property="ticked"
                             selection-mode="single"
                             helper-elements="filter"
                             translation="select_country"
                        >

                        </div>
                        <%--<input type="hidden" name="countryCompany" ng-value="companyCountry"/>--%>
                    </div>
                </div>
                <span class="input_error" ng-cloak
                      ng-show="company_country_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

                <div style="height:40px">
                    <div class="ist_mult_parent">
                        <div class="ist_mult list_mult_region " data-region={{companyRegion}}
                             on-item-click="compRegion()"
                             isteven-multi-select
                             input-model="companyRegionArray"
                             output-model="$parent.companyRegionOut"
                             button-label="icon name"
                             item-label=" icon name"
                             tick-property="ticked"
                             selection-mode="single"
                             helper-elements="filter"
                             translation="select_region"
                        >
                        </div>

                        <%--<input type="hidden" name="countryRegionCompany" ng-value="companyRegion"/>--%>
                    </div>


                </div>
                <span class="input_error" ng-cloak
                      ng-show="company_state_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>

            <div>
                <a ui-sref="form.companyInfo" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>

                <a ng-click="uiSerfCompanyUplod()" class="btn btn-block btn-info  ">
                    <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
                    &nbsp
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>
                </a>

            </div>
        </div>

    </script>

    <script type="text/ng-template" id="companyUpload">
        <div>
            <div class="company_upload_parent">
                <h1><s:text name="page.home.company.request.upload.company.logo">Upload Company Logo</s:text></h1>
                <div class="company_upload_inpar">

                    <label for="company_logo" class="first_label" style="background-image: url({{up_img}})">
                        <input type="file" data-file="company" id="company_logo" class="company_files_input"
                               accept="image/*"
                               file-model="fileInfo"/>
                    </label>
                    <label ng-show="drag_true" for="company_logo" class="second_label"></label>
                    <div class="remove" ng-show="remove_true"></div>
                </div>
                <div>
                    <h3>{{fileInfo.name}}</h3>
                    <h3 ng-show="upload_success" class="upload_success"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_error" class="upload_error"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_remove_succsses" class="upload_remove_succsses"><s:text
                            name="page.home.company.request.upload.remove.success">Your file removed successfully</s:text></h3>
                </div>
            </div>


        </div>

        <a ui-sref="form.setup" class="btn btn-block btn-info  ">
            <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
            &nbsp
            <i class="fa fa-hand-o-right" aria-hidden="true"></i>
        </a>
    </script>

    <script type="text/ng-template" id="setup">
        <div class="form_div_conteiner">


            <div class="company_details">
                <h1><s:text
                        name="page.home.placeholder.company.request.form.setup.details">Company cashbox details </s:text></h1>
            </div>
            <%--<div style="height: 40px">
                <div class="ist_mult_parent">
                    <div class="ist_mult list_mult_currency" data-currency={{setupCurrencyType}}
                         on-item-click="curType()"
                         isteven-multi-select
                         input-model="currencyType"
                         output-model="$parent.currencyTypeOut"
                         button-label="code"
                         item-label=" name"
                         tick-property="ticked"
                         selection-mode="single"
                         helper-elements="filter"
                         translation="currency_type"
                    >
                    </div>

                    &lt;%&ndash;<input type="hidden" name="currencyTypeMerchantSetup" ng-value="setupCurrencyType"/>&ndash;%&gt;

                </div>
            </div>--%>
            <%--<span class="input_error"--%>
            <%--ng-show="setup_currencyType">--%>
            <%--<s:text name="page.main.third.block.message.required">Required!</s:text>--%>
            <%--</span>--%>

            <div>
                <input type="text" class="company_name_input" name="depositFeePercentMerchantSetup"
                       ng-blur="percent(setup.depositFeePercentMerchantSetup,'setup_depFeePercent_size')"
                       ng-model="setup.depositFeePercentMerchantSetup"
                       value='<s:property value="depositFeePercentMerchantSetup"/>'
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.deposit.fee.percent">Deposit Fee Percent</s:text>"
                       required="required" only-digits/>

                <span class="input_error"
                      ng-show="setup_depFeePercent">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                <span class="input_error"
                      ng-show="setup_depFeePercent_size">
                            <s:text name="page.activation.company.percent.size">The percent must be between 0-100</s:text>
                        </span>
            </div>

            <div>
                <input type="text" class="company_addres_input" name="depositMinFeeMerchantSetup"
                       ng-model="setup.depositMinFeeMerchantSetup"
                       value='<s:property value="depositMinFeeMerchantSetup"/>'
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.deposit.min.fee">Deposit Min Fee</s:text>"
                       required="required" only-digits/>
                <span class="input_error"
                      ng-show="setup_depMinFee">
                             <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_city_input" name="depositMaxFeeMerchantSetup"
                       ng-model="setup.depositMaxFeeMerchantSetup"
                       value='<s:property value="depositMaxFeeMerchantSetup"/>'
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.deposit.max.fee">Deposit Max Fee</s:text>"
                       required="required" only-digits/>

                <span ng-class="{'input_error_green' : !setup.depositMaxFeeMerchantSetup , 'input_error_red' : setup.depositMinFeeMerchantSetup > setup.depositMaxFeeMerchantSetup}"
                      ng-show="setup.depositMinFeeMerchantSetup > setup.depositMaxFeeMerchantSetup || depMaxError">
                            <s:text name="page.home.min.fee.max.fee.error">Deposit min fee must be less than deposit max fee</s:text>
                        </span>

                <span class="input_error"
                      ng-show=" setup_depMaxFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_street_input" name="withdrawFeePercentMerchantSetup"
                       ng-blur="percent(setup.withdrawFeePercentMerchantSetup,'setup_withFeePercent_size')"
                       ng-model="setup.withdrawFeePercentMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.withdraw.fee.percent">Withdraw Fee percent</s:text>"
                       required="required" only-digits/>
                <span class="input_error"
                      ng-show="setup_withFeePercent">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                <span class="input_error"
                      ng-show="setup_withFeePercent_size">
                            <s:text name="page.activation.company.percent.size">The percent must be between 0-100</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_zipcode_input" name="withdrawMinFeeMerchantSetup"
                       ng-model="setup.withdrawMinFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.withdraw.min.fee"> Withdraw Min Fee</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="setup_withMinFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            </div>
            <div>
                <input type="text" class="company_email_input" name="withdrawMaxFeeMerchantSetup"
                       ng-model="setup.withdrawMaxFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.withdraw.max.fee">Withdraw Max Fee</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="setup_withMaxFee">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                <span ng-class="{'input_error_green' : !setup.withdrawMaxFeeMerchantSetup , 'input_error_red' : setup.withdrawMinFeeMerchantSetup > setup.withdrawMaxFeeMerchantSetup}"
                      ng-show="setup.withdrawMinFeeMerchantSetup > setup.withdrawMaxFeeMerchantSetup || withMaxError">
                            <s:text name="page.home.min.fee.max.fee.error">Deposit min fee must be less than deposit max fee</s:text>
                        </span>

            </div>

            <div>
                <input type="text" class="company_country_input" name="exchangeDepositFeePercentMerchantSetup"
                       ng-blur="percent(setup.withdrawFeePercentMerchantSetup,'setup_exchDepFeePercent_size')"
                       ng-model="setup.exchangeDepositFeePercentMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.deposit.fee.percent">Exchange Deposit Fee Percent</s:text>"
                       required="required" only-digits/>
                <span class="input_error" ng-cloak
                      ng-show="setup_exchDepFeePercent">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                <span class="input_error"
                      ng-show="setup_exchDepFeePercent_size">
                            <s:text name="page.activation.company.percent.size">The percent must be between 0-100</s:text>
                        </span>
            </div>

            <div>
                <input type="text" class="company_state_input" name="exchangeDepositMinFeeMerchantSetup"
                       ng-model="setup.exchangeDepositMinFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.deposit.min.fee">Exchange Deposit Min Fee</s:text>"
                       required="required" only-digits/>
                <span class="input_error" ng-cloak
                      ng-show="setup_exchDepMinFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_state_input" name="exchangeDepositMaxFeeMerchantSetup"
                       ng-model="setup.exchangeDepositMaxFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.deposit.max.fee">Exchange Deposit Max Fee</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="setup_exchDepMaxFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

                <span ng-class="{'input_error_green' : !setup.exchangeDepositMaxFeeMerchantSetup , 'input_error_red' : setup.exchangeDepositMinFeeMerchantSetup > setup.exchangeDepositMaxFeeMerchantSetup}"
                      ng-show="setup.exchangeDepositMinFeeMerchantSetup > setup.exchangeDepositMaxFeeMerchantSetup || exchDepMaxError">
                            <s:text name="page.home.min.fee.max.fee.error">Deposit min fee must be less than deposit max fee</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_state_input" name="exchangeWithdrawFeePercentMerchantSetup"
                       ng-blur="percent(setup.withdrawFeePercentMerchantSetup,'setup_exchWithFeePercent_size')"
                       ng-model="setup.exchangeWithdrawFeePercentMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.withdraw.fee.percent"> Exchange Withdraw Fee Percent</s:text>"
                       required="required" only-digits/>
                <span class="input_error" ng-cloak
                      ng-show="setup_exchWithFeePercent">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
                <span class="input_error"
                      ng-show="setup_exchWithFeePercent_size">
                            <s:text name="page.activation.company.percent.size">The percent must be between 0-100</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_state_input" name="exchangeWithdrawMinFeeMerchantSetup"
                       ng-model="setup.exchangeWithdrawMinFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.withdraw.min.fee">Exchange Withdraw Min Fee</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="setup_exchWithMinFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_state_input" name="exchangeWithdrawMaxFeeMerchantSetup"
                       ng-model="setup.exchangeWithdrawMaxFeeMerchantSetup"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.setup.exchange.withdraw.max.fee">Exchange Withdraw Max Fee</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="setup_exchWithMaxFee">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

                <span ng-class="{'input_error_green' : !setup.exchangeWithdrawMaxFeeMerchantSetup , 'input_error_red' : setup.exchangeWithdrawMinFeeMerchantSetup > setup.exchangeWithdrawMaxFeeMerchantSetup}"
                      ng-show="setup.exchangeWithdrawMinFeeMerchantSetup > setup.exchangeWithdrawMaxFeeMerchantSetup || exchWithMaxError">
                            <s:text name="page.home.min.fee.max.fee.error">Deposit min fee must be less than deposit max fee</s:text>
                        </span>
            </div>
            <%--<div style="height: 40px">
                <div class="ist_mult_parent">
                    <div class="ist_mult ist_mult_avile_curency" data-availCurrency={{availCurrencyType}}
                         on-item-click="availCurType()"
                         isteven-multi-select
                         input-model="availableCurrencyType"
                         output-model="$parent.availableCurrencyTypeOut"
                         button-label="code"
                         item-label="name"
                         tick-property="ticked"
                         selection-mode="multiple"
                         helper-elements="filter"
                         translation="avail_currency_type"
                    >
                    </div>

                    &lt;%&ndash;<input type="hidden" name="availableRateValuesMerchantSetup" ng-value="availCurrencyType "/>&ndash;%&gt;

                </div>
            </div>--%>
            <span class="input_error" ng-cloak
                  ng-show="setup_availCurType">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            <div>
                <a ui-sref="form.companyUpload" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>

                <a ng-click="uiSerfSetup()" class="btn btn-block btn-info  ">
                    <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
                    &nbsp
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>
                </a>
            </div>
        </div>

    </script>

    <script type="text/ng-template" id="branch">
        <div class="form_div_conteiner">
            <div class="company_details">

                <h1><s:text
                        name="page.home.placeholder.company.request.form.branch.details"> Branch details </s:text></h1>
            </div>
            <div>
                <input type="text" class="company_name_input" name="nameBranch" ng-model="branch.nameBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Name">Name</s:text>"
                       required="required"/>

                <span class="input_error"
                      ng-show="branch_name_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>

            <div>
                <input type="text" class="company_addres_input" name="addressBranch" ng-model="branch.addressBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Address">Address</s:text>"
                       required="required"/>
                <span class="input_error"
                      ng-show="branch_address_show">
                             <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_city_input" name="cityBranch" ng-model="branch.cityBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.city">City</s:text>"
                       required="required"/>
                <span class="input_error"
                      ng-show="branch_city_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_street_input" name="streetBranch" ng-model="branch.streetBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.street">Street</s:text>"
                       required="required"/>
                <span class="input_error"
                      ng-show="branch_street_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>
            <div>
                <input type="text" class="company_zipcode_input" name="zipBranch" ng-model="branch.zipBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.zip.code"> Zip Code</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="branch_zip_code_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            </div>
            <div>
                <input type="email" class="company_email_input" name="emailBranch" ng-model="branch.emailBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Email">E-mail</s:text>"
                       required="required" ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>

                <span class="input_error" ng-cloak
                      ng-show="branch_e_mail_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                <span class="input_error" ng-cloak
                      ng-show="comanyActivationForm.emailBranch.$touched && comanyActivationForm.emailBranch.$error.pattern">
                    <s:text name="page.main.third.block.message.email.invalid">Your email address is invalid!</s:text>
                    </span>


            </div>

            <div class="ist_mult_parent">
                <div class="ist_mult  ist_mult_branch_phone_code " data-branchPhoneCode={{branchPhoneCode}}
                     on-item-click="branchPhoneCodeSet()"
                     isteven-multi-select
                     input-model="branchPhoneCodes"
                     output-model="$parent.branchPhoneCodesOut"
                     button-label="icon code"
                     item-label="code icon country"
                     tick-property="ticked"
                     selection-mode="single"
                     helper-elements="none"
                >
                </div>


                <%--<input type="hidden" name="phoneCodeBranch" ng-value="branchPhoneCode"/>--%>
                <input type="text" class="company_phone_input" name="phoneBranch" ng-model="branch.phoneBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="branch_phone_number_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            </div>

            <div class="ist_mult_parent">
                <div class="ist_mult ist_mult_branch_policy_code" data-branchPolicyCode={{branchPolicyCode}}
                     on-item-click="branchPolicyCodeSet()"
                     isteven-multi-select
                     input-model="branchPolicyCodes"
                     output-model="$parent.branchPolicyCodesOut"
                     button-label="icon code"
                     item-label="code icon country"
                     tick-property="ticked"
                     selection-mode="single"
                     helper-elements="none"
                >
                </div>

                <%--<input type="hidden" name="policyPhoneCodeBranch" ng-value="branchPolicyCode"/>--%>
                <input type="text" class="company_policphone_input" name="policyPhoneBranch"
                       ng-model="branch.policyPhoneBranch"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Policy.Number">Policy phone number</s:text>"
                       required="required" only-digits/>
                <span class="input_error" ng-cloak
                      ng-show="branch_policy_phone_number_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>
            </div>


            <div style="height:40px">
                <div class="ist_mult_parent">

                    <div class="ist_mult list_mult_regiont_branch " data-branchRegions={{branchRegion}}
                         on-item-click="branchRegionSet()"
                         isteven-multi-select
                         input-model="branchRegionArray"
                         output-model="$parent.branchRegionOut"
                         button-label="icon name"
                         item-label=" icon name"
                         tick-property="ticked"
                         selection-mode="single"
                         helper-elements="filter"
                         translation="select_region"
                    >
                    </div>

                    <%--<input type="hidden" name="countryRegionBranch" ng-value="branchRegion"/>--%>
                </div>


            </div>
            <span class="input_error" ng-cloak
                  ng-show="branch_region_show">
                            <s:text name="page.main.third.block.message.required">Required!</s:text>
                        </span>

            <div>
                <a ui-sref="form.setup" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>

                <a ng-click="uiSerfBranch()" class="btn btn-block btn-info  ">
                    <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
                    &nbsp
                    <i class="fa fa-hand-o-right" aria-hidden="true"></i>
                </a>

            </div>

        </div>


    </script>

    <script type="text/ng-template" id="branchUpload">
        <div>
            <div class="company_upload_parent">
                <h1><s:text name="page.home.company.request.upload.branch.logo">Upload Branch Logo</s:text></h1>
                <div class="company_upload_inpar">

                    <label for="branch_logo" class="first_label" style="background-image: url({{up_img}})">
                        <input type="file" data-file="branch" id="branch_logo" class="company_files_input"
                               accept="image/*"
                               file-model="fileInfo"/>
                    </label>
                    <label ng-show="drag_true" for="branch_logo" class="second_label"></label>
                    <div class="remove" ng-show="remove_true"></div>
                </div>
                <div>
                    <h3>{{fileInfo.name}}</h3>
                    <h3 ng-show="upload_success" class="upload_success"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_error" class="upload_error"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_remove_succsses" class="upload_remove_succsses"><s:text
                            name="page.home.company.request.upload.remove.success">Your file removed successfully</s:text></h3>
                </div>
            </div>


        </div>

        <a ui-sref="form.branch" class="btn btn-block btn-info">
            Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
        </a>
        <a ui-sref="form.cashier" class="btn btn-block btn-info  ">
            <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
            &nbsp
            <i class="fa fa-hand-o-right" aria-hidden="true"></i>
        </a>
    </script>

    <script type="text/ng-template" id="cashier">
        <div class="form_div_conteiner">
            <div class="company_details">
                <h1><s:text
                        name="page.home.placeholder.company.request.form.cashier.details"> Cashier details </s:text></h1>
            </div>
            <div>
                <input type="text" class="company_name_input" name="nameCashier" ng-model="cashier.nameCashier"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Name">Name</s:text>"
                       required="required"/>

                <span class="input_error" ng-cloak
                      ng-show="cashier_name_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
            </div>
            <div>
                <input type="text" class="company_addres_input" name="surnameCashier" ng-model="cashier.surnameCashier"
                       placeholder="<s:text name="page.home.surname">Surname</s:text>"
                       required="required"/>

                <span class="input_error" ng-cloak
                      ng-show="cashier_surname_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
            </div>

            <div>
                <input type="email" class="company_email_input" name="emailCashier" ng-model="cashier.emailCashier"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Email">E-mail</s:text>"
                       required="required" ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>

                <span class="input_error" ng-cloak
                      ng-show="cashier_e_mail_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                <span class="input_error" ng-cloak
                      ng-show="comanyActivationForm.emailCashier.$touched && comanyActivationForm.emailCashier.$error.pattern">
                    <s:text name="page.main.third.block.message.email.invalid">Your email address is invalid!</s:text>
                    </span>
            </div>

            <div class="ist_mult_parent">
                <div class="ist_mult ist_mult_cashier_phone_code" data-casheirPhoneCode={{cashierPphoneCode}}
                     on-item-click="cashierPhoneCodeSet()"
                     isteven-multi-select
                     input-model="cashierPhoneCodes"
                     output-model="$parent.cashierPhoneCodesOut"
                     button-label="icon code"
                     item-label="code icon country"
                     tick-property="ticked"
                     selection-mode="single"
                     helper-elements="none"
                >
                </div>

                <%--<input type="hidden" name="phoneCodeCashier" ng-value="cashierPphoneCode">--%>

                <input type="text" class="company_phone_input" name="phoneCashier" ng-model="cashier.phoneCashier"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="cashier_phone_number_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
            </div>

            <div>
                <a ui-sref="form.branchUpload" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>

                <a ng-click="uiSerfCashier()" class="btn btn-block btn-info">
                    Next Section <span class="glyphicon glyphicon-circle-arrow-right"></span>
                </a>

            </div>
        </div>

    </script>

    <script type="text/ng-template" id="cashierUpload">
        <div>
            <div class="company_upload_parent">
                <h1><s:text name="page.home.company.request.upload.branch.logo">Upload Branch Logo</s:text></h1>
                <div class="company_upload_inpar">

                    <label for="cashier_logo" class="first_label" style="background-image: url({{up_img}})">
                        <input type="file" data-file="cashier" id="cashier_logo" class="company_files_input"
                               accept="image/*"
                               file-model="fileInfo"/>
                    </label>
                    <label ng-show="drag_true" for="cashier_logo" class="second_label"></label>
                    <div class="remove" ng-show="remove_true"></div>
                </div>
                <div>
                    <h3>{{fileInfo.name}}</h3>
                    <h3 ng-show="upload_success" class="upload_success"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_error" class="upload_error"><s:text
                            name="page.home.company.request.upload.success">Your file uploaded successfully</s:text></h3>
                    <h3 ng-show="upload_remove_succsses" class="upload_remove_succsses"><s:text
                            name="page.home.company.request.upload.remove.success">Your file removed successfully</s:text></h3>
                </div>
            </div>


        </div>

        <a ui-sref="form.cashier" class="btn btn-block btn-info">
            Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
        </a>
        <a ui-sref="form.role" class="btn btn-block btn-info  ">
            <s:text name="page.home.placeholder.company.request.form.next">Next</s:text>
            &nbsp
            <i class="fa fa-hand-o-right" aria-hidden="true"></i>
        </a>
    </script>

    <script type="text/ng-template" id="role">

        <div class="form_div_conteiner">


            <div class="company_details">
                <h1><s:text
                        name="page.home.placeholder.company.request.form.role.details"> Role details </s:text></h1>
            </div>

            <div>
                <input type="text" class="company_name_input" name="nameRole" ng-model="role.nameRole"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.Name">Name</s:text>"
                       required="required"/>

                <span class="input_error" ng-cloak
                      ng-show="role_name_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
            </div>
            <div>

                <textarea class="role_textarea" name="descriptionRole" ng-model=role.descriptionRole
                          placeholder="<s:text name="page.home.placeholder.company.request.form.role.description">Role description</s:text>"
                >

                </textarea>

            </div>

            <div>
                <input type="text" class="company_addres_input" name="transactionMinRole"
                       ng-model="role.transactionMinRole"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.role.transaction.min">Role Min Transaction</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="role_transactionMinRole_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
            </div>

            <div>
                <input type="text" class="company_addres_input" name="transactionMaxRole"
                       ng-model="role.transactionMaxRole"
                       placeholder="<s:text name="page.home.placeholder.company.request.form.role.transaction.max">Role Max Transaction</s:text>"
                       required="required" only-digits/>

                <span class="input_error" ng-cloak
                      ng-show="role_transactionMaxRole_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                <span ng-class="{'input_error_green' : !role.transactionMaxRole , 'input_error_red' : role.transactionMinRole > role.transactionMaxRole}"
                      ng-show="role.transactionMinRole > role.transactionMaxRole || transMaxError">
                            <s:text name="page.home.min.fee.max.fee.error">Deposit min fee must be less than deposit max fee</s:text>
                        </span>
            </div>

            <div style="height: 40px">
                <div class="ist_mult_parent">
                    <div class="ist_mult list_mult_currency" data-currency={{roleCurrencyTyRate}}
                         on-item-click="curType()"
                         isteven-multi-select
                         input-model="roleCurrencyTyRateValues"
                         output-model="$parent.roleCurrencyTypeOut"
                         button-label="code"
                         item-label=" name"
                         tick-property="ticked"
                         selection-mode="multiple"
                         helper-elements="filter"
                         translation="currency_type"
                    >
                    </div>

                    <input type="hidden" name="availableRateValuesRole" ng-value="roleCurrencyTyRate"/>

                </div>
            </div>
            <span class="input_error"
                  ng-show="setup_currencyType">
            <s:text name="page.main.third.block.message.required">Required!</s:text>
            </span>


            <div>
                <a ui-sref="form.cashierUpload" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>


                <a ng-click="uiSerfRole()" class="btn btn-block btn-info">
                    Next Section <span class="glyphicon glyphicon-circle-arrow-right"></span>
                </a>
            </div>
        </div>
    </script>

    <script type="text/ng-template" id="verification">

        <div class="form_div_conteiner">
            <div class="company_details">
                <h1><s:text
                        name="label.verification.text">Verification</s:text></h1>
            </div>
            <ng-form class="ngform" name="verificationForm">
                <div class="ng_div">
                    <input type="text" class="company_name_input" ng-cut="$event.preventDefault()"
                           ng-copy="$event.preventDefault()" ng-paste="$event.preventDefault()"
                           ng-keyup="verificat_fields()" name="passwordCashier" ng-model="verification.passwordCashier"
                           ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#\$%\^\&*\)\(+=._-]{6,}$/"
                           placeholder="<s:text name="label.password">Password</s:text>"
                           required="required"/>

                    <span class="input_error" ng-cloak
                          ng-show="verificationForm.passwordCashier.$error.pattern">
                    <s:text name="pages.registration.password.lengt.6">Your password must be at least 6 characters long: one number, one lowercase and one uppercase letter.</s:text>
                    </span>
                </div>
                <div class="ng_div">
                    <input type="text" class="company_addres_input" ng-cut="$event.preventDefault()"
                           ng-copy="$event.preventDefault()" ng-paste="$event.preventDefault()"
                           ng-keyup="verificat_fields()" name="passwordCashierRepeat"
                           ng-model="verification.passwordCashierRepeat"
                           placeholder="<s:text name="label.password.pereat">Repeat password</s:text>"
                           required="required"/>

                    <span class="input_error" ng-cloak
                          ng-show="verificationForm.passwordCashierRepeat.$error.required && verificationForm.passwordCashierRepeat.$touched">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>

                    <span class="input_error" ng-cloak
                          ng-show="password_match">
                    <s:text name="pages.registration.password.type.match">Your password must be the same in both fields!</s:text>
                    </span>

                </div>

                <div class="ng_div">
                    <input type="text" class="company_addres_input" ng-keyup="verificat_fields()"
                           name="verificationCode" ng-model="verification.verificationCode"
                           placeholder="<s:text name="label.verification.code">Verification code</s:text>"
                           required="required"/>

                    <span class="input_error" ng-cloak
                          ng-show="cashier_address_show">
                    <s:text name="page.main.third.block.message.required">Required!</s:text>
                    </span>
                </div>


                <div class="g-recaptcha ng_div" data-theme="dark"
                     data-sitekey="<%=Initializer.getSetupInfo().recaptchaClientKey%>"></div>


            </ng-form>
            <div>
                <a ui-sref="form.role" class="btn btn-block btn-info">
                    Back <span class="glyphicon glyphicon-circle-arrow-left"></span>
                </a>
                <div ng-show="show_form" class="main_form">
                    <form action="activate-company.htm" method="post">


                        <input type="text" ng-repeat="(key, value) in inputs"
                               name={{key}} ng-model=value
                        />

                        <button class="btn btn-block  btn-info">
                            submit
                        </button>
                    </form>
                </div>

            </div>

        </div>


    </script>


</div>