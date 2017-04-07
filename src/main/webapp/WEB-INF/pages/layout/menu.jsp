<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">


        <div class="clearfix"></div>

        <!-- menu profile quick info -->
        <div class="profile">
            <div class="profile_pic">

                <s:if test="%{isLogoExist(#session.cashier.logo)}">
                    <img  class="img-circle profile_img" src="<s:property value='%{getLogo(#session.cashier.logo)}'/>" />
                </s:if>
                <s:else>
                    <img  class="img-circle profile_img" src="<%=request.getContextPath()%>/img/general/avatars/avatar.png" alt="avatar"/>
                </s:else>
            </div>
            <div class="profile_info">
                <span><s:text name="page.cashier.home.title">Welcome</s:text>,</span>
                <h2><s:property value="#session.cashier.name"/> <s:property value="#session.cashier.surname"/></h2>
            </div>
        </div>
        <!-- /menu profile quick info  -->

        <br>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section active">
                <h3><s:text name="menu.General">General</s:text>&nbsp;<s:property value="#session.cashier.privilege"/></h3>
                <ul style="" class="nav side-menu">
                    <%--ALL & CRUD_COMPANY menu--%>
                    <s:if test="%{#session.cashier.privilege.id == @com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege@ALL.getId() ||
                                    #session.cashier.privilege.id == @com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege@CRUD_COMPANY.getId()  }">
                        <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="branches.htm">Branches</a></li>
                                <li><a href="cashiers.htm">Cashiers</a></li>
                                <li><a href="roles.htm">Roles</a></li>
                            </ul>
                        </li>
                        <li>
                            <a><i class="fa fa-money"></i> Provider <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="provide.htm">Provide</a></li>
                            </ul>
                        </li>
                    </s:if>
                    <%--CRUD_BRANCH menu--%>
                    <s:elseif test="%{#session.cashier.privilege.id == @com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege@CRUD_BRANCH.getId() }">
                        <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="cashiers.htm">Cashiers</a></li>
                            </ul>
                        </li>
                        <li>
                            <a><i class="fa fa-money"></i> Provider <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="provide-cashiers.htm">Provided by <s:property value="#session.cashier.company.name"/></a></li>
                                <li><a href="provide.htm">Provide to Cashiers</a></li>
                            </ul>
                        </li>
                    </s:elseif>
                    <%--CRUD_CASHIER menu--%>
                    <s:elseif test="%{#session.cashier.privilege.id == @com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege@CRUD_CASHIER.getId()}">
                        <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="cashiers.htm">Cashiers</a></li>
                            </ul>
                        </li>
                        <li>
                            <a><i class="fa fa-money"></i> Provider <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="provide.htm">Provided by Connectto</a></li>
                                <li><a href="branches.htm">Completed transactions</a></li>
                            </ul>
                        </li>
                    </s:elseif>
                    <%--CRUD_CASHIER menu--%>
                    <s:elseif test="%{#session.cashier.privilege.id == @com.connectto.wallet.merchant.common.data.merchant.lcp.Privilege@CASHIER.getId()}">
                        <li>
                            <a><i class="fa fa-money"></i> Provider <span class="fa fa-chevron-down"></span></a>
                            <ul class="nav child_menu">
                                <li><a href="provide.htm">Provided by Connectto</a></li>
                                <li><a href="branches.htm">Completed transactions</a></li>
                            </ul>
                        </li>
                    </s:elseif>



                </ul>
            </div>

        </div>
        <!-- /sidebar menu -->


    </div>
</div>

