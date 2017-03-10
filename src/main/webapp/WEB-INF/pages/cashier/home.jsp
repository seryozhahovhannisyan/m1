<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    $(document).keydown(function (e) {
        if (e.which == 27) {
            $(".curent_bal_div").hide();
        }
    });
</script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cashier/wallet_login.css" media="screen">

<!-- page content -->
<div style="min-height: 890px;" class="right_col" role="main">
    <div class="">
        <div class="row">
            <div class="hello_div_parent">
                <s:text name="page.cashier.home.title">Welcome</s:text>
                <div class="name_div">
                    <s:property value="#session.cashier.name"/>&nbsp; <s:property value="#session.cashier.surname"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="user_text_div col-lg-6 col-md-6 col-sm-6 col-xs-8">
                <s:text name="page.cashier.home.content">
                    Welcome to ConnectToMerchant. With the help of this site you can easily turn cash into online money and online money into cash.
                </s:text>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->