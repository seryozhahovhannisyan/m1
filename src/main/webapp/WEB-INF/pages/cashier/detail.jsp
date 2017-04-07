<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: Serozh--%>
  <%--Date: 7/29/2016--%>
  <%--Time: 4:58 PM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ page contentType = "text/html;charset=UTF-8" language = "java" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<link rel = "stylesheet" href = "<%=request.getContextPath()%>/css/cashier/cashier_detail.css">

<script type = "text/ng-template" id = "detail">


    <div class = "row">
        <div class = "col-md-12 col-sm-12 col-xs-12">

            <div class = "close_popup" ng-if = "open_close_popup">
                <h2>Are you sure you want to close it?</h2>
                <button class = "btn btn-success" ng-click = "ctrl.cancel()">Yes</button>
                <button class = "btn btn-danger" ng-click = "ctrl.no_cancel()">NO</button>
            </div>

            <div class = "modal-header">
                <button type = "button" class = "close" ng-click = "ctrl.open_popup()">
                    <i class = "fa fa-times-circle" aria-hidden = "true"></i>
                </button>

                <button type = "button" ng-show = "ctrl.show_full_lg" class = "close"
                        ng-click = "ctrl.full_screen_large( )">
                    <i class = "fa fa-clone" aria-hidden = "true"></i>
                </button>

                <button type = "button" ng-show = " ctrl.show_full_sm" class = "close"
                        ng-click = "ctrl.full_screen_large( )">
                    <i class = "fa fa-square-o" aria-hidden = "true"></i>
                </button>

                <button type = "button" class = "close" ng-click = "ctrl.minimize()">
                    <i class = "fa fa-minus-square-o" aria-hidden = "true"></i>
                </button>

                <h3 class = "modal-title" id = "modal-title">
                    <s:text name = "page.profile.branch.details.info">Branch details</s:text>
                </h3>
            </div>

            <div class = "modal-body" id = "modal-body" ng-controller = "listController">

                <div class = "x_content">

                    <div>
                        <ul class = "cashier_detail_info">
                            <li ng-repeat = "(key, value) in items" ng-show = " value !=undefined && key !='role' && key!= 'currentCashBox'   ">
                                <h6>{{key}} :</h6>
                                <p title="{{value}}" tooltip>{{value}}</p>

                            </li>
                        </ul>
                        <h2><s:text name="page.branches.role">Role</s:text></h2>
                        <ul class = "cashier_detail_info">
                            <li ng-repeat = "(key, value) in items.role" ng-show = "value !=undefined" >

                                <h6>{{key}} :</h6>
                                <p   title="{{value}}" tooltip >{{value}}

                                </p>
                            </li>
                        </ul>
                        <h2>currentCashBox</h2>
                        <ul class = "cashier_detail_info">
                            <li ng-repeat = "(key, value) in items.currentCashBox" ng-show = "value !=undefined">

                                <h6>{{key}} :</h6>
                                <p title="{{value}}" tooltip>{{value}}</p>
                            </li>
                        </ul>

                    </div>
                    <div style = "clear: both"></div>
                </div>

                <div style = "clear: both"></div>


            </div>
        </div>
        </div>
</script>

<div class = "mini ng-cloak" ng-show = "show_mini">
    <button type = "button" ng-click = "close_from_mini()">
        <i class = "fa fa-times-circle" aria-hidden = "true"></i>
    </button>
    <button type = "button" ng-click = "full_screen()">
        <i class = "fa fa-square-o" aria-hidden = "true"></i>
    </button>
    <div style = "clear: both"></div>
</div>