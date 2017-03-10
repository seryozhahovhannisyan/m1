<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP" %>
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
<script src="<%=request.getContextPath()%>/static/generated/lcp/CurrencyType.js"></script>
<%--<script src="<%=request.getContextPath()%>/static/generated/lcp/PartitionLCP.js"></script>--%>
<script src="<%=request.getContextPath()%>/js/wallet/wallet-transaction.js"></script>


<script type="text/javascript">

    var contextPath = '<%=request.getContextPath()%>';
    var object = 'branch';
    var itemsCount = '<s:property value="dataCount"/>';


    var columns = [
        {title: 'partitionId', field: 'partitionId', visible: true},
        {title: 'img', field: 'img', visible: true},
        {title: 'name', field: 'name', visible: true},
        {title: 'surname', field: 'surname', visible: true},
        {title: 'email', field: 'email', visible: true},
        {title: 'money', field: 'money', visible: true},
        {title: 'frozenAmount', field: 'frozenAmount', visible: true},
        {title: 'receivingAmount', field: 'receivingAmount', visible: true},
        {title: 'currencyType', field: 'currencyType', visible: true}
    ]

</script>

<div class="right_col  ng-cloak" role="main" style="min-height: 2519px;">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2>
                        Wallet
                    </h2>


                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="datatable-responsive_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                        <div class="row">

                            <div ng-controller="listController" id="listContent">

                                <s:if test="%{dataCount != 0}">

                                    <div class="btn-group">`

                                        <label class="btn btn-primary"
                                               ng-repeat="column in columns"
                                               ng-class="{'not_checked' :!column.visible }"
                                        >

                                            <input type="checkbox" ng-model="column.visible"
                                                   ng-disabled="disable_labels($index)"/>

                                            <span>{{column.title}}</span>
                                        </label>
                                    </div>

                                    <div class="table_parent_div" ng-controller="walletController">
                                        <table ng-table="tableParams" show-filter="true"
                                               class="table table_br_csh table-condensed table-bordered table-striped">
                                            <div class="form-group pull-right">
                                                <input type="text" class="form-control breanch_search"
                                                       ng-model="serch_val" ng-keypress="($event.which === 13)?tableParams.filter(serch_val):0"
                                                       placeholder="<s:text  name="page.login.bracnh.search"> Search for...</s:text>"/>
                                                <buutton class="btn btn-default branch_search_button"
                                                         ng-click=tableParams.filter(serch_val)>
                                                    <s:text name="page.branches.main.list.table.go">GO</s:text>
                                                </buutton>
                                            </div>
                                            <thead>
                                            <tr>
                                                <th ng-repeat="column in columns" ng-show="column.visible"
                                                    class="text-center sortable"
                                                    ng-class="{ 'sort-asc': tableParams.isSortBy(column.field, 'asc'),  'sort-desc': tableParams.isSortBy(column.field, 'desc') }"
                                                    style="vertical-align: top; text-align: center; min-width: 90px;"
                                                    ng-click="tableParams.sorting(column.field, tableParams.isSortBy(column.field, 'asc') ? 'desc' : 'asc')">
                                                    {{column.title}}
                                                </th>
                                                <th>WITHDRAW</th>
                                                <th>DEPOSIT</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <tr ng-repeat="item in $data  "
                                                ng-class="{'tbl_row_even': $even, 'tbl_row_odd': $odd, 'tbl_row_low': item.isLocked }">
                                                <td ng-switch="item.partitionId" ng-model="item.partitionId">
                                                    <img ng-switch-when="<%=PartitionLCP.CONNECT_TO_TV.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_connectto.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.HOLLOR.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_hollor.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_LA.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_YEREVAN.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_USA.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_ARMENIA.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png"
                                                         alt="avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_ARMENIA_REGIONS.getId()%>"
                                                         ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png"
                                                         alt="avatar"/>
                                                </td>
                                                <td>
                                                    <img ng-src="<%=request.getContextPath()%>/img/general/avatars/avatar.png"
                                                         alt="avatar"/>
                                                </td>
                                                <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                    {{item.name ? item.name : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                    {{item.surname ? item.surname : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[4].visible" sortable="columns[4].field">
                                                    {{item.email ? item.email : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[5].visible" sortable="columns[5].field">
                                                    {{item.money ? item.money : '0'}}
                                                </td>
                                                <td ng-show="columns[6].visible" sortable="columns[6].field">
                                                    {{item.frozenAmount ? item.frozenAmount : '0'}}
                                                </td>
                                                <td ng-show="columns[7].visible" sortable="columns[7].field">
                                                    {{item.receivingAmount ? item.receivingAmount : '0'}}
                                                </td>
                                                <td ng-show="columns[8].visible" sortable="columns[8].field">
                                                    {{item.currencyType ? item.currencyType : 'N/A'}}
                                                </td>

                                                <td class="edit_td"
                                                    data-edit_id={{item.id}}>


                                                    <fieldset class="withdraw "  ng-class="{'tooltipOwn' : item.isLocked}"
                                                              data-id="{{item.id}}"
                                                              ng-click="withdrawDepositStart($event,$index,'withdraw-start.htm')">
                                                        <span class="tooltiptext" ng-show = "toltip_field_error_{{$index}}" ng-bind = "toltip_field_error_{{$index}}"></span>
                                                    </fieldset>

                                                </td>

                                                <td class="edit_td"  
                                                    data-edit_id={{item.id}}>
                                                    <fieldset ng-disabled="item.isLocked"
                                                         class="deposit"
                                                             data-id="{{item.id}}"
                                                             ng-click="withdrawDepositStart($event,$index,'deposit-start.htm')">
                                                    </fieldset>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'/>
                                    <input type="hidden" ng-model="actionPath"
                                           ng-init="actionPath='search-wallet-list.htm'"/>

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
