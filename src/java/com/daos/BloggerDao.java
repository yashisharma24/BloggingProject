package com.daos;

import com.db.DbConnection;
import com.beans.Blogger;
import java.sql.*;
import java.util.ArrayList;

public class BloggerDao {

    public boolean add(Blogger blogger, String[] categoryIds) {
        boolean status = false;
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "insert into blogger (name,gender,userid,password,mobile,address,pic)values(?,?,?,?,?,?,?)";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, blogger.getName());
            smt.setString(2, blogger.getGender());
            smt.setString(3, blogger.getUserid());
            smt.setString(4, blogger.getPassword());
            smt.setString(5, blogger.getMobile());
            smt.setString(6, blogger.getAddress());
            smt.setString(7, blogger.getPic());
            if (smt.executeUpdate() > 0) {
                String sql1 = "select id from blogger order by id desc limit 1";
                PreparedStatement smt1 = con.prepareStatement(sql1);
                ResultSet rs = smt1.executeQuery(sql1);
                int bid = 0;
                if (rs.next()) {
                    System.out.println("Blogger added..");
                    bid = rs.getInt("id");
                }

                for (String cid : categoryIds) {
                    String sql2 = "insert into blogercategory(bid,cid) values(?,?)";
                    PreparedStatement smt3 = con.prepareStatement(sql2);
                    smt3.setInt(1, bid);
                    smt3.setInt(2, Integer.parseInt(cid));
                    smt3.executeUpdate();
                    System.out.println("category added..");
                }
            }
            con.commit();
            con.close();
            smt.close();
            status=true;
            
        } catch (Exception e) {
            System.out.println("operation rollbacked...");
            System.out.println("Blogger Insertion error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }

        return status;
    }

    public boolean removeBlogger(int id) {
        boolean status = false;

        return status;
    }

    public boolean update(Blogger blogger, String[] categoryIds) {
        boolean status = false;
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "update bloger set name=?,gender=?,mobile=?,address=?,pic=? where id=?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, blogger.getName());
            smt.setString(2, blogger.getGender());
            smt.setString(3, blogger.getMobile());
            smt.setString(4, blogger.getAddress());
            smt.setString(5, blogger.getPic());
            smt.setInt(6, blogger.getId());
            if (smt.executeUpdate() > 0) {
                String sql1 = "delete from blogrcategory where bid=?";
                PreparedStatement smt1 = con.prepareStatement(sql1);
                smt.setInt(1, blogger.getId());
                if (smt1.executeUpdate() > 0) {
                    for (String cid : categoryIds) {
                        String sql2 = "insert into blogercategory(bid,cid) values(?,?)";
                        PreparedStatement smt3 = con.prepareStatement(sql2);
                        smt3.setInt(1, blogger.getId());
                        smt3.setInt(2, Integer.parseInt(cid));
                        smt3.executeUpdate();
                    }
                }
            }
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Updation  error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return status;
    }
    
public boolean changePassword(int id, String newPwd){
    boolean status= false;
    Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "update bloger set password=? where id=?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, newPwd);
            smt.setInt(2, id);
            if (smt.executeUpdate() > 0) {
                status=true;
                }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Password Updation error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
            return status;
   
}

    public Blogger getById(int id) {
        Blogger blogger = null;
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "select * from blogger where id=?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setInt(1,id);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                blogger  = new Blogger();
                blogger.setId(rs.getInt("id"));
                blogger.setName(rs.getString("name"));
                blogger.setGender(rs.getString("gender"));
                blogger.setUserid(rs.getString("userid"));
                blogger.setPassword(rs.getString("password"));
                blogger.setMobile(rs.getString("mobile"));
                blogger.setAddress(rs.getString("address"));
                blogger.setPic(rs.getString("pic"));
                blogger.setApprovedStatus(rs.getString("approveStatus"));
                }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Select Error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return blogger;
    }

    public Blogger getByLoginDetails(String userid, String password) {
        Blogger blogger = null;
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "select * from blogger where userid=? and password=?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, userid);
            smt.setString(2, password);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                blogger  = new Blogger();
                blogger.setId(rs.getInt("id"));
                blogger.setName(rs.getString("name"));
                blogger.setGender(rs.getString("gender"));
                blogger.setUserid(rs.getString("userid"));
                blogger.setPassword(rs.getString("password"));
                blogger.setMobile(rs.getString("mobile"));
                blogger.setAddress(rs.getString("address"));
                blogger.setPic(rs.getString("pic"));
                blogger.setApprovedStatus(rs.getString("approveStatus"));
                }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Select Error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return blogger;
    }

    public ArrayList<Blogger> getAllBloggers() {
        ArrayList<Blogger> list = new ArrayList<Blogger>();
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "select * from blogger";
            PreparedStatement smt = con.prepareStatement(sql);
            
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Blogger blogger  = new Blogger();
                blogger.setId(rs.getInt("id"));
                blogger.setName(rs.getString("name"));
                blogger.setGender(rs.getString("gender"));
                blogger.setUserid(rs.getString("userid"));
                blogger.setPassword(rs.getString("password"));
                blogger.setMobile(rs.getString("mobile"));
                blogger.setAddress(rs.getString("address"));
                blogger.setPic(rs.getString("pic"));
                blogger.setApprovedStatus(rs.getString("approveStatus"));
            list.add(blogger);
            }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Select Error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<Blogger> getBloggersByCategory(int cid) {
        ArrayList<Blogger> list = new ArrayList<Blogger>();
 Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "select * from blogger where id in (select bid from blogerCategory where cid = ?);";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setInt(1, cid);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Blogger blogger  = new Blogger();
                blogger.setId(rs.getInt("id"));
                blogger.setName(rs.getString("name"));
                blogger.setGender(rs.getString("gender"));
                blogger.setUserid(rs.getString("userid"));
                blogger.setPassword(rs.getString("password"));
                blogger.setMobile(rs.getString("mobile"));
                blogger.setAddress(rs.getString("address"));
                blogger.setPic(rs.getString("pic"));
                blogger.setApprovedStatus(rs.getString("approveStatus"));
            list.add(blogger);
            }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Select Error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<Blogger> getBloggersByStatus(String status) {
        ArrayList<Blogger> list = new ArrayList<Blogger>();
        Connection con = DbConnection.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "select * from blogger where approvedStatus = ?";
            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1, status);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Blogger blogger  = new Blogger();
                blogger.setId(rs.getInt("id"));
                blogger.setName(rs.getString("name"));
                blogger.setGender(rs.getString("gender"));
                blogger.setUserid(rs.getString("userid"));
                blogger.setPassword(rs.getString("password"));
                blogger.setMobile(rs.getString("mobile"));
                blogger.setAddress(rs.getString("address"));
                blogger.setPic(rs.getString("pic"));
                blogger.setApprovedStatus(rs.getString("approveStatus"));
            list.add(blogger);
            }
            
            con.close();
            smt.close();

        } catch (Exception e) {
            System.out.println("Blogger Select Error :" + e.getMessage());
            try {
                con.rollback();
            } catch (Exception ex) {
            }
        }
        return list;
    }

}
