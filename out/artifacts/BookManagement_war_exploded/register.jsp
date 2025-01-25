<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/25
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="css/register.css" />

</head>
<style>
    body{
        background-color: #FFF5E1; /* Light peach background */
        color: #6B4226; /* Dark brown text */
        margin: 0;
        font-family: Arial, sans-serif;
    }
    button {
        background-color: #ffffff; /* Warm orange button background */
        color: black; /* White text on buttons */
        border: none;
        padding: 5px 25px;
        border-radius: 5px;
    }
    button:hover {
        background-color: #FF7F50; /* Slightly darker orange on hover */
    }
</style>
<body>
    <div class="register_top">
        <h1 style="margin-bottom:0">用户注册</h1>
    </div>
    <div class="my-body">
        <form name="registerForm" action="RegisterServlet" method="post">
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
                    <td><input class="inputtext" type="password" name="confirmPassword"
                               id="confirmPassword" placeholder="请确认您的密码"></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input class="inputtext" type="email" name="email"
                               id="email" placeholder="请输入您的邮箱"></td>
                </tr>
                <tr>
                    <td colspan="1">
                    <td>
                        <input type="submit" id="register1" value="注册"></button>
                        <input type="reset" id="reset" value="重置"></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="admin-footer">
        <p>欢迎使用图书管理系统！</p>
    </div>
</body>
<script>
    // 检查是否存在错误消息并显示弹窗
    window.onload = function() {
        var errorMessage = "<%= request.getParameter("error") %>";
        if(errorMessage) {
            alert(errorMessage);
        }
    };
</script>
</html>

