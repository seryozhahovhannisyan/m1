<%@ page import="com.connectto.wallet.merchant.web.util.Initializer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <!-- footer content -->
 <%--<footer>
     <div class="pull-right">
         Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com/">Colorlib</a>
     </div>
     <div class="clearfix"></div>
 </footer>--%>
 <link href="<%=request.getContextPath()%>/css/layot/footer.css" rel="stylesheet">
 <style type="text/css">
     /*body { font-family:Arial, Helvetica, Sans-Serif; font-size:0.75em; color:#000;}*/
     .desc {
         color: #6b6b6b;
     }

     .desc a {
         color: #0092dd;
     }

     .dropdown dd, .dropdown dt, .dropdown ul {
         margin: 0px;
         padding: 0px;
     }

     .dropdown dt {
         display: inline-block;
         border: none;
     }

     .dropdown dd {
         position: relative;
     }

     .dropdown a, .dropdown a:visited {
         color: #816c5b;
         text-decoration: none;
         outline: none;
     }

     .dropdown a:hover {
         color: #5d4617;
     }

     .dropdown dt a:hover {
         color: #5d4617;
         border: 1px solid #c9c7c7;
         box-shadow: 10px 10px 5px #888888;
     }

     .dropdown dt a {
         display: block;
         padding-right: 20px;
         width: 150px;
     }

     .dropdown dt a span {
         cursor: pointer;
         display: block;
         padding: 5px;
     }

     .dropdown span.value {
         display: none;
     }

     .dropdown dd ul li a {
         padding: 5px;
         display: block;
     }

     .dropdown dd ul li a:hover {
         background-color: #c9c7c7;
     }

     .dropdown img.flag {
         border: none;
         vertical-align: middle;
         margin-left: 20px;
         width: 30px;
     }
 </style>
<script type="text/javascript">
    $(document).ready(function () {
                $(document).on("touchstart",function(e){

            var $clicked = $(e.target);
            if (! $clicked.parents().hasClass("dropdown"))
                $(".dropdown dd ul").hide();
        });
        var userLang = navigator.language || navigator.userLanguage;
        var l = userLang.split('-')[0];


        $(".dropdown dt a").click(function () {
            $(".dropdown dd ul").toggle();
        });

    });

    $(document).bind('click', function (e) {
        var $clicked = $(e.target);
        if (!$clicked.parents().hasClass("dropdown"))
            $(".dropdown dd ul").hide();
    });


    function select_lang(lang) {
        window.location = "locale.htm?mlang=" + lang;
    }
    function hide_lang() {
        $(".dropdown dd ul").hide();
    }

</script>

 <footer class="main_footer">
     <div class="clear_both text-center col-lg-3 col-md-3 col-sm-3 col-xs-12">
         <div class="icons  ">
             <img style="cursor: pointer" onclick="goTo('https://www.facebook.com/<%=Initializer.getSetupInfo().socialFb%>')"
                  ng-src="<%=request.getContextPath()%>/img/general/footer/fb.png">
             <img style="cursor: pointer" onclick="goTo('https://plus.google.com/<%=Initializer.getSetupInfo().socialGmail%>')"
                  ng-src="<%=request.getContextPath()%>/img/general/footer/gplus.png">
             <img style="cursor: pointer" onclick="goTo('https://twitter.com/<%=Initializer.getSetupInfo().socialTwitter%>')"
                  ng-src="<%=request.getContextPath()%>/img/general/footer/twitter.png">
         </div>
     </div>
     <dl id="sample" class="dropdown ">
         <dt>
             <a href="javascript:void(0)">
                 <s:set var="lang" value="%{getToLang()}"/>
                 <s:property value="#lang.title"/>
                 <span class="value langkey"  data-langkey = <s:property value="#lang.key"/>>

                 </span>
             </a>
         </dt>
         <dd>
             <ul>
                 <s:iterator value="%{getLanguages()}" var="language">
                     <li>
                         <a href="#"
                                 <s:if test="%{#lang.value == #language.value}"> class="current_lang" onclick="hide_lang();return false;"
                                     style=" background-color: #c9c7c7; "
                                 </s:if>
                                 <s:else> onclick="select_lang('<s:property value="#language.key"/>');return false;"</s:else>
                         >
                             <s:property value="#language.title"/>
                         </a>
                     </li>
                 </s:iterator>
             </ul>
         </dd>
     </dl>
     <div class="copyright col-lg-6 col-md-6  col-sm-6  col-xs-12">
         <div>
             <s:text name="footer.Copyright">Copyright &copy; All Rights Reserved.2015</s:text>
         </div>
     </div>
 </footer>

 <!-- /footer content -->