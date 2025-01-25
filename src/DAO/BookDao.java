package DAO;

import Bean.Book;
import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    DBUtil db = new DBUtil();
    //增加
    public void insertbook(Book book) throws SQLException {
        String query = "INSERT INTO bookItem (title, author, isbn, publisher, publishDate,stock,type,description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = db.getConn();
        try (PreparedStatement statement = conn.prepareStatement(query)){

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getPublishDate());
            statement.setInt(6, book.getStock());
            statement.setString(7, book.getType());
            statement.setString(8, book.getDescription());

            statement.executeUpdate();
            db.closeConn(null, statement, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 更新book信息
    public void updateBook(Book book) throws SQLException {
        Connection conn = db.getConn();
        String query = "UPDATE bookItem SET title = ? ,author= ? ,isbn=? ,publisher=?,publishDate=?,stock=?,type=?,description=? WHERE bookID = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getPublishDate());
            statement.setInt(6, book.getStock());
            statement.setString(7, book.getType());
            statement.setString(8, book.getDescription());

            statement.setInt(9, book.getId());

            statement.executeUpdate();
            db.closeConn(null, statement, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //删除书本记录
    public void deleteBook(int bookID) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = db.getConn();
            String query = "DELETE FROM bookItem WHERE bookID = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, bookID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 按书名查询书籍信息
    public List<Book> getBooksByTitle(String title) throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection conn = db.getConn();
        try {
            String query = "SELECT * FROM bookItem WHERE title LIKE ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + title + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getString("publisher"),
                        resultSet.getString("publishDate"),
                        resultSet.getInt("stock"),
                        resultSet.getString("type"),
                        resultSet.getString("description")
                );
                book.setId(resultSet.getInt("bookID"));
                books.add(book);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return books;
    }
    //获得书籍列表
    public List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try(Connection conn = db.getConn();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bookItem");
            ResultSet resultSet = statement.executeQuery()) {
            books.clear();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getString("publisher"),
                        resultSet.getString("publishDate"),
                        resultSet.getInt("stock"),
                        resultSet.getString("type"),
                        resultSet.getString("description")
                );
                book.setId(resultSet.getInt("bookID"));
                books.add(book);
            }
        } catch (SQLException e) {
            // 异常处理
            e.printStackTrace();
            // 可以选择抛出异常或者采取其他处理方式
        }
        return books;
    }
}
