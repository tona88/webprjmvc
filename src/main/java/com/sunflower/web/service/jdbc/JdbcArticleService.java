package com.sunflower.web.service.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sunflower.web.entity.Article;
import com.sunflower.web.entity.ArticleView;
import com.sunflower.web.service.ArticleService;

public class JdbcArticleService implements ArticleService{

    private DataSource dataSource = null;
  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ArticleView> getArticles(int page){
        return getArticles("title","",page);
    }

    public List<ArticleView> getArticles(String field,String keyword,int page){
       
        String query = "select * from ("+
                        "select row_number() over (order by createdAt desc) as num,article_view.* from article_view where "+field+" like ? ) as rna "+
                        "where num between  ? and ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ArticleView> articles = new ArrayList<ArticleView>();
        
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%"+keyword+"%");
            pstmt.setInt(2,1+(page-1)*10);
            pstmt.setInt(3,page*10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articles.add(new ArticleView(
                        rs.getInt("id")
                        , rs.getString("title")
                        ,rs.getString("content")
                        ,rs.getString("writer")
                        , rs.getDate("createdAt")
                    ,rs.getString("files")
                ,rs.getInt("hit")
                ,rs.getBoolean("pub")
                ,rs.getInt("commentCount")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    public List<ArticleView> getPublicArticles(String field, String query, int page) {
        String sql = "select * from ("+
                        "select row_number() over (order by createdAt desc) as num,article_view.* from article_view where "+field+" like ? AND pub = 1 ) as rna "+
                        "where num between  ? and ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ArticleView> articles = new ArrayList<ArticleView>();
       
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+query+"%");
            pstmt.setInt(2,1+(page-1)*10);
            pstmt.setInt(3,page*10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articles.add(new ArticleView(
                        rs.getInt("id")
                        , rs.getString("title")
                        ,rs.getString("content")
                        ,rs.getString("writer")
                        , rs.getDate("createdAt")
                    ,rs.getString("files")
                ,rs.getInt("hit")
                ,rs.getBoolean("pub")
                ,rs.getInt("commentCount")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    public int getArticleCount(){
        return getArticleCount("title","");
    }

    public int getArticleCount(String field,String keyword){
        
        String query = "SELECT count(id) as count FROM article WHERE "+ field + " LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    
        int count = 0;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%"+keyword+"%" );
            rs = pstmt.executeQuery();

            if(rs.next())
                count = rs.getInt("count");
           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public int getPublicArticleCount(String field,String query){
        int count = 0;
        String sql = "select count(id) as count from article where pub=1 and " + field + " Like ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+query+"%" );
            rs = pstmt.executeQuery();

            if(rs.next())
                count = rs.getInt("count");
           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;

    }

    public Article getArticle(int id){

        String query = "SELECT * FROM article WHERE id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Article article = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next())
                article= new Article(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("writer"),
            rs.getDate("createdAt"),
            rs.getString("files")
            ,rs.getInt("hit")
            ,rs.getBoolean("pub"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return article;

    }

    public Article getPrevArticle(int id){
        
        String query = "select * from "+
            "(select row_number() over (order by createdAt ) as num, article.* from article where id > ? ) as art " +
            "where num = 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Article article = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next())
                article= new Article(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("writer"),
            rs.getDate("createdAt"),
            rs.getString("files")
            ,rs.getInt("hit")
            ,rs.getBoolean("pub"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return article;
    }

    public Article getNextArticle(int id){
        String query = "select * from " + 
                        "(select row_number() over (order by createdAt desc) as num, article.* from article where id < ? ) as art " + 
                        " where num = 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Article article = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next())
                article= new Article(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("writer"),
            rs.getDate("createdAt"),
            rs.getString("files")
            ,rs.getInt("hit")
            ,rs.getBoolean("pub"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return article; 
    }

    public int insertArticle(Article article){
        String query = "INSERT INTO article (TITLE,CONTENT,WRITER,PUB,FILES) VALUES (?,?,?,?,?)";

       int result =  0;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getContent());
            stmt.setString(3, article.getWriter());
            stmt.setBoolean(4, article.getPub());
            stmt.setString(5, article.getFiles());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;

    }

    public int deleteArticleAll(int[] ids) {
    
        String param = "";
        for(int i=0; i<ids.length;i++){
            param += ids[i];
            if(i < (ids.length-1)){
                param += ", ";
            }
        }
        String sql= "DELETE FROM article WHERE id IN ( " +param+ " )";
        Connection conn = null;
        Statement stmt = null;
        int result =0;
        

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return result;
    
    }

    public int pubArticleAll(List<String> pids, List<String> cids) {
       String pidCSV = String.join(",",pids);
       String cidCSV = String.join(",",cids);
       return pubArticleAll(pidCSV,cidCSV);
    }
    public int pubArticleAll(int[] pids,int[] cids){
        List<String> pidList= new ArrayList<>();
        for(int i=0; i<pids.length; i++)
            pidList.add(String.valueOf(pids[i]));
        
        List<String> cidList = new ArrayList<>();
        for(int i=0; i<cids.length; i++)
            cidList.add(String.valueOf(pids[i]));

        return pubArticleAll(pidList,cidList);
        
    }
    public int pubArticleAll(String pidCSV,String cidCSV){

        int result = 0;
        String pubSql = String.format("UPDATE article SET pub = 1 WHERE id in ( %s )",pidCSV);
        String cloSql = String.format("UPDATE article SET pub = 0 WHERE id in ( %s )",cidCSV);


        Connection conn = null;
        Statement pubStmt = null;
        Statement cloStmt = null;
        try {
            conn = dataSource.getConnection();
            pubStmt = conn.createStatement();
            result += pubStmt.executeUpdate(pubSql);
            cloStmt = conn.createStatement();
            result += cloStmt.executeUpdate(cloSql);

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pubStmt != null)
                    pubStmt.close();
                if(cloStmt != null)
                    cloStmt.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    
}
