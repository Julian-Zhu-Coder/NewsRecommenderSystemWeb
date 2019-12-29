package util;

import java.sql.*;

public class DatabaseUtil {
    public DatabaseUtil () {
// 默认的空构造函数
    }
    // 获得数据库的连接
    public static Connection getConnection(){
        Connection conn = null;
        // 数据库连接字符串
        String url = "jdbc:mysql://127.0.0.1:3306/newsrecommendersystem?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "root"); //
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    // 关闭数据库连接
    public static void closeAll(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        if (st != null) {
            try {st.close();} catch (SQLException e) {e.printStackTrace();}
        }
        if (conn != null) {
            try {conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
    public static void main(String[] args) {
        // 测试数据库连接
        Connection conn = DatabaseUtil.getConnection();
        System.out.print(conn);
    }
}

