<%@ page import="Bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="Servlet.BookServlet" %>
<%--
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
    <title>manage</title>
    <link rel="stylesheet" href="css/manage.css">
    <script src="js/jquery-3.4.1.js"></script>

</head>
<style>

</style>
<%
    List<Book> books = (List<Book>)session.getAttribute("books");
%>
<body>
<div class="container">
    <form action="BookServlet" method="post">
    <div class="search-container">
        <label>
            <input type="text" id="searchInput" class="admin-input" placeholder="请输入书名搜索">
        </label>
        <button id="searchButton" type="button" class="searchButton" onclick="search()">搜索</button>
        <button id="addBookBtn" type="button" >新增</button>
    </div>
    <div class="table-container">
    <table class="admin-table" id="bookTable">
        <thead>
        <tr>
            <th>图书ID</th>
            <th>书名</th>
            <th>作者</th>
            <th>ISBN</th>
            <th>出版社</th>
            <th>出版日期</th>
            <th>库存数量</th>
            <th>类型</th>
            <th>简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="bookTableBody">
        <% if (books != null) { %>
        <% for (Book book : books) { %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getPublisher() %></td>
            <td><%= book.getPublishDate() %></td>
            <td><%= book.getStock() %></td>
            <td><%= book.getType() %></td>
            <td><%= book.getDescription() %></td>
            <td><button class="editButton">修改</button>
                <button class="deleteButton">删除</button>
            </td>
        </tr>
        <% } %>
        <% } %>
        </tbody>

    </table>
    </div>
    <div id="pagination" class="admin-box"></div>
    <!-- HTML结构中添加一个隐藏的模态框 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="bookForm">
                <div class="container1">
                    <label class="text-label">图书ID:</label>
                    <input type="text" id="bookID" name="bookID" required><br>
                    <label class="text-label">书名:</label>
                    <input type="text" id="bookName" name="bookName" required><br>
                    <label class="text-label">作者:</label>
                    <input type="text" id="bookwriter" name="bookwriter" required><br>
                    <label class="text-label">ISBN:</label>
                    <input type="text" id="bookisbn" name="bookisbn" required><br>
                    <label class="text-label">出版社:</label>
                    <input type="text" id="bookpublish" name="bookpublish" required><br>
                </div>
                <div class="container2">
                    <label class="text-label">出版日期:</label>
                    <input type="text" id="bookdate" name="bookdate" required><br>
                    <label class="text-label">库存数量:</label>
                    <input type="text" id="bookNum" name="bookNum" required><br>
                    <label class="text-label">类型:</label>
                    <input type="text" id="booktype" name="booktype" required><br>
                    <label class="text-label">简介:</label>
                    <input type="text" id="bookintro" name="bookintro" required><br>
                    <button type="submit" id="submitBookInfo" class="editsubmit">提交</button>
                </div>
                <input type="hidden" name="action" id="action" value="">
            </form>
        </div>
    </div>
    </form>
</div>
<script>
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("addBookBtn");
    var span = document.getElementsByClassName("close")[0];
    var submitButton = document.getElementById("submitBookInfo");
    var bookForm = document.getElementById("bookForm");
    var isEditing = false;

    document.getElementById("searchButton").addEventListener("click", function() {
        var searchInput = document.getElementById("searchInput").value;
        document.getElementById("action").value = "search";
        search(searchInput);
    });
    function search(searchInput){
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "BookServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                location.reload();
            }
        };
        xhr.send("action=search&searchInput=" + searchInput); // 发送书籍 ID 到后端
    }

    document.querySelectorAll(".editButton").forEach(function(button) {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            var row = this.parentNode.parentNode; // 获取包含编辑按钮的行
            cells = row.getElementsByTagName("td");
            // 获取该行中各列的内容
            document.getElementById("bookID").value = cells[0].innerHTML;
            document.getElementById("bookName").value = cells[1].innerHTML;
            document.getElementById("bookwriter").value = cells[2].innerHTML;
            document.getElementById("bookisbn").value = cells[3].innerHTML;
            document.getElementById("bookpublish").value = cells[4].innerHTML;
            document.getElementById("bookdate").value = cells[5].innerHTML;
            document.getElementById("bookNum").value = cells[6].innerHTML;
            document.getElementById("booktype").value = cells[7].innerHTML;
            document.getElementById("bookintro").value = cells[8].innerHTML;
            isEditing=true;
            document.getElementById("action").value = "update";
            modal.style.display = "block";
        });
    });
    document.querySelectorAll(".deleteButton").forEach(function(button) {
        button.addEventListener("click", function() {
            if (confirm("确定要删除这本书吗？")) {
                var row = this.parentNode.parentNode;
                var bookID = row.cells[0].innerHTML; // 获取要删除的书籍的 ID
                document.getElementById("action").value = "delete";
                deleteBook(bookID); // 调用函数发送删除请求
            }
        });
    });

    function deleteBook(bookID) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "BookServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                location.reload();
            }
        };
        xhr.send("action=delete&bookID=" + bookID); // 发送书籍 ID 到后端
    }

    btn.addEventListener("click", function() {
        document.getElementById("action").value = "add";
        modal.style.display = "block";
    });

    span.addEventListener("click", function() {
        modal.style.display = "none";
    });

    bookForm.addEventListener("submit", function(event) {
        event.preventDefault();
        if (isEditing){
            // 更新所选行中各列的内容
            document.getElementById("bookID").value = cells[0].innerHTML;
            document.getElementById("bookName").value = cells[1].innerHTML;
            document.getElementById("bookwriter").value = cells[2].innerHTML;
            document.getElementById("bookisbn").value = cells[3].innerHTML;
            document.getElementById("bookpublish").value = cells[4].innerHTML;
            document.getElementById("bookdate").value = cells[5].innerHTML;
            document.getElementById("bookNum").value = cells[6].innerHTML;
            document.getElementById("booktype").value = cells[7].innerHTML;
            document.getElementById("bookintro").value = cells[8].innerHTML;

            isEditing=false;
            document.getElementById("bookForm").reset();
            modal.style.display = "none";
            // 关闭模态框
        }else {
            // 获取表单中的数据并处理
            var bookID = document.getElementById("bookID").value;
            var bookName = document.getElementById("bookName").value;
            var bookwriter = document.getElementById("bookwriter").value;
            var bookisbn = document.getElementById("bookisbn").value;
            var bookpublish = document.getElementById("bookpublish").value;
            var bookdate = document.getElementById("bookdate").value;
            var bookNum = document.getElementById("bookNum").value;
            var booktype = document.getElementById("booktype").value;
            var bookintro = document.getElementById("bookintro").value;
            // 进行数据验证和处理
            if (bookID === "" || bookName === "") {
                alert("请填写图书ID和书名");
                return;
            }
            var tableBody = document.getElementById("bookTableBody");
            var newRow = tableBody.insertRow();
            var bookInfoArray = [bookID, bookName, bookwriter, bookisbn, bookpublish, bookdate, bookNum, booktype, bookintro]
            var flag = 1;
            for (var i = 0; i < Object.values(bookInfoArray).length; i++) {
                if (bookInfoArray[i] === "") {
                    flag = 0;
                    break;
                }
            }

            document.getElementById("bookForm").reset();
            // 关闭模态框
            modal.style.display = "none";

        }
    });


</script>
</body>
</html>
