 
package com.db;
import java.sql.*;

/**
 *
 * @author DELL
 */
public class DbConnection {
   public static Connection getConnection(){
       Connection con = null;
       try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","");
           
       }catch(Exception e){
           System.out.println("Connection error  :" + e.getMessage());
       }
       return con;
   } 
}
