<%--
  Created by IntelliJ IDEA.
  User: Serozh
  Date: 7/29/2016
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script src="<%=request.getContextPath()%>/libs/js/angular/angular-sanitize.js" ></script>
<script src="<%=request.getContextPath()%>/libs/js/angular/ng-table.min.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/js/general/object-list.js" type="text/javascript"></script>


<link href="<%=request.getContextPath()%>/css/template/cashier.css" type="text/css" rel="stylesheet">



<script type="text/javascript"  >

    $(document).ready(function(){
        var branch = '<s:property value="branch.name"/>';
        var branches = '<s:property value="branches[0].name"/>';
        if(branch != null && branch.length != 0){
            alert("Hide isteven");
        } else {
            alert("Show isteven");
        }
    })

    var contextPath = '<%=request.getContextPath()%>';
    var object = 'cashier';
    var itemsCount = '<s:property value="dataCount"/>';
    var columns = [
        {title: 'name', field: 'name', visible : true},
        {title: 'surname', field: 'surname', visible : true},
        {title: 'email', field: 'email', visible : true},
        {title: 'phone', field: 'phone', visible : true},
        {title: 'registeredAt', field: 'registeredAt', visible : true},
        {title: 'activatedAt', field: 'activatedAt', visible : true},
        {title: 'privilege', field: 'privilege', visible : true}
    ];

    var branches = [
        <s:iterator value="branches" >
            {id: '<s:property escapeJavaScript="true" value="id"/>', name: '<s:property escapeJavaScript="true" value="name"/>', ticked: true  },
        </s:iterator>
    ];


    var isteven_branches = [
        {
            field: 'branchId', inPut: branches, outPut: [],
            localeFilter : localeFilter
        }
    ];


</script>

<div class="right_col" role="main" style="min-height: 2519px;" ng-controller="listController">
    <div class="clearfix"></div>
    <div class="row"   >
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>
                         Cashier
                    </h2>

                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row">
                            <div class="add_div_brch_parent">
                                <div class="add_div_brch"  data-toggle="modal"></div>
                                <div><s:text name="page.branches.main.add.record">Add new record</s:text></div>
                            </div>
                            <div class="add_div_brch_parent">
                                <div class="delete_div_brch" data-target="#add" data-toggle="modal"></div>
                                <div><s:text name="page.branches.main.delete.marked">Delete marked</s:text></div>
                            </div>
                        </div>

                        <div class="row">
                            <div  id="listContent">
                                <s:if test="%{dataCount != 0}">
                                    <%--<form action="#" method="post">--%>
                                    <div class="btn-group">
                                        <label class="btn btn-primary"
                                               ng-repeat="column in columns"
                                               <%--ng-class="column.visible ? 'btn btn-primary ng-scope ng-binding' : 'btn btn-primary ng-scope ng-binding column-unchecked'"--%>
                                        >
                                            <input type="checkbox"  ng-model="column.visible" ng-disabled="disable_labels($index)"  />

                                            <span>{{column.title}}</span>
                                        </label>
                                    </div>
                                    <div>
                                        <isteven-multi-select
                                                directive-id="filter_branch"
                                                input-model="isteven_branches[0].inPut"
                                                output-model="isteven_branches[0].outPut"
                                                button-label="name"
                                                item-label="name"
                                                max-labels="1"
                                                max-height="auto"
                                                min-search-length="1"
                                                output-properties="id"
                                                translation="isteven_branches[0].localeFilter"
                                                on-item-click="tableParams.sorting(columns[0].field, tableParams.isSortBy(columns[0].field, 'asc') ? 'desc' : 'asc')"
                                                tick-property="ticked" >
                                        </isteven-multi-select>
                                    </div>
                                    <div class="table_parent_div">
                                        <table ng-table="tableParams"  show-filter="true" class="table table_br_csh  table-bordered table-striped listItems">
                                            <div class="form-group pull-right">
                                                <input type="text" class="form-control breanch_search"
                                                       ng-model="serch_val"
                                                       placeholder="<s:text  name="page.login.bracnh.search"> Search for...</s:text>">
                                                <buutton class="btn btn-default branch_search_button" ng-click=tableParams.filter(serch_val)>
                                                    <s:text name="page.branches.main.list.table.go">GO</s:text>
                                                </buutton>
                                            </div>
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th ng-repeat="column in columns" ng-show="column.visible"
                                                    ng-bind-html="column.title"
                                                    class="text-center sortable"  ng-class="{
                                            'sort-asc': tableParams.isSortBy(column.field, 'asc'),
                                            'sort-desc': tableParams.isSortBy(column.field, 'desc')
                                          }" style="vertical-align: top; text-align: center; min-width: 90px;"
                                                    ng-click="tableParams.sorting(column.field, tableParams.isSortBy(column.field, 'asc') ? 'desc' : 'asc')">

                                                </th>
                                                <th>Editing</th>

                                            </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="item in $data" ng-class-even="'tbl-row-even'"
                                                    ng-class="{'tbl_row_even': $even, 'tbl_row_odd': $odd }">
                                                    <td>
                                                        <input type="checkbox" name="id" />
                                                    </td>
                                                    <td ng-show="columns[0].visible" sortable="columns[0].field">
                                                        {{item.name ? item.name : '-'}}
                                                    </td>
                                                    <td ng-show="columns[1].visible" sortable="columns[1].field">
                                                        {{item.surname ? item.surname : '-'}}
                                                    </td>
                                                    <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                        {{item.email ? item.email : '-'}}
                                                    </td>
                                                    <td ng-show="columns[3].visible" sortable="columns[2].field">
                                                        {{item.phoneCode ? item.phoneCode : '-'}}&nbsp;{{item.phone ? item.phone : '-'}}
                                                    </td>
                                                    <td ng-show="columns[4].visible" sortable="columns[2].field">
                                                        {{item.registeredAt ? item.registeredAt : '-'}}
                                                    </td>
                                                    <td ng-show="columns[5].visible" sortable="columns[2].field">
                                                        {{item.activatedAt ? item.activatedAt : '-'}}
                                                    </td>
                                                    <td ng-show="columns[5].visible" sortable="columns[2].field">
                                                        {{item.privilege ? item.privilege : '-'}}
                                                    </td>

                                                    <td class="edit_td" ng-click="dropdown_tds($event)">
                                                        <span>EDIT <i class="fa fa-sort-desc" aria-hidden="true"></i></span>
                                                        <ul class="branches_crud_ul">
                                                            <li data-target="#edit"  data-toggle="modal"  ng-click = "viewDetail({{item.id}})">
                                                                <i  class="fa fa_edit fa-pencil-square-o" aria-hidden="true"/>
                                                                <s:text name="page.branches.main.list.table.edit">Edit</s:text>
                                                            </li>
                                                            <li data-target="#detail" data-toggle="modal" ng-click = "viewDetail({{item.id}})">
                                                                <i  class="fa fa_detail fa-info-circle" aria-hidden="true"/>
                                                                <s:text name="page.branches.main.list.table.details">Details</s:text>
                                                            </li>
                                                            <li data-target="#upload" data-toggle="modal" ng-click = "viewDetail({{item.id}})">
                                                                <i class="fa fa_upload fa-upload" aria-hidden="true"/>
                                                                <s:text name="page.branches.main.list.table.datas">Datas</s:text>
                                                            </li>
                                                            <li data-target="#delete" data-toggle="modal" ng-click = "viewDetail({{item.id}})">
                                                                <i class="fa  fa_delete fa-trash-o" aria-hidden="true"/>
                                                                <s:text name="page.branches.main.list.table.delete">Delete</s:text>
                                                            </li>
                                                        </ul>
                                                    </td>


                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'  />
                                    <input type="hidden" ng-model="actionPath" ng-init="actionPath='cashier-list.htm'"/>
                                    <input type="hidden" ng-model="actionPathLoad" ng-init="actionPathLoad='cashier-view.htm'"/>
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
