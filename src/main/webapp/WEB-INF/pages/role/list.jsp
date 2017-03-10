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
<script src="<%=request.getContextPath()%>/libs/js/angular/ng-tags-input.min.js" ></script>
<script src="<%=request.getContextPath()%>/libs/js/angular/ng-table.min.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/js/cashier/main.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath()%>/js/general/object-list.js" type="text/javascript"></script>



<script type="text/javascript"  >
    var contextPath = '<%=request.getContextPath()%>';
    var object = 'role';
    var itemsCount = '<s:property value="dataCount"/>';

    var columns = [
        {title: 'name', field: 'name', visible : true},
        {title: 'description', field: 'description', visible : true},
        {title: 'transactionMin', field: 'transactionMin', visible : true},
        {title: 'transactionMax', field: 'transactionMax', visible : true},
        {title: 'isExchangeAllowed', field: 'isExchangeAllowed', visible : true}
    ];

    var currencies = [];

    <s:iterator value="@com.connectto.wallet.merchant.common.data.wallet.lcp.CurrencyType@values()">
        currencies.push({
            'id': '<s:property value="id"/>',
            'name': '<s:property value="name"/>',
            'code': '<s:property value="code"/>'
        });
    </s:iterator>

    function loadAvailableRates(this_) {
        var availableRateValues = $(this_).attr('data-available_rate_values');
        var currency_ides = availableRateValues.split(",");
        var availableRates = [];
        $.each(currency_ides, function(index, item){
            availableRates.push(find_currency(item));
        })
        console.log('availableRates',availableRates)
    }

    function find_currency(id){
        $.each(currencies, function(index, item){
            if(item.id == id){
                return item;
            }
        });
        return null;
    }

</script>

<div class="right_col" role="main" style="min-height: 2519px;" >
    <div class="clearfix"></div>
    <div class="row"   >
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

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
                            <div ng-controller="listController" id="listContent">
                                <s:if test="%{true}">
                                    <%--<div action="#" method="post">--%>
                                        <div class="btn-group">
                                            <label class="btn btn-primary"
                                                   ng-repeat="column in columns"
                                                   <%--ng-class="column.visible ? 'btn btn-primary ng-scope ng-binding' : 'btn btn-primary ng-scope ng-binding column-unchecked'"--%>
                                            >
                                                <input type="checkbox"  ng-model="column.visible" ng-disabled="disable_labels($index)"  />

                                                <span>{{column.title}}</span>
                                            </label>
                                        </div>

                                    <div class="table_parent_div">
                                        <table ng-table="tableParams" show-filter="true"  class="table table_br_csh table-condensed table-bordered table-striped">
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
                                                    <input type="checkbox" name="id" value="{{item.id}}" />
                                                </td>
                                                <td ng-show="columns[0].visible" sortable="columns[0].field">
                                                    {{item.name ? item.name : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[1].visible" sortable="columns[1].field">
                                                    {{item.description ? item.description : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                    {{item.transactionMin ? item.transactionMin : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                    {{item.transactionMax ? item.transactionMax : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[4].visible" sortable="columns[5].field">
                                                    <a ng-if="item.isExchangeAllowed" href="#" data-id="{{item.id}}" data-available_rate_values="{{item.availableRateValues}}"
                                                       onclick="loadAvailableRates(this)" >View Currencies popup</a>
                                                </td>
                                                <td class="edit_td" ng-click="dropdown_tds($event)" >
                                                    <span>EDIT <i class="fa fa-sort-desc" aria-hidden="true"></i></span>
                                                    <ul class="branches_crud_ul">
                                                        <li data-target="#edit"  data-toggle="modal" ng-click = "edit_row()">
                                                            <i  class="fa fa_edit fa-pencil-square-o" aria-hidden="true"></i>
                                                            <s:text name="page.branches.main.list.table.edit">Edit</s:text>
                                                        </li>
                                                        <li data-target="#detail" data-toggle="modal">
                                                            <i  class="fa fa_detail fa-info-circle" aria-hidden="true"></i>
                                                            <s:text name="page.branches.main.list.table.details">Details</s:text>
                                                        </li>
                                                        <li data-target="#upload" data-toggle="modal"><i
                                                                class="fa fa_upload fa-upload" aria-hidden="true"></i>
                                                            <s:text name="page.branches.main.list.table.datas">Datas</s:text>
                                                        </li>
                                                        <li><i class="fa  fa_delete fa-trash-o" aria-hidden="true"></i>
                                                            <s:text name="page.branches.main.list.table.delete">Delete</s:text>
                                                        </li>
                                                    </ul>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        </div>
                                        <div style="text-align: right;">
                                            <button type="submit" class="btn btn-sm btn-default" style=" margin-top:5px;" value="remove"></button>
                                        </div>
                                        <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'  />
                                        <input type="hidden" ng-model="actionPath" ng-init="actionPath='role-list.htm'"/>
                                    <%--</form>--%>
                                </s:if>
                                <s:else>
                                    <s:text name="page.branches.info.list.data.found">Data not found</s:text>
                                </s:else>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">

                                <table id="datatable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                    <tr >
                                        <th ng-repeat="col in columns">{{col.data}}</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th ng-repeat="col in columns">{{col.data}}</th>
                                    </tr>
                                    </tfoot>
                                    <%--<tbody>
                                        <tr ng-repeat="(indexX,object)  in columns">
                                            <td ng-repeat="col in columns">{{col.data}}- {{indexX}} </td>
                                        </tr>
                                    </tbody>--%>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
