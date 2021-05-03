 
package com.daos;
import com.beans.Category;
import java.sql.*;
import com.db.DbConnection;
import java.util.ArrayList;
public class CategoryDao {
      public boolean add(Category category){
        boolean status =false;
        try{
            Connection con =DbConnection.getConnection();
            String sql = "insert into categories(name,description)values(?,?)";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, category.getName());
            smt.setString(2, category.getDescription());
            
            if(smt.executeUpdate()!=0)
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Category insert error :" + e.getMessage());
        }
        
        return status;
    }
    
 public boolean update(Category category) {
     boolean status=false;
      try{
            Connection con =DbConnection.getConnection();
            String sql = "update categories set name =?, description =? where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setString(1, category.getName());
            smt.setString(2, category.getDescription());
            smt.setInt(3, category.getId());
            if(smt.executeUpdate()!=0)
                status=true;
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin insert error :" + e.getMessage());
        }
     
     return status;  
 }
 
 public Category getById(int id){
     Category category=null;
        try{
            Connection con =DbConnection.getConnection();
            String sql = "select *  from categories where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setInt(1, id);
            ResultSet rs = smt.executeQuery();
            if (rs.next()){
                category = new  Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            }
             
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin insert error :" + e.getMessage());
        }
     
     return category;
 }
 
 public ArrayList<Category> getAllCategories(){
     ArrayList<Category> list  = new ArrayList();
     try{
            Connection con =DbConnection.getConnection();
            String sql = "select *  from categories";
            PreparedStatement smt  = con.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category = new  Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                list.add(category);
            }
             
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin insert error :" + e.getMessage());
        }
     return list;
 }
 
 public boolean removeCategory(int id){
     boolean status = false;
     try{
            Connection con =DbConnection.getConnection();
            String sql = "delete from categories where id=?";
            PreparedStatement smt  = con.prepareStatement(sql);
            smt.setInt(1, id);
             
            if (smt.executeUpdate()>0){
                status=true;
            }
             
            con.close();
            smt.close();
        }catch(Exception e){
            System.out.println("Admin insert error :" + e.getMessage());
        }
     return status;
 }
 
   /* public static void main(String[] args) {
       Category c= new Category();
       c.setName("Information Technology");
       c.setDescription("Information technology and computer world along with digital technology");
       CategoryDao cd = new CategoryDao();
       if(cd.add(c))
            System.out.println("category added..");
    }*/
 
}
