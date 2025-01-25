package Servlet;

import Bean.AdminItem;
import DAO.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AdminDao userdao = new AdminDao();
    AdminDao admindao = new AdminDao();
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");
        // 检查输入是否为空
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                name == null || name.isEmpty() || email == null || email.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {
            // 如果有任何一个输入为空，可以重定向回注册页面并显示错误消息
            response.sendRedirect("register.jsp?error=emptyFields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            // Passwords don't match, handle this scenario (e.g., show an error message)
            // You can redirect back to the registration page with an error message
            response.sendRedirect("register.jsp?error=passwordMismatch");
            return;
        }
        // Create an AdminBean object with the user input
        AdminItem newAdmin = new AdminItem();
        try {
            newAdmin.setUsername(username);
            newAdmin.setPassword(password);
            newAdmin.setName(name);
            newAdmin.setEmail(email);
            newAdmin.setRole("用户");
            // Add the new admin to the database
            userdao.addAdmin(newAdmin);
            response.sendRedirect("login.jsp");
            // Redirect to login page after successful addition
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during the addition of the new admin
            // You can redirect to an error page or show an error message to the user
            response.sendRedirect("register.jsp?error=registrationFailed");
        }
    }
}
