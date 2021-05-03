 
package com.controllers;

import com.beans.Blogger;
import com.daos.BloggerDao;
import com.sun.imageio.plugins.common.ImageUtil;
import com.utility.ImageUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
public class BloggerController extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String op = request.getParameter("op");
        if(op!=null && op.equalsIgnoreCase("add")){
            BloggerDao bd = new BloggerDao();
            HttpSession session = request.getSession();
            Blogger blogger = (Blogger) session.getAttribute("blogger");
            String categories[] = (String[]) session.getAttribute("categories");
            String image = ImageUtility.uploadImage(request, getServletConfig(), "media/bloggers");
            if(image!=null){
                blogger.setPic(image);
            if(bd.add(blogger, categories))
            {
                out.println("Blogger registered ! <br/> Pending for approval");
                out.println("<br/><a href='login.jsp'> goto Login Page</a>");
                out.println("<br/> <a href='index.jsp'>Back</a>");
            }
            else
                out.println("Error in Registraion");
            }
            else 
                out.println("Image cannot be uploaded...");
        }
    }

 

}
