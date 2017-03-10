<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html  lang = en  ng-app="mainApp" >

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Merchant </title>


    <link href="<%=request.getContextPath()%>/libs/css/bootstrap.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<%=request.getContextPath()%>/libs/css/font-awesome.min.css" type="text/css" rel="stylesheet">
    <!-- jQuery custom content scroller -->
    <link href="<%=request.getContextPath()%>/css/template/bootstrap-template.css" rel="stylesheet">
    <%--isteven multi select --%>
    <link href="<%=request.getContextPath()%>/libs/css/isteven-multi-select.css" rel="stylesheet"/>
    <!-- Custom Theme Style -->
    <link href="<%=request.getContextPath()%>/css/template/custom.css" rel="stylesheet">
    <!-- Custom Style -->
    <link href="<%=request.getContextPath()%>/css/template/branches.css" rel="stylesheet">


    <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/jquery/jquery-2.2.4.js" ></script>
    <!-- jQuery ui -->
    <script  type="text/javascript" src="<%=request.getContextPath()%>/libs/js/jquery/jquery-ui-1.11.4.js"></script>
    <%--angular  js--%>
    <script   type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/angular.js"></script>
    <%--angular animate min js--%>
    <%--<script src="<%=request.getContextPath()%>/libs/js/angular/angular-animate.js" type="text/javascript" ></script>--%>
    <%--isteven-multi-select--%>
    <script src="<%=request.getContextPath()%>/libs/js/angular/isteven-multi-select.js" type="text/javascript" ></script>
    <!-- Bootstrap -->
    <script   type="text/javascript" src="<%=request.getContextPath()%>/libs/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/angular/ui-bootstrap.js"></script>
    <!-- ng-droplet -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/libs/js/uploader/ng-droplet.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/general/modal.js"></script>
    
    <script src="<%=request.getContextPath()%>/js/cashier/main.js" type="text/javascript" ></script>




    <style>
        .loader{
            width: 150px;
            height: 150px;
            background-image: url("./img/general/loader.gif");
            background-size: contain;
            background-repeat: no-repeat;
            position: absolute;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            z-index: 1101;
            /*display: none;*/
        }
    </style>

    <script type="text/javascript">
        context = '<%=request.getContextPath()%>';
        localeFilter = {//default-label is deprecated and replaced with this.
            selectAll       : '<s:text name="label.selectAll">Select All</s:text>',
            selectNone      : '<s:text name="label.selectNone">Select None</s:text>',
            reset           : '<s:text name="label.reset">Reset</s:text>',
            search          : '<s:text name="label.search">Search</s:text>',
            nothingSelected : '<s:text name="label.nothingSelected">Nothing Selected</s:text>'
        }
    </script>

</head>

<body class="nav-md footer_fixed" >
<div class="container body">
    <div class="main_container" ng-controller ="mainLoginCtrl as ctrl">

        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="content"/>
        <tiles:insertAttribute name="footer"/>

        <tiles:insertAttribute name="add"/>
        <tiles:insertAttribute name="detail"/>
        <tiles:insertAttribute name="edit"/>
        <tiles:insertAttribute name="upload"/>
        <tiles:insertAttribute name="message"/>

        <div class="loader" ng-show="loadergif">

        </div>
    </div>
</div>

<!-- FastClick -->
<script  type="text/javascript" src="<%=request.getContextPath()%>/js/template/fastclick.js"></script>
<!-- NProgress -->
<script  type="text/javascript" src="<%=request.getContextPath()%>/js/template/nprogress.js"></script>
<!-- jQuery custom content scroller -->
<script  type="text/javascript" src="<%=request.getContextPath()%>/js/template/jquery-mousewell.js"></script>

<!-- Custom Theme Scripts -->
<script  src="<%=request.getContextPath()%>/js/template/custom.js"  type="text/javascript"></script>

</body>
</html>

