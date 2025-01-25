<%@ page import="Bean.BorrowItem" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>
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
    <title>borrow</title>
    <link rel="stylesheet" href="css/borrow.css">
</head>
<%
    List<BorrowItem> books = (List<BorrowItem>)session.getAttribute("borrowItems");
%>
<body class="admin-body">
<div class="table-container">
    <form action="BorrowServlet" method="post">
    <button id="addBookBtn">增加记录</button>
    <table class="admin-table" id="borrowTable">
        <thead>
        <tr>
            <th>记录ID</th>
            <th>用户名</th>
            <th>书名</th>
            <th>ISBN</th>
            <th>借阅日期</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="bookTableBody">
        <% if (books != null) { %>
        <% for (BorrowItem book : books) { %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getName() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getBorrowDate()%></td>
            <td><%= book.getBorrowState() %></td>
            <td><button class="editButton">修改</button>
                <button class="deleteButton">删除</button>
            </td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
    <!-- HTML结构中添加一个隐藏的模态框 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="bookForm">
                <div class="container1">
                    <label class="text-label">记录ID:</label>
                    <input type="text" id="bookID" name="bookID" required><br>
                    <label class="text-label">用户名:</label>
                    <input type="text" id="username" name="username" required><br>
                    <label class="text-label">书名:</label>
                    <input type="text" id="bookName" name="bookName" required><br>

                </div>
                <div class="container2">
                    <label class="text-label">ISBN:</label>
                    <input type="text" id="bookisbn" name="bookisbn" required><br>
                    <label class="text-label">借阅日期:</label>
                    <input type="text" id="bookdate" name="bookdate" required><br>
                    <label class="text-label">状态:</label>
                    <input type="text" id="bookState" name="bookState" required><br>
                    <button type="submit" id="submitBookInfo" class="editsubmit">提交</button>
                </div>
                <input type="hidden" name="borrow" id="borrow" value="">
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

    document.querySelectorAll(".editButton").forEach(function(button) {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            var row = this.parentNode.parentNode; // 获取包含编辑按钮的行
            cells = row.getElementsByTagName("td");
            // 获取该行中各列的内容
            document.getElementById("bookID").value = cells[0].innerHTML;
            document.getElementById("username").value = cells[1].innerHTML;
            document.getElementById("bookName").value = cells[2].innerHTML;
            document.getElementById("bookisbn").value = cells[3].innerHTML;
            document.getElementById("bookdate").value = cells[4].innerHTML;
            document.getElementById("bookState").value = cells[5].innerHTML;
            isEditing=true;
            document.getElementById("borrow").value = "update";
            modal.style.display = "block";
        });
    });
    document.querySelectorAll(".deleteButton").forEach(function(button) {
        button.addEventListener("click", function() {
            if (confirm("确定要删除这本书吗？")) {
                var row = this.parentNode.parentNode;
                var bookID = row.cells[0].innerHTML; // 获取要删除的书籍的 ID
                document.getElementById("borrow").value = "delete";
                deleteBook(bookID); // 调用函数发送删除请求
            }
        });
    });

    btn.addEventListener("click", function() {
        document.getElementById("borrow").value = "add";
        modal.style.display = "block";
    });

    span.addEventListener("click", function() {
        modal.style.display = "none";
    });

    bookForm.addEventListener("submit", function(event) {
        event.preventDefault();
        if (isEditing){
            // 更新所选行中各列的内容
            cells[0].innerHTML = document.getElementById("bookID").value;
            cells[1].innerHTML = document.getElementById("username").value;
            cells[2].innerHTML = document.getElementById("bookName").value;
            cells[3].innerHTML = document.getElementById("bookisbn").value;
            cells[4].innerHTML = document.getElementById("bookdate").value;
            cells[5].innerHTML = document.getElementById("bookState").value;

            isEditing=false;
            document.getElementById("bookForm").reset();
            // 关闭模态框
            modal.style.display = "none";
        }else {
            // 获取表单中的数据并处理
            var bookID = document.getElementById("bookID").value;
            var username = document.getElementById("username").value;
            var bookName = document.getElementById("bookName").value;
            var bookisbn = document.getElementById("bookisbn").value;
            var bookdate = document.getElementById("bookdate").value;
            var bookState = document.getElementById("bookState").value;
            // 进行数据验证和处理
            if (bookID === "" || bookName === "") {
                alert("请填写图书ID和书名");
                return;
            }
            var tableBody = document.getElementById("bookTableBody");
            var newRow = tableBody.insertRow();
            var bookInfoArray = [bookID, username,bookName, bookisbn, bookdate, bookState]
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
