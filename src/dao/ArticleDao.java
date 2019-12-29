package dao;

import util.DatabaseUtil;
import vo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public Article getOneById(int id){
        Article a = new Article();
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pst = null;   ResultSet rs = null;
        String sql = "select * from article where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                a.setId(id);
                a.setDocno(rs.getString("docno"));
                a.setUrl(rs.getString("url"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
            }
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
              }
        finally{
            DatabaseUtil.closeAll(rs, pst, conn);
        }
        return a;
    }
    public List<Article> getList(){
        List<Article> list = new ArrayList<Article>();

        Connection conn = DatabaseUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        // 随机读取前 10 条记录，用 SQL Server 内置的 newid()函数
//        newid()在sqlserver中用于随机排序，在mysql中可用rand()表示。
//        mysql不支持select top n的语法
        String sql = "select * from article order by rand() limit 0,10";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setUrl(rs.getString("url"));
                a.setDocno(rs.getString("docno"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                list.add(a);
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            DatabaseUtil.closeAll(rs, st, conn);
        }
        return list;
    }
}