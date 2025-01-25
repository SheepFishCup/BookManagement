<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/23
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="css/userform.css"/>
</head>
<script type="text/javascript">
    function checkLoginForm() {
        var username=document.login_form.username.value;
        var password=document.login_form.password.value;
        if (username==""||password==""){
            alert("信息不完整!");
            document.login_form.username.focus();
            return false;
        }
        return true;
    }
</script>
<style>
    html{
        background-image: url("css/img/bookLogin.jpg");
    }
</style>
<body id="userform">
<center>
    <form name="login_form" action="LoginServlet" method="post">
    <h1>用户登录</h1>
    <hr/>
    <table border="1" align="center">
        <tr>
            <td>账号：</td>
            <td><input class="text1" type="text" name="username" id="username" placeholder="请输入您的账号" autofocus="autofocus"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input class="text1" type="password" name="password" id="password" placeholder="请输入您的密码"></td>

        </tr>
        <tr>
            <td colspan="1">
            </td>
            <td>
                <input type="submit" onclick="checkLoginForm()" value="登录"/>

                <input type="reset" value="重置"/>
            </td>
        </tr>
        <tr>
            <td colspan="1">
            <td>
                <a href="search_password.jsp">找回密码</a>
                <a href="register.jsp">注册</a>
            </td>
        </tr>
    </table>
    </form>
</center>
<script>
    // Check if the popup flag is set to 'show'
    var showPopup = '<%= request.getParameter("popup") %>';
    var username=document.login_form.username.value;
    var password=document.login_form.password.value;
    if (username!=""&&password!=""){
        if (showPopup === 'show') {
            // Show the popup here using JavaScript
            alert("该账号不存在，请重新输入或者注册账号");
        }else if (showPopup === 'none') {
            alert("密码错误，请重新输入或者找回密码");
        }
    }
</script>
</body>
</html>


