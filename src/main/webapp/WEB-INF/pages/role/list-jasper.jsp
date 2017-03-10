<%@ page import="com.connectto.wallet.merchant.common.data.merchant.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.connectto.wallet.merchant.common.util.Utils" %>
<%@ page import="com.connectto.wallet.merchant.common.data.wallet.lcp.CurrencyType" %><%--
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

<%
    List<Role> roles = (List<Role>)request.getAttribute("roles");
    int dataCount = 0;
    if(!Utils.isEmpty(roles)){
        dataCount = roles.size();
    }
%>



<script type="text/javascript"  >
    var contextPath = '<%=request.getContextPath()%>';
    var object = 'role';
    var itemsCount = <%=dataCount%>;
    var columns = [
        {title: 'name', field: 'name', visible : true},
        {title: 'description', field: 'description', visible : true},
        {title: 'transactionMin', field: 'transactionMin', visible : true},
        {title: 'transactionMax', field: 'transactionMax', visible : true},
        {title: 'isExchangeAllowed', field: 'isExchangeAllowed', visible : true}
    ];
    var role_rates = [];


    $(document).ready(function() {
        console.log('role_rates',role_rates)
    } );
</script>

<div class="right_col" role="main" style="min-height: 2519px;" ng-app="mainApp">
    <div class="clearfix"></div>
    <div class="row"   >
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Responsive example
                        <small>Roles</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                        Responsive is an extension for DataTables that resolves that problem by optimising the table's
                        layout for different screen sizes through the dynamic insertion and removal of columns from the
                        table.
                    </p>
                    <div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="dataTables_length" id="datatable-responsive ">
                                    <a data-target="#add" data-toggle="modal" class="btn btn-primary" type="button">Add</a>
                                    <%--handle data from table columns--%>
                                    <a data-target="#edit" data-toggle="modal" class="btn btn-primary" type="button">Edit </a>
                                    <a data-target="#detail" data-toggle="modal" class="btn btn-primary" type="button">Detail</a>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div  id="listContent">
                                <s:if test="%{true}">
                                    <form action="#" method="post">
                                        <div class="btn-group">
                                            <label class="btn btn-primary"
                                                   ng-repeat="column in columns"
                                                   ng-class="column.visible ? 'btn btn-primary ng-scope ng-binding' : 'btn btn-primary ng-scope ng-binding column-unchecked'">
                                                <input type="checkbox" ng-model="column.visible" ng-click="alert(column.visible);" />
                                                <span ng-bind-html="column.title"/>
                                            </label>
                                        </div>


                                        <table show-filter="true"  class="table table-condensed table-bordered table-striped">
                                            <div class="form-group pull-right">
                                                <label for="usr"><s:text name="page.login.bracnh.search"> Search:</s:text></label>
                                                <input type="text" class="form-control"   ng-model="serch_val" id="usr">
                                                <buutton class="btn btn-primary" ng-click = search()> GO</buutton>
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
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <%for (Role role : roles){%>
                                            <tr ng-class-even="'tbl-row-even'">
                                                <td>
                                                    <input type="checkbox" name="id" value="<%=role.getId()%>" />
                                                </td>
                                                <td ng-show="columns[0].visible" sortable="columns[0].field">
                                                    <%=role.getName()%>
                                                </td>
                                                <td ng-show="columns[1].visible" sortable="columns[1].field">
                                                    <% if(!Utils.isEmpty(role.getDescription())){%>
                                                        <%=role.getDescription()%>
                                                    <%} else { %>
                                                    -
                                                    <%}%>
                                                </td>
                                                <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                    <%=role.getTransactionMin()%>
                                                </td>
                                                <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                    <%=role.getTransactionMax()%>
                                                </td>
                                                <td ng-show="columns[4].visible" sortable="columns[4].field">
                                                        <script type="text/javascript">

                                                    <% if(role.getIsExchangeAllowed()){
                                                        List<CurrencyType> availableRates = role.parseAvailableRates();
                                                        for (CurrencyType currencyType : availableRates){
                                                    %>
                                                    role_rates.push(<%=role.getId()%>,{
                                                        'id': '<%=currencyType.getId()%>',
                                                        'name': '<%=currencyType.getName()%>',
                                                        'code': '<%=currencyType.getCode()%>'
                                                    });
                                                    <%
                                                        }
                                                    %>
                                                        </script>
                                                        <a href="#<%=role.getId()%>" >View Currencies popup</a>
                                                    <%} else { %>
                                                    -
                                                    <%}%>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </table>
                                        <div style="text-align: right;">
                                            <button type="submit" class="btn btn-sm btn-default" style=" margin-top:5px;" value="remove"></button>
                                        </div>
                                    </form>
                                </s:if>
                                <s:else>
                                    <s:text name="info.list.data.found">Data not found</s:text>
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
