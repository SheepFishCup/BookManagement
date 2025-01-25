package Servlet;

import DAO.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AdminDao userdao = new AdminDao();
    AdminDao admindao = new AdminDao();
    public FindbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String relpassword = request.getParameter("relpassword");
        if (userdao.updatePassword(username,relpassword))
            response.sendRedirect("login.jsp");
        else {
            System.out.println("更新失败");
            response.sendRedirect("search_password.jsp?error=none");
        }
    }
}
