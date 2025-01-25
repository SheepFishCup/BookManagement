<%@ page import="java.util.Objects" %>
<%@ page import="Bean.AdminItem" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/25
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书管理系统</title>
    <link rel="stylesheet" href="css/admin_index.css">
    <style>
        body{
            background-color: #FFF5E1; /* Light peach background */
            margin: 0;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<%
    String name = (String) session.getAttribute("name");
    AdminItem user = (AdminItem) session.getAttribute("user");
%>
<body>
    <div class="admin_top">
        <div class="register_top1">图书管理系统</div>
        <div class="register_top2">
            <ul class="admin_right">
            <% if (user != null) { %>
            <li class="admin-nav-item" id="userItem">
                <a id="username">
                    <%// 从会话中获取属性
                        out.print(name);
                    %>
                </a>
                <dl class="admin-nav-child" id="userMenu">
                    <dd><a>个人信息</a></dd>
                    <dd><a href="login.jsp">注销</a></dd>
                </dl>
            </li>
            <% } else { %>
            <li class="admin-nav-item" id="loginItem">
                <a class="a1" href="login.jsp">登录</a>
            </li>
            <% } %>
            </ul>
        </div>
    </div>
    <div class="admin-container">
    <!-- 左侧菜单区域 -->
    <div class="admin_side">
        <!-- 左侧垂直导航 -->
        <td class="admin-side-scroll">
            <ul class="admin-nav">
                <li class="admin-nav-item1">
                    <a href="javascript:void(0);" onclick="loadPage('booklist.jsp');"class="white-text">图书列表</a>
                </li>
                <li class="admin-nav-item1">
                    <a href="javascript:void(0);" onclick="loadPage('borrow.jsp');"class="white-text">借阅管理</a>
                </li>
                <li class="admin-nav-item1">
                    <a href="javascript:void(0);" onclick="loadPage('user.jsp');"class="white-text">用户管理</a>
                </li>
                <li class="admin-nav-item1">
                    <a href="javascript:void(0);" onclick="loadPage('manage.jsp');"class="white-text">图书管理</a>
                </li>
            </ul>
        </td>
    </div>
    <!-- 页面主体区域 -->
    <div class="admin-body">
        <!-- 内容主体区域 -->
        <iframe src="./booklist.jsp" frameborder="0" name="view_frame" id="view_frame"
                class="my-body"></iframe>
    </div>
    </div>
<script>
        function loadPage(page) {
        document.getElementById('view_frame').src = page;
        }
</script>
</body>
</html>
