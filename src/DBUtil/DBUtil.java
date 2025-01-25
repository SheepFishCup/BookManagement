package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    //加载JDBC驱动
    private String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //连接服务器和数据库
    private String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=book;sendStringParametersAsUnicode=false";
    //默认用户名，一般为sa
    private String userName = "sa";
    //用户名密码
    private String password = "6500068123";
    //创建驱动
    public Connection getConn() {
        Connection dbConn = null;
        try {
            Class.forName(driverName);
            dbConn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
    //释放资源
    public static void closeConn(ResultSet rs, PreparedStatement pstm, Connection conn) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
