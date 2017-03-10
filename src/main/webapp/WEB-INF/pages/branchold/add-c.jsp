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
    var countries = [
        <s:iterator value="countries" >
        {
            id: '<s:property escapeJavaScript="true" value="id"/>',
            phoneCode: '<s:property escapeJavaScript="true" value="phoneCode"/>',
            name: '<s:property escapeJavaScript="true" value="name"/>',
            ticked: false  },
        </s:iterator>
    ];


    var isteven_countries = [
        {
            field: 'country_id',
            inPut: countries,
            outPut: [],
            localeFilter : localeFilter
        }
    ];

   /* function save_data(){

    }*/
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/branches/modal_minimize_restorize.js"></script>

<div id="add" aria-hidden="true" role="dialog" tabindex="-1" class="modal fade" style="display: none; padding-right: 17px;" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog lg-modal resizable_style" id="draggable_add" >
        <div class="modal-content resizable_style" >
            <div class="modal-header">
                <button data-dismiss="modal" class="close close_modal" type="button">
                    <span aria-hidden="true">×</span>
                </button>
                <div class="add_modal_restore">
                    <img alt="<s:text name="page.branches.modal.add.Maximize.Minimize">Maximize/Minimize</s:text>"
                         src="<%=request.getContextPath()%>/img/branches/maximize.png">
                </div>
                <div class="add_modal_minimize">
                    <i class="glyphicon glyphicon-minus"></i>
                </div>
                <h4 id="myModalLabel" class="modal-title">
                    <s:text name="page.branches.modal.add.new.branch">Add New Branch</s:text>
                </h4>
            </div>
            <div class="modal-body" ng-controller="listController">
                <div class="add_modal_form_block" >
                    <form id="add_branch" method="post" action="branch-add.htm">
                        <div class="col-lg-10 add_modal_form_block">
                            <input autocomplete="off" type="text" name="name" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Name">Name</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="address" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address">Address</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="city" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.City">City</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="street" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.Street">Street</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="zip" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Address.Zip">Zip</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="email" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.email">E-mail</s:text>"
                                   class="form-control"/>
                            <input autocomplete="off" type="text" name="" required="required"
                                   placeholder="<s:text name="page.branches.modal.add.modal.placeholder.Phone">Phone</s:text>"
                                   class="form-control branch_add_phone_input"/>
                            <div class="phone_code_policy_isteven">
                                <isteven-multi-select
                                        directive-id="filter_branch"
                                        input-model="isteven_countries[0].inPut"
                                        output-model="isteven_countries[0].outPut"
                                        button-label="phoneCode"
                                        item-label="phoneCode name"
                                        max-labels="1"
                                        max-height="auto"
                                        min-search-length="1"
                                        output-properties="phoneCode"
                                        translation="isteven_countries[0].localeFilter"
                                        isteven-multi-select
                                        tick-property="ticked" >
                                </isteven-multi-select>
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

