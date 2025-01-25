package DAO;

import Bean.AdminItem;
import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    DBUtil db = new DBUtil();
    Connection conn = db.getConn();
    //查询账号是否存在
    public boolean getAdminByusername(String username) throws SQLException {
        Connection conn = db.getConn();
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
    //查询账号密码是否符合要求
    public boolean Login_verify(String username,String password) throws Exception {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    preparedStatement.close();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //获得符合账号密码的对象
    public AdminItem getAdminInfo(String username, String password) throws Exception {
        // TODO Auto-generated method stub
        AdminItem adminItem = new AdminItem();
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                adminItem.setUserId(resultSet.getInt("userID"));
                adminItem.setName(resultSet.getString("name"));
                adminItem.setUsername(resultSet.getString("username"));
                adminItem.setPassword(resultSet.getString("password"));
                adminItem.setEmail(resultSet.getString("email"));
                adminItem.setRole(resultSet.getString("role"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminItem;
    }
    //更新密码
    public boolean updatePassword(int id, String newPassword) {
        try {
            String sql = "UPDATE users SET password = ? WHERE userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();

            db.closeConn(null, pstmt, conn);
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //更新密码
    public boolean updatePassword(String username, String newPassword) {
        try {
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);

            int rowsUpdated = pstmt.executeUpdate();

            db.closeConn(null, pstmt, conn);
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //更新用户名
    public boolean updateUsername(int id, String newUsername) {
        try {
            String sql = "UPDATE users SET name = ? WHERE userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUsername);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();

            db.closeConn(null, pstmt, conn);
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //增加用户
    public void addAdmin(AdminItem admin) throws SQLException {
        PreparedStatement statement=null;
        try {
            String query = "INSERT INTO users (name, username, password, email, role) VALUES ( ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getUsername());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getRole());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
