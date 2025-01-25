package Servlet;

import Bean.Book;
import DAO.BookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BookDao bookDAO = new BookDao();
    public BookServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "add":
                    addBook(request, response);
                    break;
                case "update":
                    updateBook(request, response);
                    break;
                case "delete":
                    deleteBook(request, response);
                    break;
                case "search":
                    searchBook(request, response);
                    break;
                default:
                    System.out.println("error");
                    break; // 默认情况下重定向到管理页面
            }
        }else {
            System.out.println("无操作");
            return;
        }
    }

    private void searchBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String searchInput =request.getParameter("searchInput");
        HttpSession session = request.getSession();
        try {
            List<Book> books = new ArrayList<>();
            books=bookDAO.getBooksByTitle(searchInput);
            session.setAttribute("books",books);
            response.sendRedirect("manage.jsp");// 操作成功后重定向到管理页面
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //更新
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 类似于添加书籍的逻辑，根据书籍ID从请求中获取书籍信息，然后调用 BookDao 方法进行更新操作
        Book book=new Book();
        book.setId(Integer.parseInt(request.getParameter("bookID")));
        book.setTitle(request.getParameter("bookName"));
        book.setAuthor(request.getParameter("bookwriter"));
        book.setIsbn(request.getParameter("bookisbn"));
        book.setPublisher(request.getParameter("bookpublish"));
        book.setPublishDate(request.getParameter("bookdate"));
        book.setStock(Integer.parseInt(request.getParameter("bookNum")));
        book.setType(request.getParameter("booktype"));
        book.setDescription(request.getParameter("bookintro"));
        HttpSession session = request.getSession();
        try {
            bookDAO.updateBook(book);
            List<Book> books = new ArrayList<>();
            books=bookDAO.getBooks();
            session.setAttribute("books",books);
            response.sendRedirect("manage.jsp");// 操作成功后重定向到管理页面
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中获取书籍信息
        Book book=new Book();
        book.setId(Integer.parseInt(request.getParameter("bookID")));
        book.setTitle(request.getParameter("bookName"));
        book.setAuthor(request.getParameter("bookwriter"));
        book.setIsbn(request.getParameter("bookisbn"));
        book.setPublisher(request.getParameter("bookpublish"));
        book.setPublishDate(request.getParameter("bookdate"));
        book.setStock(Integer.parseInt(request.getParameter("bookNum")));
        book.setType(request.getParameter("booktype"));
        book.setDescription(request.getParameter("bookintro"));
        HttpSession session = request.getSession();
        try {
            // 调用 BookDao 方法将书籍信息插入数据库
            bookDAO.insertbook(book);
            List<Book> books = new ArrayList<>();
            books=bookDAO.getBooks();
            session.setAttribute("books",books);
            response.sendRedirect("manage.jsp"); // 操作成功后重定向到管理页面

        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常情况
        }
    }
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要删除的书籍ID
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        HttpSession session = request.getSession();
        try {
            // 调用 BookDao 方法删除指定ID的书籍
            bookDAO.deleteBook(bookID);
            List<Book> books = new ArrayList<>();
            books=bookDAO.getBooks();
            session.setAttribute("books",books);
            response.sendRedirect("manage.jsp"); // 操作成功后重定向到管理页面
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
