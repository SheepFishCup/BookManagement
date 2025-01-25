<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/27
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>search_password</title>
    <link rel="stylesheet" href="css/Findback.css">
</head>
<body>
<div class="register_top">
    <h1 style="margin-bottom:0">找回密码</h1>
</div>
<div class="my-body">
    <form name="registerForm" action="FindbackServlet" method="post">
        <table border="1" align="center">
            <tr>
                <td>用户名：</td>
                <td><input class="inputtext" type="text" name="name"
                           id="name" placeholder="请输入您的用户名" autofocus="autofocus"></td>
            </tr>
            <tr>
                <td>账号：</td>
                <td><input class="inputtext" type="text" name="username"
                           id="username" placeholder="请输入您的账号" autofocus="autofocus"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input class="inputtext" type="password" name="password"
                           id="password" placeholder="请输入您的密码"></td>

            </tr>
            <tr>
                <td>确认密码：</td>
                <td><input class="inputtext" type="password" name="relpassword"
                           id="relpassword" placeholder="请确认您的密码"></td>
            </tr>
            <tr>
                <td colspan="1">
                <td>
                    <input type="submit" id="register1" value="确认"></button>
                    <input type="reset" id="reset" value="重置"></button>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="layui-footer">
    <p>欢迎使用图书管理系统！</p>
</div>
<script>
    var showPopup = '<%= request.getParameter("error") %>';
    if (showPopup === 'none') {
        alert("信息不完整!");
    }
</script>
</body>
</html>
