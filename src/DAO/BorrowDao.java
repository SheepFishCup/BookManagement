package DAO;

import Bean.BorrowItem;
import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao {
    DBUtil db = new DBUtil();
    Connection conn = db.getConn();
    // 添加借阅项
    public void addBorrowItem(BorrowItem borrowItem) throws SQLException {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO borrow(name, title, isbn, borrowDate, bookState) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, borrowItem.getName());
            statement.setString(2, borrowItem.getTitle());
            statement.setString(3, borrowItem.getIsbn());
            statement.setString(4, borrowItem.getBorrowDate());
            statement.setString(5, borrowItem.getBorrowState());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    // 更新借阅项
    public void updateBorrowItem(BorrowItem borrowItem) throws SQLException {
        PreparedStatement statement = null;

        try {
            String query = "UPDATE borrow SET name = ?, title = ?, isbn = ?, borrowDate = ?, bookState = ? WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, borrowItem.getName());
            statement.setString(2, borrowItem.getTitle());
            statement.setString(3, borrowItem.getIsbn());
            statement.setString(4, borrowItem.getBorrowDate());
            statement.setString(5, borrowItem.getBorrowState());

            statement.setInt(6, borrowItem.getId());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    // 删除借阅项
    public void deleteBorrowItem(int id) throws SQLException {
        PreparedStatement statement = null;

        try {
            String query = "DELETE FROM borrow WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    //获得列表
    public List<BorrowItem> getBorrowItems() throws SQLException {
        List<BorrowItem> borrowItems = new ArrayList<>();
        try(Connection conn = db.getConn();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM borrow");
            ResultSet resultSet = statement.executeQuery()) {
            borrowItems.clear();
            while (resultSet.next()) {
                BorrowItem item = new BorrowItem(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("title"),
                        resultSet.getString("isbn"),
                        resultSet.getString("borrowDate"),
                        resultSet.getString("bookState")
                );
                borrowItems.add(item);
            }
        } catch (SQLException e) {
            // 异常处理
            e.printStackTrace();
            // 可以选择抛出异常或者采取其他处理方式
        }
        return borrowItems;
    }
}
