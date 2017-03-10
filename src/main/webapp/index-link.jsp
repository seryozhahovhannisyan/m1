<!DOCTYPE html>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Merchant</title>

    <script type="text/javascript">
        function slide(id) {
            document.getElementById("data").src = document.getElementById(id).src;
        }
        window.onload = function () {
            //window.location = "<%=request.getContextPath()%>/start.htm";
        }
    </script>
</head>
<body >
<table>
    <tr>
        <td>Privilege</td>
        <td>Status</td>
        <td>Img</td>
        <td>View</td>
        <td>Description </td>
    </tr>
    <tr>
        <td>1</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(1)">
                <img id="1"  width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/main_desktop.png"/>
            </a>
        </td>
        <td><a href="start.htm">Start</a> </td>
        <td>When browser opened </td>
    </tr>
    <tr>
        <td>2</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(2)">
                <img id="2" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/first_page.png"/>
            </a>
        </td>
        <td><a href="first_page.htm">first_page</a></td>
        <td>When user loginned or clicke icon  </td>
    </tr>
    <tr>
        <td>3</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(6)">
                <img id="6" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/third_page_role.png"/>
            </a>
        </td>
        <td><a href="third_page_role.htm">third_page_role</a></td>
        <td>third_page_role  </td>
    </tr>
    <tr>
        <td>4</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(7)">
                <img id="7" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/partnerare.png"/>
            </a>
        </td>
        <td><a href="company-area.htm">Company Area</a></td>
        <td>From main page when clicked Company Area  </td>
    </tr>
    <tr>
        <td>5</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(8)">
                <img id="8" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/userare.png"/>
            </a>
        </td>
        <td><a href="company-area.htm">Company Area</a></td>
        <td>From main page when clicked Branch Area  </td>
    </tr>
    <tr>
        <td>6</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(3)">
                <img id="3" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/sec_page.png"/>
            </a>
        </td>
        <td><a href="sec_page.htm">sec_page</a></td>
        <td>Settings roles  </td>
    </tr>
    <tr>
        <td>7</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(4)">
                <img id="4" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/new-role.png"/>
            </a>
        </td>
        <td><a href="new-role.htm">new-role</a></td>
        <td>new-role  </td>
    </tr>
    <tr>
        <td>8</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(5)">
                <img id="5" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/login-forward.png"/>
            </a>
        </td>
        <td><a href="login-forward.htm">Login</a></td>
        <td>new-role  </td>
    </tr>

    <tr>
        <td>9</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(9)">
                <img id="9" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/cashier-Sign-up.png"/>
            </a>
        </td>
        <td><a href="cashier-sign-up.htm">cashier-Sign-up</a></td>
        <td>cashier-Sign-up  </td>
    </tr>
    <tr>
        <td>10</td>
        <td>-</td>
        <td>
            <a href="#" onclick="slide(10)">
                <img id="10" width="150px" height="150" src="<%=request.getContextPath()%>/ui-mock/administrator-Sign-up_branches.png"/>
            </a>
        </td>
        <td><a href="first_page.htm">Branch-Sign-up</a></td>
        <td>Branch-Sign-up  </td>
    </tr>
    <tr>
        <td>11</td>
        <td>-</td>
        <td>

        </td>
        <td><a href="forgot-password.htm">forgot password</a></td>
        <td>Branch-Sign-up  </td>
    </tr>


</table>
<div  >
    <img id="data"  width="80%" height="80%"  />
</div>

</body>
</html>