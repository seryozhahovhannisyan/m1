<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="<%=request.getContextPath()%>/libs/js/angular/angular-sanitize.js"></script>
<script src="<%=request.getContextPath()%>/libs/js/angular/ng-table.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/general/object-list.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/branches/branch.js" type="text/javascript" ></script>


<script type="text/javascript">

    $(document).ready(function(){
        console.log('moduls_array', moduls_array)
    })

    var contextPath = '<%=request.getContextPath()%>';
    var object = 'branch';
    var itemsCount = '<s:property value="dataCount"/>';

    var columns = [
        {title: 'name', field: 'name', visible: true},
        {title: 'address', field: 'address', visible: true},
        {title: 'email', field: 'email', visible: true},
        {title: 'city', field: 'city', visible: true},
        {title: 'street', field: 'street', visible: true},
        {title: 'zip', field: 'zip', visible: true},
        {title: 'phoneCode', field: 'phoneCode', visible: true},
        {title: 'phone', field: 'phone', visible: true},
        {title: 'policyPhoneCode', field: 'policyPhoneCode', visible: true},
        {title: 'policyPhone', field: 'policyPhone', visible: true},
        {title: 'status', field: 'status', visible: true}
    ];

</script>

<div class="right_col" role="main" style="min-height: 2519px;">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2>
                        Branch
                    </h2>

                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="datatable-responsive_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                        <div class="row">
                            <div class="add_div_brch_parent">
                                <div class="add_div_brch"  data-target="#add" data-toggle="modal"></div>
                                <div><s:text name="page.branches.main.add.record">Add new record</s:text></div>
                            </div>
                            <div class="add_div_brch_parent">
                                <div class="delete_div_brch" data-target="#add" data-toggle="modal"></div>
                                <div><s:text name="page.branches.main.delete.marked">Delete marked</s:text></div>
                            </div>
                        </div>
                        <div class="row">

                            <div ng-controller="listController" id="listContent">

                                <s:if test="%{dataCount != 0}">

                                    <div class="btn-group">`

                                        <label class="btn btn-primary"
                                               ng-repeat="column in columns"
                                               ng-class="{'not_checked' :!column.visible }"
                                        >

                                            <input type="checkbox"  ng-model="column.visible" ng-disabled="disable_labels($index)"  />

                                            <span>{{column.title}}</span>
                                        </label>
                                    </div>

                                    <div class="table_parent_div">
                                        <table ng-table="tableParams"  show-filter="true" class="table table_br_csh table-condensed table-bordered table-striped">
                                            <div class="form-group pull-right">
                                                <input type="text" class="form-control breanch_search" ng-model="serch_val"  placeholder="<s:text  name="page.login.bracnh.search"> Search for...</s:text>"/>
                                                <buutton class="btn btn-default branch_search_button" ng-click=tableParams.filter(serch_val)>
                                                    <s:text name="page.branches.main.list.table.go">GO</s:text>
                                                </buutton>
                                            </div>
                                            <thead>
                                                <tr>
                                                    <th><input type="checkbox"  ng-model ="checked_all" /></th>
                                                    <th> <s:text name="img/general/avatars/avatar.png">User photo</s:text></th>
                                                    <th ng-repeat="column in columns" ng-show="column.visible"
                                                        class="text-center sortable"
                                                        ng-class="{ 'sort-asc': tableParams.isSortBy(column.field, 'asc'),  'sort-desc': tableParams.isSortBy(column.field, 'desc') }"
                                                        style="vertical-align: top; text-align: center; min-width: 90px;"
                                                        ng-click="tableParams.sorting(column.field, tableParams.isSortBy(column.field, 'asc') ? 'desc' : 'asc')">
                                                        {{column.title}}
                                                    </th>
                                                    <th>Editing</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="item in $data  "
                                                    ng-class="{'tbl_row_even': $even, 'tbl_row_odd': $odd }" >
                                                    <td>
                                                        <input type="checkbox" name="id" ng-checked="checked_all"/>
                                                    </td>
                                                    <td>
                                                        <img ng-src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt = "avatar"/>
                                                    </td>
                                                    <td ng-show="columns[0].visible" sortable="columns[0].field">
                                                        {{item.name ? item.name : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[1].visible" sortable="columns[1].field">
                                                        {{item.address ? item.address : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[6].visible" sortable="columns[6].field">
                                                        {{item.phoneCode ? item.phoneCode : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                        {{item.city ? item.city : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                        {{item.street ? item.street : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[4].visible" sortable="columns[4].field">
                                                        {{item.zip ? item.zip : 'N/A'}}
                                                    </td>

                                                    <td ng-show="columns[6].visible" sortable="columns[6].field">
                                                        {{item.phoneCode ? item.phoneCode : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[7].visible" sortable="columns[7].field">
                                                        {{item.phone ? item.phone : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[8].visible" sortable="columns[8].field">
                                                        {{item.policyPhoneCode ? item.policyPhoneCode : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[9].visible" sortable="columns[9].field">
                                                        {{item.policyPhone ? item.policyPhone : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[10].visible" sortable="columns[10].field">
                                                        {{item.status ? item.status : 'N/A'}}
                                                    </td>

                                                    <td class="edit_td" ng-click="dropdown_tds($event)" data-edit_id = {{item.id}}>
                                                        <span>EDIT <i class="fa fa-sort-desc" aria-hidden="true"></i></span>
                                                        <ul class="branches_crud_ul">
                                                            <li data-target="#edit"  data-id = "{{item.id}}" data-toggle="modal" ng-click = "viewDetail($event)">
                                                                <i  class="fa fa_edit fa-pencil-square-o" aria-hidden="true"></i>
                                                               <s:text name="page.branches.main.list.table.edit">Edit</s:text>
                                                            </li>
                                                            <li data-target="#detail" data-id = "{{item.id}}" data-toggle="modal" ng-click = "viewDetail($event)">
                                                                <i  class="fa fa_detail fa-info-circle" aria-hidden="true"></i>
                                                                <s:text name="page.branches.main.list.table.details">Details</s:text>
                                                            </li>
                                                            <li data-target="#upload" data-id = "{{item.id}}" data-toggle="modal" ng-click = "viewDetail($event)">
                                                                <i class="fa fa_upload fa-upload" aria-hidden="true"></i>
                                                                <s:text name="page.branches.main.list.table.datas">Data</s:text>
                                                            </li>
                                                            <li data-target="#delete" data-id = "{{item.id}}" data-toggle="modal" ng-click = "viewDetail($event)">
                                                                <i class="fa  fa_delete fa-trash-o" aria-hidden="true"></i>
                                                                <s:text name="page.branches.main.list.table.delete">Delete</s:text>
                                                            </li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'/>
                                    <input type="hidden" ng-model="actionPath" ng-init="actionPath='branch-list.htm'"/>
                                    <input type="hidden" ng-model="actionPathLoad" ng-init="actionPathLoad='branch-view.htm'"/>

                                    </s:if>
                                    <s:else>
                                        <s:text name="page.branches.info.list.data.found">Data not found</s:text>
                                    </s:else>
                                </div>
                            </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
