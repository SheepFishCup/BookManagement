package Servlet;

import Bean.BorrowItem;
import DAO.BorrowDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BorrowDao borrowDao=new BorrowDao();
    public BorrowServlet(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("borrow");
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
                default:
                    break; // 默认情况下重定向到管理页面
            }
        }else {
            System.out.println("无操作");
            return;
        }
    }//更新
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 类似于添加书籍的逻辑，根据书籍ID从请求中获取书籍信息，然后调用 BookDao 方法进行更新操作
        BorrowItem borrowItem=new BorrowItem();
        borrowItem.setId(Integer.parseInt(request.getParameter("bookID")));
        borrowItem.setName(request.getParameter("username"));
        borrowItem.setTitle(request.getParameter("bookName"));
        borrowItem.setIsbn(request.getParameter("bookisbn"));
        borrowItem.setBorrowDate(request.getParameter("bookdate"));
        borrowItem.setBorrowState(request.getParameter("bookState"));
        HttpSession session = request.getSession();
        try {
            borrowDao.updateBorrowItem(borrowItem);
            List<BorrowItem> borrowItems = new ArrayList<>();
            borrowItems=borrowDao.getBorrowItems();
            session.setAttribute("borrowItems",borrowItems);
            response.sendRedirect("borrow.jsp");// 操作成功后重定向到管理页面
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中获取书籍信息
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BorrowItem borrowItem=new BorrowItem();
        borrowItem.setId(Integer.parseInt(request.getParameter("bookID")));
        borrowItem.setName(request.getParameter("username"));
        borrowItem.setTitle(request.getParameter("bookName"));
        borrowItem.setIsbn(request.getParameter("bookisbn"));
        borrowItem.setBorrowDate(request.getParameter("bookdate"));
        borrowItem.setBorrowState(request.getParameter("bookState"));
        HttpSession session = request.getSession();
        try {
            borrowDao.addBorrowItem(borrowItem);
            List<BorrowItem> borrowItems = new ArrayList<>();
            borrowItems=borrowDao.getBorrowItems();
            session.setAttribute("borrowItems",borrowItems);
            response.sendRedirect("borrow.jsp"); // 操作成功后重定向到管理页面
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
            borrowDao.deleteBorrowItem(bookID);
            List<BorrowItem> borrowItems = new ArrayList<>();
            borrowItems=borrowDao.getBorrowItems();
            session.setAttribute("borrowItems",borrowItems);
            response.sendRedirect("borrow.jsp"); // 操作成功后重定向到管理页面
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
