package Servlet;

import Bean.AdminItem;
import Bean.Book;
import Bean.BorrowItem;
import DAO.AdminDao;
import DAO.BookDao;
import DAO.BorrowDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AdminDao userdao = new AdminDao();
    AdminDao admindao = new AdminDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        response.sendRedirect("admin_index.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        AdminItem adminItem =new AdminItem();
        adminItem.setName(username);
        adminItem.setPassword(password);
        HttpSession session = request.getSession();
        // 设置会话属性
        if (username.equals("123456")&&password.equals("123456")){
            session.setAttribute("name", "admin");
            session.setAttribute("user", adminItem);
            response.sendRedirect("admin_index.jsp");
            return;
        }
        if (username==null||password==null)
            return;
        //检验登录
        try {
            if (userdao.Login_verify(username,password)){
                //加载用户
                response.sendRedirect("admin_index.jsp");
                adminItem =userdao.getAdminInfo(username,password);
                session.setAttribute("name", adminItem.getName());
                session.setAttribute("user", adminItem);
                //加载书籍
                BookDao bookDAO = new BookDao();
                List<Book> books =bookDAO.getBooks();
                session.setAttribute("books",books);

                BorrowDao borrowDao=new BorrowDao();
                List<BorrowItem> borrowItems=borrowDao.getBorrowItems();
                session.setAttribute("borrowItems",borrowItems);
                return;
            }else if (userdao.getAdminByusername(username)){
                response.sendRedirect("login.jsp?popup=none");
                return;
            }else {
                response.sendRedirect("login.jsp?popup=show");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
