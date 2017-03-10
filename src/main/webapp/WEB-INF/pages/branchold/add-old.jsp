<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="<%=request.getContextPath()%>/libs/css/isteven-multi-select.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/libs/css/datePicker/datePicker.css" rel="stylesheet"/>
<script type="text/javascript">

    var istevenMultiSelectTranslateObject = {
        search       : '<s:text name="page.branches.modal.multi.select.Search">Search</s:text>',
        SelectAll    : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.All">Select All</s:text>',
        SelectNone   : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.None">Select None</s:text>',
        Reset        : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Reset">Reset</s:text>',
        NoneSelected : '<s:text name="page.branches.modal.multi.select.None.Selected">None Selected</s:text>'
    };

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/datePicker/ui-bootstrap-tpls-1.3.3.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/add-controller.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/modal_minimize_restorize.js"></script>

<div id="add" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade" style="display: none; padding-right: 17px;" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog lg-modal resizable_style" id="draggable_add" >
        <div class="modal-content resizable_style" >
            <div class="modal-header">
                <button data-dismiss="modal" class="close close_modal" type="button"><span aria-hidden="true">×</span>
                </button>
                <div class="add_modal_restore">
                    <img
                            alt="<s:text name="page.branches.modal.add.Maximize.Minimize">Maximize/Minimize</s:text>"
                            src="<%=request.getContextPath()%>/img/branches/maximize.png">
                </div>
                <div class="add_modal_minimize">
                    <i class="glyphicon glyphicon-minus"></i>
                </div>
                <h4 id="myModalLabel" class="modal-title">
                    <s:text name="page.branches.modal.add.new.branch">Add New Branch</s:text>
                </h4>
            </div>
            <div class="modal-body">
                <div class="add_modal_form_block" >
                    <form method="post" action="">
                        <div class="col-lg-10 add_modal_form_block">
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Name">Name</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address">Address</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.City">City</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.Street">Street</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.Zip">Zip</s:text>"
                                   class="form-control"/>

                            <div ng-controller="DatepickerPopupDemoCtrl" class="date_picker">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p class="input-group">
                                            <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="dt" is-open="popup1.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" alt-input-formats="altInputFormats" />
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-default" ng-click="open1()"><i class="glyphicon glyphicon-calendar"></i></button>
                                              </span>
                                        </p>
                                    </div>
                                    <input type="hidden" ng-value="dt" class="form-control" uib-datepicker-popup ng-model="dt" is-open="popup2.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" />
                                </div>
                            </div>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.email">E-mail</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Phone">Phone</s:text>"
                                   class="form-control branch_add_phone_input"/>
                            <div ng-controller="phoneCodeIstevenCtrl as phoneCodePolicyIsteven" class="phone_code_policy_isteven">
                                <div  isteven-multi-select
                                      input-model="phoneCodePolicyIsteven.phoneCode"
                                      output-model="outputBrowsers"
                                      button-label="icon name maker"
                                      item-label="icon name maker"
                                      helper-elements=""
                                      selection-mode="single"
                                      tick-property="ticked">
                                </div>
                            </div>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Policy.Phone">Policy Phone</s:text>"
                                   class="form-control branch_add_phone_input"/>

                            <div ng-controller="phoneCodeIstevenCtrl as phoneCodeIsteven">
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
                            <div class="clear_both"></div>
                            <div class="checkbox">
                                <label for="active_checkbox">
                                    <input id="active_checkbox" autocomplete="off" type="checkbox" name="" required="required" value="active" />
                                    <s:text name="page.branches.modal.add.modal.placeholder.checkbox.Active">Active</s:text>
                                </label>
                            </div>
                            <div ng-controller="multiSelectDropDownCtrl as multiSelectDropDown">
                                <div  isteven-multi-select
                                      input-model="multiSelectDropDown.modernBrowsers"
                                      output-model="outputBrowsers"
                                      button-label="icon name"
                                      item-label="icon name maker"
                                      tick-property="ticked">
                                </div>
                            </div>
                            <div class="add_block_checkbox col-lg-6">
                                checkbox block
                                <div class="checkbox">
                                    <label><input type="checkbox" value="">
                                        Option 1
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label><input type="checkbox" value="">
                                        Option 2
                                    </label>
                                </div>
                                <div class="checkbox disabled">
                                    <label><input type="checkbox" value="" disabled>
                                        Option 3
                                    </label>
                                </div>
                            </div>

                            <div class="add_block_radio col-lg-6">
                                radio button block
                                <div class="radio">
                                    <label><input type="radio" name="optradio">
                                        Option 1
                                    </label>
                                </div>
                                <div class="radio">
                                    <label><input type="radio" name="optradio">
                                        Option 2
                                    </label>
                                </div>
                                <div class="radio disabled">
                                    <label><input type="radio" name="optradio" disabled>
                                        Option 3
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">
                    <s:text name="page.branches.modal.add.modal.Close">Close</s:text>
                </button>
                <button class="btn btn-primary add_modal_button_submit" type="button" >
                    <s:text name="page.branches.modal.add.modal.Save.Changes">Save Changes</s:text>
                </button>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">




</script>
