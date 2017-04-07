<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<title>Merchant</title>
<html ng-app="merchantApp">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <style>
        body {
            font-size: 16px !important;
        }

        .loader {
            width: 150px;
            height: 150px;
            background-image: url("<%=request.getContextPath()%>/img/general/loader.gif");
            background-size: contain;
            background-repeat: no-repeat;
            position: fixed;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            z-index: 1101;
            /*display: none;*/
        }
    </style>

    <link rel="icon" href="<%=request.getContextPath()%>/img/main/favicondaf.png?v=2" type="image/x-icon"/>
    <link href="<%=request.getContextPath()%>/libs/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/libs/css/isteven-multi-select.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/libs/css/font-awesome.min.css" type="text/css" rel="stylesheet">


    <%--HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <%--WARNING: Respond.js doesn't work if you view the page via file://--%>
    <%--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <%--[endif]--%>

    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/jquery/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular-animate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/ui-bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/general/main-general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/general/modal.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?hl=<s:property value="getToLang().getKey()"/>' async
            defer></script>
    <script type="text/javascript">
        context = '<%=request.getContextPath()%>';
        function goTo(href) {
            window.open(href, '_blank');
        }
        function goToAction(href) {
            if (context != null && context.length > 1) {
                href = context + href;
            }
            window.location = href;
        }
    </script>

</head>

<body>
<div ng-controller="mainCtrlGeneral as ctrl">
    <div>{{ctrl_fm_main.show}}</div>

    <tiles:insertAttribute name="header-area"/>
    <tiles:insertAttribute name="content"/>
    <tiles:insertAttribute name="footer"/>
    <tiles:insertAttribute name="modal"/>

    <div class="loader" ng-show="loadergif"></div>
</div>
</body>
</html>