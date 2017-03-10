<%@ page import="com.connectto.wallet.merchant.web.util.Initializer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<%=request.getContextPath()%>/css/layot/header.css" rel="stylesheet">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cashier/cashier-transaction.css"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/libs/js/datePicker/flipclock.css"/>

<script src="<%=request.getContextPath()%>/static/generated/lcp/TransactionRationalDuration.js"></script>
<script src="<%=request.getContextPath()%>/libs/js/datePicker/flipclock.js"></script>
<script src="<%=request.getContextPath()%>/static/generated/locale/i18n_en.js"></script>
<script src="<%=request.getContextPath()%>/static/generated/locale/i18n_ru.js"></script>
<script src="<%=request.getContextPath()%>/static/generated/locale/i18n_hy.js"></script>
<script src="<%=request.getContextPath()%>/js/wallet/wallet-transaction-notification.js" type="text/javascript" ></script>

<script type="text/javascript">

    var time_to_check_notification = <%=Initializer.NOTIFICATION_DURATION_SECOND%>;

    function cashier_search_wallet(state) {
        window.location = "cashier-search-wallet.htm?state=" + state;
    }
    function transaction() {
        window.location = "cashier-search-wallet.htm" ;
    }




</script>

<!-- top navigation -->
<div class="top_nav">
    <div class="nav_menu">
        <nav class="" role="navigation">
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <div class="header_logo_block" >
                <a href="cashier-home.htm" class="site_title">
                    <img  src="<%=request.getContextPath()%>/img/main/merchant_main_logo.png" alt="<s:text name="alt.main.Logo">Logo</s:text>" />
                </a>
            </div>

            <ul class="nav navbar-nav navbar-right ng-cloak" ng-controller="notificationControllerView">

                <li class="">
                    <a href="cashier-home.htm" class="user-profile dropdown-toggle" data-toggle="dropdown"
                       aria-expanded="false">
                        <s:if test="%{isUserThumbFileExist(#session.session_user.id, #session.session_user.photoData.fileName)}">
                            <img class="user-prof-pic" src="<s:property value='%{getUserThumbImg(#session.session_user.id, #session.session_user.photoData.fileName)}'/>" />
                        </s:if>
                        <s:else>
                            <img class="user-prof-pic" src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt=""/>
                        </s:else>
                        <s:property value="#session.cashier.name"/>&nbsp;<s:property value="#session.cashier.surname"/>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="cashier-profile.htm?id=<s:property value="#session.cashier.id"/>"><s:text name="header.Profile">Profile</s:text></a></li>
                        <li><a href="cashier-settings.htm"><span><s:text name="header.Settings">Settings</s:text></span></a></li>
                        <li><a href="company-settings.htm"><span>Company setting</span></a></li>
                        <li><a href="cashier-help.htm"><s:text name="header.Help">Help</s:text></a></li>
                        <li><a href="cashier-logout.htm"><i class="fa fa-sign-out pull-right"></i><s:text name="header.Sign.Out">Sign Out</s:text></a></li>
                    </ul>
                </li>

                <li role="presentation" onclick="transaction()">
                    <a href="javascript:;"   data-toggle="dropdown" aria-expanded="false">
                        <img  width="70px" height="29px" src="<%=request.getContextPath()%>/img/icon/transaction.png"  />
                    </a>
                </li>




                <li role="presentation" class="dropdown">
                    <a href="javascript:;" ng-click = "transaction_notification('',true)" class="dropdown-toggle info-number" data-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fa fa fa-refresh"></i>
                        <span ng-show = "notification_count > 0" class="badge bg-green">{{notification_count_refresh}}</span>
                    </a>
                </li>






                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown"
                       aria-expanded="false">
                        <i class="fa fa-envelope-o"></i>
                        <span ng-show = "notification_count > 0" class="badge bg-green"> {{notification_count}}</span>
                    </a>
                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                        <li class="notification_list_li" ng-repeat = "item in notific_array" ng-show ="not_list_{{$index}}">
                            <div class="notification_list_div" ng-click = "open_notified_transaction($event, item.withdrawId, $index)" >
                            <span  ng-if = "item.img" class="image"> <img src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt="Profile Image"></span>
                            <span ng-if = "!item.img" class="image"> <img ng-src="{{IMAGE_BASE_URL}}{{item.img}}" alt="Profile Image"></span>
                            <span>{{item.name}} </span>
                            <span>{{item.surname}} </span>
                            <span>{{item.finalState}} </span>
                            </div>
                        </li>
                        <%--todo fill notification--%>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>

</div>
<!-- /top navigation -->
