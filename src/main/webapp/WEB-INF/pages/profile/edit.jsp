<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">



</script>

<div id="edit_profile" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade in" style="display: none; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span>
                </button>
                <h4  class="profile-modal-title">Privacy Settings</h4>
            </div>
            <div class="modal-body" ng-controller="profileFormValidateCtrl as profileFormValidate">
                <form id="rf" name="profileSettingsForm" action="do-company-form-request.htm" method="post">

                    <div class="main-request-form">
                        <div class="request-form-group">
                               <input autocomplete="off" type="text" class="request-form-control" name="companyName"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Name">Company Name</s:text>"
                                   required="required" ng-model="profileFormValidate.company.name"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyName.$touched && profileSettingsForm.companyName.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.companyName.$dirty">
                                <s:fielderror  name="companyName" fieldName="companyName" />
                            </div>
                        </div>
                        <div class="request-form-group">
                            <input autocomplete="off" type="text" class="request-form-control" name="companyAddress" cssClass="custom" escape="true"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Address">Company Address</s:text>"
                                   required="required" ng-model="profileFormValidate.company.address"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyAddress.$touched && profileSettingsForm.companyAddress.$error.required">
                           <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.companyAddress.$dirty">
                                <s:fielderror name="companyAddress" fieldName="companyAddress"/>
                            </div>
                        </div>
                        <div class="request-form-group">
                            <input autocomplete="off" type="email" class="request-form-control" name="companyEmail"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Company.Email">Company E-mail</s:text>"
                                   required="required" ng-model="profileFormValidate.company.companyEmail" ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyEmail.$touched && profileSettingsForm.companyEmail.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyEmail.$touched && profileSettingsForm.companyEmail.$error.pattern">
                            <s:text name="page.profile.message.email.invalid">Your email address is invalid!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.companyEmail.$dirty">
                                <s:fielderror name="companyEmail" fieldName="companyEmail"/>
                            </div>
                        </div>
                        <div >
                            <div class="request-form-group ">
                                <div ng-controller="phoneCodeIstevenCtrl as phoneCodeIsteven" class="phone_code">
                                    <div  isteven-multi-select
                                          input-model="phoneCodeIsteven.phoneCode"
                                          output-model="outputBrowsers"
                                          button-label="icon name maker"
                                          item-label="icon name maker"
                                          helper-elements=""
                                          selection-mode="single"
                                          tick-property="ticked">
                                    </div>
                                </div>
                                <input autocomplete="off" type="tel" class="request-form-control request-form-phone-number" name="companyPhone"
                                       placeholder="<s:text name="page.home.placeholder.company.request.form.Phone.Number">Phone Number</s:text>"
                                       required="required" ng-model="profileFormValidate.company.phone" ng-pattern="/[0-9]/"/>
                            </div>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyPhone.$touched && profileSettingsForm.companyPhone.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.companyPhone.$touched && profileSettingsForm.companyPhone.$error.pattern">
                            <s:text name="page.profile.message.only.numbers">Only Numbers Allowed!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.companyPhone.$dirty">
                                <s:fielderror name="companyPhone" fieldName="companyPhone"/>
                            </div>
                        </div>
                        <div ng-controller="profileSettingsNumberPickerCtrl as profileSettingsNumberPicker">
                            <div class="request-form-group">
                                <%--todo min 1 key pres implementation--%>
                                <input type="text" class="request-form-control request_form_number_input" name="countOfBranches" autocomplete="off"
                                       placeholder="<s:text name="page.home.placeholder.company.request.form.Number.of.Branches">Number of Branches</s:text>"
                                       required="required" ng-model="profileSettingsNumberPicker.numberInput" ng-change="profileSettingsNumberPicker.checkMinOne(profileSettingsNumberPicker.numberInput)" ng-pattern="/[0-9]/"/>
                                <div class="request_form_number_picker_block">
                                    <img class="number_picker_increment"
                                         alt="<s:text name="page.home.alt.company.request.form.Increment">Increment</s:text>"
                                         src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_top.png" ng-click="profileSettingsNumberPicker.profileSettingsNumberIncrement(profileSettingsNumberPicker.numberInput)" />
                                    <img class="number_picker_decrement"
                                         alt="<s:text name="page.home.alt.company.request.form.Decrement">Decrement</s:text>"
                                         src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_bottom.png" ng-click="profileSettingsNumberPicker.profileSettingsNumberDecrement(profileSettingsNumberPicker.numberInput)" />
                                </div>
                            </div>
                            <div role="alert" class="request_form_error_div">
                            <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.countOfBranches.$touched && profileSettingsForm.countOfBranches.$error.required">
                                <s:text name="page.profile.message.required">Required!</s:text>
                            </span>
                            <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.countOfBranches.$touched && profileSettingsForm.countOfBranches.$error.pattern">
                                <s:text name="page.profile.message.only.numbers">Only Numbers Allowed!</s:text>
                            </span>
                                <div ng-hide="profileSettingsForm.countOfBranches.$dirty">
                                    <s:fielderror name="countOfBranches" fieldName="countOfBranches"/>
                                </div>
                            </div>
                        </div>
                        <div ng-controller="profileSettingsNumberPickerCtrl as profileSettingsNumberPicker">
                            <div class="request-form-group">
                                <%--todo min 1 key pres implementation--%>
                                <input type="text" class="request-form-control request_form_number_input" name="countOfWorkers" autocomplete="off"
                                       placeholder="<s:text name="page.home.placeholder.company.request.form.Number.of.Employees">Number of Employees</s:text>"
                                       required="required" ng-model="profileSettingsNumberPicker.numberInput" ng-change="profileSettingsNumberPicker.checkMinOne(profileSettingsNumberPicker.numberInput)" ng-pattern="/[0-9]/"/>
                                <div class="request_form_number_picker_block">
                                    <img class="number_picker_increment"
                                         alt="<s:text name="page.home.alt.company.request.form.Increment">Increment</s:text>"
                                         src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_top.png" ng-click="profileSettingsNumberPicker.profileSettingsNumberIncrement(profileSettingsNumberPicker.numberInput)" />
                                    <img class="number_picker_decrement"
                                         alt="<s:text name="page.home.alt.company.request.form.Decrement">Decrement</s:text>"
                                         src="<%=request.getContextPath()%>/img/main/merchant_main_arrow_bottom.png" ng-click="profileSettingsNumberPicker.profileSettingsNumberDecrement(profileSettingsNumberPicker.numberInput)" />
                                </div>
                            </div>
                            <div role="alert" class="request_form_error_div">
                            <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.countOfWorkers.$touched && profileSettingsForm.countOfWorkers.$error.required">
                                <s:text name="errors.required">Required!</s:text>
                            </span>
                            <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.countOfWorkers.$touched && profileSettingsForm.countOfWorkers.$error.pattern">
                                <s:text name="page.profile.message.only.numbers">Only Numbers Allowed!</s:text>
                            </span>
                                <div ng-hide="profileSettingsForm.countOfWorkers.$dirty">
                                    <s:fielderror name="countOfWorkers" fieldName="countOfWorkers"/>
                                </div>
                            </div>
                        </div>
                        <div class="request-form-group">
                            <input autocomplete="off" type="text" class="request-form-control" name="contactName"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Name">Contact Name</s:text>"
                                   required="required" ng-model="profileFormValidate.company.contactName"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactName.$touched && profileSettingsForm.contactName.$error.required">
                            <s:text name="errors.required">Required!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.contactName.$dirty">
                                <s:fielderror name="contactName" fieldName="contactName"/>
                            </div>
                        </div>
                        <div class="request-form-group">
                            <input autocomplete="off" type="text" class="request-form-control" name="contactLastName"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Surname">Contact Surname</s:text>"
                                   required="required" ng-model="profileFormValidate.company.contactSurname"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactLastName.$touched && profileSettingsForm.contactLastName.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.contactLastName.$dirty">
                                <s:fielderror name="contactLastName" fieldName="contactLastName"/>
                            </div>
                        </div>
                        <div class="request-form-group">
                            <input autocomplete="off" type="email" class="request-form-control" name="contactEmail"
                                   placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Email">Contact E-mail</s:text>"
                                   required="required" ng-model="profileFormValidate.company.contactEmail" ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/"/>
                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactEmail.$touched && profileSettingsForm.contactEmail.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactEmail.$touched && profileSettingsForm.contactEmail.$error.pattern">
                            <s:text name="page.profile.message.email.invalid">Your email address is invalid!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.contactEmail.$dirty">
                                <s:fielderror name="contactEmail" fieldName="contactEmail"/>
                            </div>
                        </div>
                        <div>
                              <div class="request-form-group">
                                  <div ng-controller="phoneCodeContactIstevenCtrl as phoneCodeContactIsteven" class="phone_code_contact">
                                      <div
                                            isteven-multi-select
                                            input-model="phoneCodeContactIsteven.phoneCode"
                                            output-model="outputBrowsers"
                                            button-label="icon name maker"
                                            item-label="icon name maker"
                                            helper-elements=""
                                            selection-mode="single"
                                            tick-property="ticked">
                                      </div>
                                  </div>
                                <input autocomplete="off" type="tel" class="request-form-control request-form-phone-number" name="contactPhone"
                                       placeholder="<s:text name="page.home.placeholder.company.request.form.Contact.Phone">Contact Phone</s:text>"
                                       required="required" ng-model="profileFormValidate.company.contactPhone" ng-pattern="/[0-9]/"/>
                            </div>

                        </div>
                        <div role="alert" class="request_form_error_div">
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactPhone.$touched && profileSettingsForm.contactPhone.$error.required">
                            <s:text name="page.profile.message.required">Required!</s:text>
                        </span>
                        <span class="request_form_error_span" ng-cloak ng-show="profileSettingsForm.contactPhone.$touched && profileSettingsForm.contactPhone.$error.pattern">
                            <s:text name="page.profile.message.only.numbers">Only Numbers Allowed!</s:text>
                        </span>
                            <div ng-hide="profileSettingsForm.contactPhone.$dirty">
                                <s:fielderror name="contactPhone" fieldName="contactPhone"/>
                            </div>
                        </div>


                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                            <button class="btn btn-primary"  type="submit" ng-disabled="profileSettingsForm.$invalid">Save changes</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>