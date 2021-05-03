
package com.daos;

import java.sql.*;
import com.db.DbConnection;
import com.beans.Blog;
import java.util.ArrayList;
public class BlogDao {
     public boolean add(Blog blog, String[] categoryIds) {
        boolean status = false;
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "insert into Blog (title,contents,date,bid,posterImage,status,reason) values(?,?,?,?,?,?,?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, blog.getTitle());
            smt.setString(2, blog.getContents());
            smt.setString(3, blog.getDate());
            smt.setInt(4, blog.getBid());
            smt.setString(5,blog.getPosterImage());
            smt.setString(6, blog.getStatus());
            smt.setString(7, blog.getReason());
            if (smt.executeUpdate() > 0) {
                String sql1 = "select id from blog order by id desc limit 1";
                PreparedStatement smt1 = con.prepareStatement(sql1);
                ResultSet rs = smt1.executeQuery(sql1);
                int bid = 0;
                if (rs.next()) {
                    bid = rs.getInt("id");
                }

                for (String cid : categoryIds) {
                    String sql2 = "insert into blogcategory(blogId,catId) values(?,?)";
                    PreparedStatement smt3 = con.prepareStatement(sql2);
                    smt3.setInt(1, bid);
                    smt3.setInt(2, Integer.parseInt(cid));
                    smt3.executeUpdate();
                }
            }
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blog Insertion error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }

        return status;
    }
public Blog getBlogById(int id){
    Blog blog=null;
    Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog where id=?";
       PreparedStatement smt = con.prepareStatement(sql);
       smt.setInt(1, id);
       
       ResultSet rs= smt.executeQuery();
       if(rs.next()){
           blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return blog;
}


public ArrayList<Blog> getAllBlogs(){
    ArrayList<Blog> list = new ArrayList();
      Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog";
       PreparedStatement smt = con.prepareStatement(sql);
       //smt.setInt(1, id);
       
       ResultSet rs= smt.executeQuery();
       while(rs.next()){
           Blog blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
           list.add(blog);
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return list;
}


public  ArrayList<Blog> getBlogsByCategory(int cid){
    ArrayList<Blog> list= new ArrayList();
       Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog where id in (select blogId from blogcategory where catId =?);";
       PreparedStatement smt = con.prepareStatement(sql);
       smt.setInt(1, cid);
       
       ResultSet rs= smt.executeQuery();
       while(rs.next()){
           Blog blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
           list.add(blog);
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return list;
}

public ArrayList<Blog> getBlogByBloggerId(int bid){
    ArrayList<Blog> list= new ArrayList();
      Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog where bid=?";
       PreparedStatement smt = con.prepareStatement(sql);
       smt.setInt(1, bid);
       
       ResultSet rs= smt.executeQuery();
       while(rs.next()){
          Blog blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
           list.add(blog);
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return list;
}

public ArrayList<Blog> getBlogsByStatus(String status){
    ArrayList<Blog> list= new ArrayList();
    Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog where status=?";
       PreparedStatement smt = con.prepareStatement(sql);
       smt.setString(1, status);
       
       ResultSet rs= smt.executeQuery();
       while(rs.next()){
          Blog blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
           list.add(blog);
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return list;
}

public ArrayList<Blog> getRecentBlogs(){
    ArrayList<Blog> list= new ArrayList();
    Connection con = DbConnection.getConnection();
    try{
       String sql = "select * from blog order by id desc limit 5";
       PreparedStatement smt = con.prepareStatement(sql);
             
       ResultSet rs= smt.executeQuery();
       while(rs.next()){
          Blog blog = new Blog();
           blog.setId(rs.getInt("id"));
           blog.setBid(rs.getInt("bid"));
           blog.setTitle(rs.getString("title"));
           blog.setContents(rs.getString("contents"));
           blog.setDate(rs.getString("date"));
           blog.setPosterImage(rs.getString("posterImage"));
           blog.setStatus(rs.getString("status"));
           blog.setReason(rs.getString("reason"));
           list.add(blog);
       }
    }catch(Exception e){
        System.out.println("Blog Selection error " + e.getMessage());
    }
    return list;
}

public int getBlogCountByCategory(int cid){
    int count=0;
    Connection con = DbConnection.getConnection();
     try{
       String sql = "select count(blogId)\"result\" from blogCategory where catId=?";
       PreparedStatement smt = con.prepareStatement(sql);
       smt.setInt(1, cid);
       ResultSet rs = smt.executeQuery();
       if(rs.next()){
           count= rs.getInt("result");      //you can also pass the index 0,1,.. 
       }
       con.close();
       smt.close();
     }catch(Exception e){
         System.out.println("Blog Count error : "+ e.getMessage());
     }
    return count;
}

}
