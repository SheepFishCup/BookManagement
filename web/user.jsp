<%@ page import="Bean.AdminItem" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/26
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/user.css">
    <style>
        /* 模态框样式 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 30%;
        }
    </style>
</head>
<body>
<%
    AdminItem user = (AdminItem) session.getAttribute("user");
    if (user == null) {
        return;
    }
%>
<div class="profile-container">
    <div class="profile-item">
        <label>用户名:</label>
        <span id="username"><%= user.getName() %></span>
        <button class="layui-btn layui-btn-sm" onclick="openEditUsername()">修改姓名</button>
    </div>
    <div class="profile-item">
        <label>邮箱:</label>
        <span id="email"><%= user.getEmail() %></span>
    </div>
    <div class="profile-item">
        <label>身份:</label>
        <span id="role"><%= user.getRole() %></span>
    </div>
    <div class="actions">
        <button class="changebtn" onclick="openEditPassword()">修改密码</button>
    </div>
    <form action="ChangeUserServlet" method="post">
    <!-- 模态框 -->
    <div id="myModal" class="modal">

        <div class="modal-content">
            <span id="closeModal" style="float: right; cursor: pointer;">&times;</span>
            <label for="newUsername">新用户名:</label>
            <input type="text" id="newUsername">
            <button id="saveUsername">保存</button>
        </div>

    </div>
    <!-- 修改密码的模态框 -->
    <div id="passwordModal" class="modal">
        <div class="modal-content">
            <span id="closePasswordModal" style="float: right; cursor: pointer;">&times;</span>
            <label for="newPassword">新密码:</label>
            <input type="password" id="newPassword">
            <button id="savePassword">保存</button>
        </div>
    </div>
        <div>
            <input type="hidden" name="action" id="action" value="">
        </div>
    </form>
</div>
<script>
    function openEditUsername() {
        var modal = document.getElementById("myModal");
        var span = document.getElementById("closeModal");
        var newUsernameInput = document.getElementById("newUsername");
        var usernameSpan = document.getElementById("username");
        modal.style.display = "block";
        newUsernameInput.value = usernameSpan.innerText;
        span.onclick = function() {
            modal.style.display = "none";
        }
        window.onclick = function(event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }
        document.getElementById("saveUsername").onclick = function() {
            var newUsername = newUsernameInput.value;
            usernameSpan.innerText = newUsername;
            document.getElementById("action").value = "update";
            modal.style.display = "none";
        }
    }
    function openEditPassword() {
        var modal = document.getElementById("passwordModal");
        var span = document.getElementById("closePasswordModal");

        modal.style.display = "block";

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }

        document.getElementById("savePassword").onclick = function() {
            var newPassword = document.getElementById("newPassword").value;
            // 在这里添加代码来保存新密码，可以发送到后端进行更新
            alert("密码已更新为：" + newPassword);
            document.getElementById("action").value = "chPassword";
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
