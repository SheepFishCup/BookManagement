package Servlet;

import Bean.AdminItem;
import DAO.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AdminDao adminDao=new AdminDao();
    public ChangeUserServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "update":
                    updateUsername(request, response);
                    break;
                case "chPassword":
                    updatePassword(request, response);
                    break;
                default:
                    break; // 默认情况下重定向到管理页面
            }
        }else {
            System.out.println("无操作");
            return;
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        AdminItem user = (AdminItem) session.getAttribute("user");
        String newPassword=request.getParameter("newPassword");
        if (adminDao.updatePassword(user.getUserId(),newPassword)){
            session.setAttribute("user", user);
            try {
                response.sendRedirect("user.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("更新失败");
        }
    }

    private void updateUsername(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        AdminItem user = (AdminItem) session.getAttribute("user");
        String newUsername=request.getParameter("newUsername");
        if (adminDao.updateUsername(user.getUserId(),newUsername)){
            session.setAttribute("user", user);
            try {
                response.sendRedirect("user.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("更新失败");
        }
    }
}
