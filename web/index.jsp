<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/23
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>图书管理功能</title>
      <style type="text/css">
          h1{
              color: deepskyblue;
              text-align: center;
          }
          body{
              background-color: antiquewhite;
          }
          p{
              text-decoration: none;
              color: black;
              text-align: center;
          }
          button:hover{
              text-decoration: underline;color: red;text-align: center;
          }
      </style>
  </head>
  <body>
  <center>
    <div class="bookindex-without-login">
        <h1 >
            欢迎登录图书馆管理系统!
        </h1>
    </div>
    <div>
        <p>请先登录账号</p>
        <a href="login.jsp">
            <button>登录</button>
        </a>
    </div>
  </center>
  </body>
</html>
