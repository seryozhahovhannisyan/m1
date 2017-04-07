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
<script src="<%=request.getContextPath()%>/js/provider/provider.js"></script>



<script type="text/javascript">

    $(document).ready(function(){
        console.log('moduls_array', moduls_array)
    })

    var contextPath = '<%=request.getContextPath()%>';
    var object = 'branch';
    var itemsCount = '<s:property value="dataCount"/>';

    var columns = [
        {title: 'name', field: 'name', visible: false},
        {title: 'city', field: 'city', visible: false},
        {title: 'address', field: 'address', visible: false},
        {title: 'zip', field: 'zip', visible: true},
        {title: 'email', field: 'email', visible: true},
        {title: 'phoneCode', field: 'phoneCode', visible: true},
        {title: 'phone', field: 'phone', visible: true},
        {title: 'policyPhoneCode', field: 'policyPhoneCode', visible: true},
        {title: 'policyPhone', field: 'policyPhone', visible: true},
        /*currentCashBox*/
        {title: 'balanceProvidedByCompany', field: '-', visible: true},
        {title: 'balanceCurrent', field: '-', visible: false},
        {title: 'balanceTotalProvidedForCashierCashBox', field: '-', visible: false},
        {title: 'balanceCurrentProvidedForCashierCashBox', field: '-', visible: false},
        {title: 'balanceReturnedFromCashierCashBox', field: '-', visible: false},
        {title: '(Info) balanceGatheredTax', field: '-', visible: false},
        {title: 'currencyType', field: '-', visible: true}
    ];

</script>

<div class="right_col" role="main" style="min-height: 2519px;">

    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2>
                        Provide to branch
                        <s:if test="#session.info != null">
                            INFO ::
                            <s:property value="#session.info"/>
                            <s:set var="info" value="" scope="session"/>
                        </s:if>
                        <s:if test="#session.error != null">
                            ERROR ::
                            <s:property value="#session.error"/>
                            <s:set var="#session.error" value=""/>
                            <s:set var="error" value="" scope="session"/>
                        </s:if>
                    </h2>

                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="datatable-responsive_wrapper"
                         class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                        <div class="row" ng-controller="providerController">
                            <div class="add_div_brch_parent" ng-click="provideStart($event)">
                                <div class="add_div_brch" ></div>
                                <div>Provide</div>
                            </div>
                            <div class="add_div_brch_parent" ng-click="takeBack($event)">
                                <div class="delete_div_brch" ></div>
                                <div>Take Back</div>
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
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="item in $data  "
                                                    ng-class="{'tbl_row_even': $even, 'tbl_row_odd': $odd }" >
                                                    <td>
                                                        <input data-id = "{{item.id}}"  type="checkbox" name="branchId" ng-checked="checked_all"/>
                                                    </td>
                                                    <td>
                                                        <img ng-src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt = "avatar"/>
                                                    </td>
                                                    <td ng-show="columns[0].visible" sortable="columns[0].field">
                                                        {{item.name ? item.name : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[1].visible" sortable="columns[1].field">
                                                        {{item.city ? item.city : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[2].visible" sortable="columns[2].field">
                                                        {{item.address ? item.address : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[3].visible" sortable="columns[3].field">
                                                        {{item.zip ? item.zip : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[4].visible" sortable="columns[4].field">
                                                        {{item.email ? item.email : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[5].visible" sortable="columns[5].field">
                                                        {{item.phoneCode ? item.phoneCode : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[6].visible" sortable="columns[6].field">
                                                        {{item.phone ? item.phone : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[7].visible" sortable="columns[7].field">
                                                        {{item.policyPhoneCode ? item.policyPhoneCode : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[8].visible" sortable="columns[8].field">
                                                        {{item.policyPhone ? item.policyPhone : 'N/A'}}
                                                    </td>

                                                    <%--CurrentCashBox--%>

                                                    <td ng-show="columns[9].visible" sortable="columns[9].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceProvidedByCompany : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[10].visible" sortable="columns[10].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceCurrent : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[11].visible" sortable="columns[11].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceTotalProvidedForCashierCashBox : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[12].visible" sortable="columns[12].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceCurrentProvidedForCashierCashBox : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[13].visible" sortable="columns[13].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceReturnedFromCashierCashBox : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[14].visible" sortable="columns[14].field">
                                                        {{item.currentCashBox ? item.currentCashBox.balanceGatheredTax : 'N/A'}}
                                                    </td>
                                                    <td ng-show="columns[15].visible" sortable="columns[15].field">
                                                        {{item.currentCashBox ? item.currentCashBox.currencyType : 'N/A'}}
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <input type="hidden" ng-model="itemsCount" ng-init='itemsCount=${dataCount}'/>
                                    <input type="hidden" ng-model="actionPath" ng-init="actionPath='provide-branch-list.htm'"/>

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
