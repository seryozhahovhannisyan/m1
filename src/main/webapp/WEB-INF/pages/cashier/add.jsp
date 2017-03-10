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
<script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/datePicker/ui-bootstrap-tpls-1.3.3.js"></script>

<script type="text/javascript">

    var istevenMultiSelectTranslateObject = {
        search       : '<s:text name="page.branches.modal.multi.select.Search">Search</s:text>',
        SelectAll    : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.All">Select All</s:text>',
        SelectNone   : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Select.None">Select None</s:text>',
        Reset        : '&nbsp;&nbsp;<s:text name="page.branches.modal.multi.select.Reset">Reset</s:text>',
        NoneSelected : '<s:text name="page.branches.modal.multi.select.None.Selected">None Selected</s:text>'
    };

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/add-controller.js"></script>
<div id="add" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade in " style="display: none; padding-right: 17px;" ng-controller="branchesFormPhoneCodeCtrl as branchesFormPhoneCode">
    <div class="modal-dialog add_modal_content " id="draggable">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span>
                </button>
                <h4 id="myModalLabel" class="modal-title">
                    <s:text name="page.branches.modal.add.new.branch">Add New Branch</s:text>
                </h4>
            </div>
            <div class="modal-body">
                <h4>
                    <s:text name="page.branches.modal.add.modal.text">Text in a modal</s:text>
                </h4>
                  <div class="add_modal_form_block" ng-controller="multiSelectDropDownCtrl as multiSelectDropDown">
                    <form method="post" action="">
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

                            <div ng-controller="DatepickerPopupDemoCtrl">
                                <pre>
                                    <s:text name="page.branches.modal.add.modal.datepicker.Selected.date.is">Selected date is:</s:text> <em>{{dt | date:'fullDate' }}</em>
                                </pre>
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
                                <button type="button" class="btn btn-sm btn-danger" ng-click="clear()">
                                    <s:text name="page.branches.modal.add.modal.datepicker.Clear">Clear</s:text>
                                </button>
                            </div>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.email">E-mail</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Phone">Phone</s:text>"
                                   class="form-control branch_add_phone_input"/>
                            <div >
                                  <div class="phone_code_block">
                                    <div class="toggle_for_phone_code" ng-click="branchesFormPhoneCode.branchesPhoneCodeToggle()">
                                        <img ng-cloak ng-src="{{branchesFormPhoneCode.branchesPhoneCodeToggleImgSrc}}"
                                             alt="<s:text name="page.branches.modal.add.modal.alt.Select.Phone.Code">Select Phone Code</s:text>"
                                        />
                                    </div>
                                    <img ng-cloak class="request_form_code_flag"
                                         alt="<s:text name="page.branches.modal.add.modal.alt.Country.Flag">Country Flag</s:text>"
                                         ng-src="<%=request.getContextPath()%>/img/general/icon/language/{{branchesFormPhoneCode.selected.imgFlagSrc}}" />
                                    <input type="text" ng-value="branchesFormPhoneCode.selected.code" ng-model-options="{ getterSetter: true }" class="request_form_phone_code" readonly="readonly"/>
                                </div>
                                <div class="toggle_block_for_phone_code" ng-style="branchesFormPhoneCode.toggleBlockForPhoneCodeOpacity">
                                    <div class="toggle_block_items" ng-repeat="toggleBlockItem in branchesFormPhoneCode.toggleBlockItems" ng-click="branchesFormPhoneCode.selectCode(toggleBlockItem)">
                                        <span class="toggle_block_item_code">{{toggleBlockItem.code}}</span>
                                        <img class="toggle_block_item_flag"
                                             alt="<s:text name="page.branches.modal.add.modal.alt.Country.Flag">Country Flag</s:text>"
                                             ng-src="<%=request.getContextPath()%>/img/general/icon/language/{{toggleBlockItem.imgFlagSrc}}" />
                                        <span class="toggle_block_item_country">{{toggleBlockItem.country}}</span>
                                    </div>
                                </div>
                            </div>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Policy.Phone">Policy Phone</s:text>"
                                   class="form-control"/>
                            <div class="checkbox">
                                <label for="active_checkbox">
                                    <input id="active_checkbox" autocomplete="off" type="checkbox" name="" required="required" value="active" />
                                    <s:text name="page.branches.modal.add.modal.placeholder.checkbox.Active">Active</s:text>
                                </label>
                            </div>
                            <div isteven-multi-select
                                    input-model="multiSelectDropDown.modernBrowsers"
                                    output-model="outputBrowsers"
                                    button-label="icon name"
                                    item-label="icon name maker"
                                    tick-property="ticked">
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
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">
                    <s:text name="page.branches.modal.add.modal.Close">Close</s:text>
                </button>
                <button class="btn btn-primary" type="button">
                    <s:text name="page.branches.modal.add.modal.Save.Changes">Save Changes</s:text>
                </button>
            </div>

        </div>
    </div>
</div>