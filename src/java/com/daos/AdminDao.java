package com.daos;
import com.beans.Admin;
import java.sql.Connection;
import java.util.*;
import com.db.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
    public boolean add(Admin admin){
        boolean status =false;
        try{
            Connection con =DbConnection.getConnection();
            String sql = "insert into admin(name,userid,password,pic,mobile)values(?,?,?,?,?)";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, admin.getName());
            smt.setString(2, admin.getUserid());
            smt.setString(3, admin.getPassword());
            smt.setString(4, admin.getPic());
            smt.setString(5, admin.getMobile());
            if(smt.executeUpdate()!=0)
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin insert error :" + e.getMessage());
        }
        
        return status;
    }
    
 public boolean update(Admin admin){
     boolean status=false;
      try{
            Connection con =DbConnection.getConnection();
            String sql = "update admin set name=?,pic=?,mobile=? where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, admin.getName());
            smt.setString(2, admin.getPic());
            smt.setString(3, admin.getMobile());
            smt.setInt(4, admin.getId());
            if(smt.executeUpdate()!=0)
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Update error :" + e.getMessage());
        }
        
     
     return status;
 }
 
 public boolean updatePassword(int id, String password){
   boolean status=false;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "update admin set password=? where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, password);
            smt.setInt(2, id);
             if(smt.executeUpdate()!=0)
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Update error :" + e.getMessage());
        }
     
     return status;  
 }
 
 public boolean validateOldPassword(int id, String password){
     boolean status=false;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "select * from admin where password=? and id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, password);
            smt.setInt(2, id);
            ResultSet rs = smt.executeQuery();
            if(rs.next())
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Old Passoword  error :" + e.getMessage());
        }
     
     return status;
 }
 
 public Admin getById(int id){
     Admin admin=null;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "select * from admin where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setInt(1, id);
            ResultSet rs = smt.executeQuery();
            if(rs.next())
            {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setMobile(rs.getString("mobile"));
                admin.setPassword(rs.getString("password"));
                admin.setPic(rs.getString("pic"));
                admin.setUserid(rs.getString("userid"));
            }
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Old Passoword  error :" + e.getMessage());
        }
     return admin;
 }
 
 public Admin getByUserid(String userid){
     Admin admin=null;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "select * from admin where userid=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, userid);
            ResultSet rs = smt.executeQuery();
            if(rs.next())
            {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setMobile(rs.getString("mobile"));
                admin.setPassword(rs.getString("password"));
                admin.setPic(rs.getString("pic"));
                admin.setUserid(rs.getString("userid"));
            }
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Old Passoword  error :" + e.getMessage());
        }
     return admin;
 }
 
 
 public boolean validateLogin(String userid,String password){
     boolean status=false;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "select * from admin where userid=? and password=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, userid);
            smt.setString(2, password);
            ResultSet rs = smt.executeQuery();
            if(rs.next())
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin Login  error :" + e.getMessage());
        }
     return status;
 }
 
 
    public static void main(String[] args) {
        Admin admin = new AdminDao().getById(1);
        System.out.println(admin.getName());
    }
}
