package dao;

import util.DatabaseUtil;
import vo.Article;
import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User getOneById(int id){
        User user = new User();
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from user where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setYearofbirth(rs.getInt("yearofbirth"));
            }
            // TODO: 2019/12/25 测试article_list是否调用该方法
//            System.out.println(user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DatabaseUtil.closeAll(rs, pst, conn);
        }
        return user;
    }

    public List<User> getList() {
        List<User> list = new ArrayList<User>();

        Connection conn = DatabaseUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        // 随机读取前 10 条记录，用 SQL Server 内置的 newid()函数
//        newid()在sqlserver中用于随机排序，在mysql中可用rand()表示。
        String sql = "select * from user";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setGender(rs.getString("gender"));
                a.setYearofbirth(rs.getInt("yearofbirth"));
                list.add(a);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeAll(rs, st, conn);
        }
        return list;
    }
}
