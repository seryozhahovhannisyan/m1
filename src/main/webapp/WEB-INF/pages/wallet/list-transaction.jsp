<%@ page import="com.connectto.wallet.merchant.common.data.merchant.lcp.PartitionLCP" %><%--
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
<script src="<%=request.getContextPath()%>/js/wallet/wallet-transaction-pending.js"></script>



<script type="text/javascript">

    var contextPath = '<%=request.getContextPath()%>';
    var object = 'branch';
    var itemsCount = '<s:property value="dataCount"/>';

    var columns = [
        {title: 'partitionId', field: 'partitionId', visible: true},
        {title: 'img', field: 'img', visible: true},
        {title: 'account', field: 'account', visible: true},
        {title: 'orderId', field: 'orderId', visible: true},
        {title: 'finalState', field: 'finalState', visible: true},
        {title: 'name', field: 'name', visible: true},
        {title: 'surname', field: 'surname', visible: true},
        {title: 'email', field: 'email', visible: true},
        {title: 'money', field: 'money', visible: false},
        {title: 'frozenAmount', field: 'frozenAmount', visible: false},
        {title: 'receivingAmount', field: 'receivingAmount', visible: false},
        {title: 'duration', field: 'duration', visible: true}

    ]

</script>

<div class="right_col ng-cloak"  role="main" style="min-height: 2519px;">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2 >
                        transaction-pending-list

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
                                            <input type="checkbox"  ng-model="column.visible" ng-disabled="disable_labels($index)"/>
                                            <span>{{column.title}}</span>
                                        </label>
                                    </div>

                                    <div class="table_parent_div" ng-controller="walletControllerView">
                                        <table ng-table="tableParams"  show-filter="true" class="table table_br_csh table-condensed table-bordered table-striped">

                                            <div class="form-group pull-right">
                                                <input type="text" class="form-control breanch_search" ng-model="serch_val"  placeholder="<s:text  name="page.login.bracnh.search"> Search for...</s:text>"/>
                                                <buutton class="btn btn-default branch_search_button" ng-click=tableParams.filter(serch_val)>
                                                    <s:text name="page.branches.main.list.table.go">GO</s:text>
                                                </buutton>
                                            </div>

                                            <thead>

                                            <tr>
                                                <th ng-repeat="column in columns" ng-show="column.visible"
                                                    class="text-center sortable"
                                                    <%--ng-class="{ 'sort-asc': tableParams.isSortBy(column.field, 'asc'),  'sort-desc': tableParams.isSortBy(column.field, 'desc') }"--%>
                                                    style="vertical-align: top; text-align: center; min-width: 90px;"
                                                    ng-click="tableParams.sorting(column.field, tableParams.isSortBy(column.field, 'asc') ? 'desc' : 'asc')">
                                                    {{column.title}}
                                                </th>
                                                <th  >
                                                    WITH/DEP

                                                </th>


                                            </tr>
                                            </thead>
                                            <tbody >


                                            <tr ng-repeat="item in $data"
                                                ng-class="{'tbl_row_big': row_clases[$index][0], 'tbl_row_low': row_clases[$index][1]}"  >
                                                <td  ng-switch="item.walletDto.partitionId" ng-model="item.walletDto.partitionId" class="partition_picture">
                                                    <img ng-switch-when="<%=PartitionLCP.CONNECT_TO_TV.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_connectto.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.HOLLOR.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_hollor.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_LA.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_YEREVAN.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_USA.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_ARMENIA.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt = "avatar"/>
                                                    <img ng-switch-when="<%=PartitionLCP.VSHOO_ARMENIA_REGIONS.getId()%>" ng-src="<%=request.getContextPath()%>/img/general/logo_vshoo.png" alt = "avatar"/>

                                                </td>
                                                <td>
                                                    <img ng-src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt = "avatar"/>
                                                    {{row_clases[$index].tr_color_green}}
                                                </td>

                                                <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                    {{item.account ? item.account : 'N/A'}} {{arajin.arg}}
                                                </td>

                                                <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                    {{item.orderId ? item.orderId : 'N/A'}}
                                                </td>

                                                <td ng-show="columns[4].visible" sortable="columns[4].field">
                                                    {{item.finalState ? item.finalState : 'N/A'}}
                                                </td>

                                                <td ng-show="columns[5].visible" sortable="columns[5].field">
                                                    {{item.walletDto.name ? item.walletDto.name : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[6].visible" sortable="columns[6].field">
                                                    {{item.walletDto.surname ? item.walletDto.surname : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[7].visible" sortable="columns[7].field">
                                                    {{item.walletDto.email ? item.walletDto.email : 'N/A'}}
                                                </td>
                                                <td ng-show="columns[8].visible" sortable="columns[8].field">
                                                    {{item.money ? item.money : '0'}}
                                                </td>
                                                <td ng-show="columns[9].visible" sortable="columns[9].field">
                                                    {{item.frozenAmount ? item.frozenAmount : '0'}}
                                                </td>
                                                <td ng-show="columns[10].visible" sortable="columns[10].field">
                                                    {{item.receivingAmount ? item.receivingAmount : '0'}}
                                                </td>
                                                <td ng-show="columns[11].visible" sortable="columns[11].field">
                                                    <%--{{item.duration ? item.duration : '0'}}--%>
                                                    <%--{{item.duration+l | date: 'medium' }}--%>
                                                    {{cur_dur[$index] | date: 'HH:mm:ss'}}

                                                </td>

                                                <td class="edit_td"   data-edit_id = {{item.id}}>
                                                    <div ng-class="{'withdraw' :item.withdrawId!= null, 'deposit' : item.withdrawId == null}"  data-allData = "{{$data}}"  data-id = "{{item.id}}" ng-click = "withdrawView($event,$index)"></div>

                                                </td>

                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'/>
                                    <input type="hidden" ng-model="actionPath" ng-init="actionPath='transaction-pending-list.htm'"/>

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
