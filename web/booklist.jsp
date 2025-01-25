<%@ page import="Bean.Book" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/12/26
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>booklist</title>
    <link rel="stylesheet" href="css/booklist.css">

</head>
<style>
    .container{
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        border-radius: 10px;
        border: 1px solid rgba(0, 0, 0, 0.1);
        background-color:white;
    }
    #searchBtn {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .admin-table th {
        background-color: #f2f2f2; /* 设置表头背景颜色 */
    }
</style>
<%
    List<Book> books = (List<Book>)session.getAttribute("books");
%>
<body>
<div class="container">
    <div class="left-section">
        <div class="search-container">
            <label>
                <input type="text" name="searchTitle" id="searchTitle" placeholder="请输入书籍标题" autocomplete="off"
                       class="admin-input">
            </label>
            <button type="button" id="searchBtn" onclick="search()">搜索</button>
        </div>
        <div class="table-container">
            <table class="admin-table" id="bookTable">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>作者</th>
                    <th>ISBN</th>
                    <th>出版社</th>
                    <th>出版日期</th>
                    <th>库存量</th>
                    <th>类型</th>
                    <th>介绍</th>
                </tr>
                </thead>
                <tbody id="bookTableBody">
                <% if (books != null) { %>
                <% for (Book book : books) { %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getIsbn() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td><%= book.getPublishDate() %></td>
                    <td><%= book.getStock() %></td>
                    <td><%= book.getType() %></td>
                    <td><%= book.getDescription() %></td>
                </tr>
                <% } %>
                <% } %>
                </tbody>
            </table>
        </div>

        <div id="pagination" class="admin-box"></div>
    </div>
</div>
<script>
    var searchInput = document.getElementById("searchTitle");
    var searchButton = document.getElementById("searchBtn");
    var table = document.getElementById("bookTable");
    var rows = table.getElementsByTagName("tr");

    searchButton.addEventListener("click", function() {
        var searchText = searchInput.value.trim().toLowerCase();

        for (var i = 1; i < rows.length; i++) { // 从第二行开始搜索，跳过表头
            var title = rows[i].cells[0].textContent.toLowerCase(); // 书名
            if (searchText === "" || title.includes(searchText)) {
                rows[i].style.display = ""; // 显示匹配的行或全部行
            } else {
                rows[i].style.display = "none"; // 隐藏不匹配的行
            }
        }
    });
</script>
</body>
</html>
