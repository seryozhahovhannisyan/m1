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
<script src="<%=request.getContextPath()%>/js/branches/branch.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/provider/provider.js"></script>


<script type="text/javascript">

    $(document).ready(function () {
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
                        Provided to cashier <s:property value="#session.cashier.name"/>
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
                            <div data-id='<s:property value="#session.cashier.id"/>' class="add_div_brch_parent"
                                 ng-click="takeBackFromCashier($event)">
                                <div class="delete_div_brch"></div>
                                <div>Take Back</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2><s:property value="#session.cashier.name"/>
                                        <s:property value="#session.cashier.surname"/>
                                        <small><s:property value="#session.cashier.email"/>&nbsp;</small>
                                    </h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>

                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <ul class="list-unstyled timeline">

                                        <li>
                                            <div class="block">
                                                <div class="tags">
                                                    <a href="" class="tag">
                                                        <span>Cash Box</span>
                                                    </a>
                                                </div>
                                                <div class="block_content">
                                                    <h2 class="title">
                                                        <span>Cash Box Got From&nbsp;<s:property value="#session.cashier.company.name"/></span>
                                                    </h2>
                                                    <div class="excerpt">balanceProvidedByCompany &nbsp;<s:property value="currentCashBox.balanceProvidedByBranch"/></div>
                                                    <div class="excerpt">balanceCurrent&nbsp;<s:property value="currentCashBox.balanceCurrent"/></div>
                                                    <div class="excerpt">balanceTotalProvidedForCashierCashBox&nbsp;<s:property value="currentCashBox.balanceTotalProvidedForCashierCashBox"/></div>
                                                    <div class="excerpt">balanceCurrentProvidedForCashierCashBox&nbsp;<s:property value="currentCashBox.balanceCurrentProvidedForCashierCashBox"/></div>
                                                    <div class="excerpt">balanceReturnedFromCashierCashBox&nbsp;<s:property value="currentCashBox.balanceReturnedFromCashierCashBox"/></div>
                                                    <div class="excerpt">balanceGatheredTax&nbsp;<s:property value="currentCashBox.balanceGatheredTax"/></div>
                                                    <div class="excerpt">currencyType&nbsp;<s:property value="currentCashBox.currencyType"/></div>
                                                    <div class="excerpt">openedAt&nbsp;<s:property value="currentCashBox.openedAt"/></div>
                                                    <div class="excerpt">closedAt&nbsp;<s:property value="currentCashBox.closedAt"/></div>
                                                    <div class="excerpt">status&nbsp;<s:property value="currentCashBox.status"/></div>
                                                </div>
                                            </div>
                                        </li>
                                        <s:if test="%{currentCashBox.currentBranchCashBoxProvider != null}">
                                            <li>
                                                <div class="block">
                                                    <div class="tags">
                                                        <a href="" class="tag">
                                                            <span>currentBranchCashBoxProvider</span>
                                                        </a>
                                                    </div>
                                                    <div class="block_content">
                                                        <h2 class="title">
                                                            <span>balanceProvided&nbsp;<s:property value="currentCashBox.currentBranchCashBoxProvider.balanceProvided"/></span>
                                                            <span>balanceTaken&nbsp;<s:property value="currentCashBox.currentBranchCashBoxProvider.balanceTaken"/></span>
                                                            <span>currencyType&nbsp;<s:property value="currentCashBox.currentBranchCashBoxProvider.currencyType"/></span>
                                                            <span>providedAt&nbsp;<s:property value="currentCashBox.currentBranchCashBoxProvider.providedAt"/></span>
                                                            <span>isTaken&nbsp;<s:property value="currentCashBox.currentBranchCashBoxProvider.isTaken"/></span>

                                                        </h2>
                                                        <div class="byline">
                                                            <span>Takers</span>
                                                        </div>
                                                        <s:if test="%{currentCashBox.currentBranchCashBoxProvider.cashBoxTakers}">
                                                            <s:iterator value="currentCashBox.currentBranchCashBoxProvider.cashBoxTakers" var="cashBoxTaker">
                                                                <div class="excerpt">balanceTaken&nbsp;<s:property value="#cashBoxTaker.balanceTaken"/></div>
                                                                <div class="excerpt">currencyType&nbsp;<s:property value="#cashBoxTaker.currencyType"/></div>
                                                                <div class="excerpt">tookAt&nbsp;<s:property value="#cashBoxTaker.tookAt"/></div>
                                                                <hr/>
                                                            </s:iterator>
                                                        </s:if>

                                                    </div>
                                                </div>
                                            </li>
                                        </s:if>
                                    </ul>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
