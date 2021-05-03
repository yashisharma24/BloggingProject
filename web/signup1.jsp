<%@page import="com.beans.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.daos.BlogDao"%>
<%@page import="com.daos.CategoryDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="TemplateMo">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i&display=swap" rel="stylesheet">

    <title>Inno Blogger Home</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-stand-blog.css">
    <link rel="stylesheet" href="assets/css/owl.css">
<!--

TemplateMo 551 Stand Blog

https://templatemo.com/tm-551-stand-blog

-->
  </head>

  <body>

    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->

    <!-- Header -->
    <jsp:include page="header.jsp"></jsp:include>
    
    <!-- Page Content -->
    <!-- Banner Starts Here -->
    <div class="main-banner header-text">
      <div class="container-fluid">
        <div class="owl-banner owl-carousel">
        </div>
          <center><h2>New Blogger Registration</h2></center>
                   
        
      </div>
    </div>
    <!-- Banner Ends Here -->
  <section class="blog-posts">
      <div class="container">
          <script language = "text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
  

<script type="text/javascript">
            function validateForm() {
                gender = document.getElementsByName("gender");
                categories = document.getElementsByName("category");
                pass = document.getElementById("pass").value;
                cpass = document.getElementById("cpass").value;
                // alert('ok');
                g = 0;
                h = 0;

                for (i = 0; i < gender.length; i++) {
                    //alert('gender '+gender[i].checked);
                    if (gender[i].checked)
                        g++;
                }
                for (j = 0; j < categories.length; j++) {
                    // alert('hobbies : '+ hobbies[i].checked);
                    if (categories[j].checked)
                        h++;
                }
                if (pass != cpass)
                {
                    document.getElementById("s1").innerHTML = "Password and confirm password doesn't matched";
                    return false;
                }

                if (g == 0)
                {
                    s2.innerHTML = "Please Select any Gender";
                    return false;
                }

                if (h == 0)
                {
                    s3.innerHTML = "Please Select atleast one hobby ";
                    return false;
                }


            }





            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        preview.src = e.target.result;
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
            
         </script>
     
        <!--<input type="button" value="close window" onclick="window.close();"/> -->

         <div class="row">
                <div class="col-md-12">
                    <center> 
                        <form action="signup2.jsp" method="post" class="form_container" onsubmit="return validateForm()"> 
                            <table class ="table">
                                
                                <tr>
                                    <td><input type="text" name="name" id="nm" placeholder="Enter name here" class="form-control" required="required"/></td>
                                </tr>
                                 
                                <tr>
                                    <td><input type="email" name="userid" id="username" placeholder="Enter Username[your email]" class="form-control" required="required"/>
                                        <span id="sp1"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="text" name="mobile" id="mobile" placeholder="mobile" class="form-control" required="required"/>
                                        <span id="sp1"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Password must contain atleast one upppercase, one lowercase , one digit   and min 8 character long<br/></b>
                                        <input type="password" name="password" id="pass" placeholder="Enter Password" class="form-control" required="required" onfocus="s1.innerHTML = '';" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/></td>
                                </tr>
                                <tr>
                                    <td><input type="password" name="cpass" id="cpass" placeholder="Confirm Password" class="form-control" required="required" onfocus="s1.innerHTML = '';"/>
                                        <br/> <span id="s1" style="color:red"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td> Select Gender : <span style="font-weight: bold;color:red" id="s2"></span> <br/>
                                        <input type="radio" name="gender" value="Male" id="gender1"/> Male <br/>
                                        <input type="radio" name="gender" value="Female" id="gender2"/> FeMale <br/>
                                    </td>
                                </tr>

                                <tr>
                                    <td> Select Categories :<span style="font-weight: bold;color:red" id="s3"></span> <br/>
                                    <% CategoryDao cd = new CategoryDao();
                    BlogDao bd = new BlogDao();
                    ArrayList<Category> clist = cd.getAllCategories();
                    for(Category c : clist){%>
                                 <input type="checkbox" name="category" value="<%=c.getId()%>" /> <%=c.getName()%> <br/>
                           
                     <%}%>
                                         </td>
                                </tr>
                                <tr>
                                    <td><input type="submit" class="btn btn-primary form-control" value="Next" name="submit"/></td>
                                </tr>
                            </table>
                            </form> 
                    </center>
                </div> 

             
            </div>
       
        
        <script type="text/javascript"> 
            $("#username").change(function(){
               xml = new XMLHttpRequest();
               xml.open("GET", "UserController?op=checkid&userid="+$(this).val(),true);
               xml.send();
               
               xml.onreadystatechange = function(){
                 if(this.readyState==4 && this.status==200){
                     output = this.responseText;
                     $("#sp1").html(output);
                 }  
               }
                
            });
            </script>
            
     
      </div>
    </section>

   <!--footer section -->
    <jsp:include page="footer.jsp"></jsp:include>
   
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Additional Scripts -->
    <script src="assets/js/custom.js"></script>
    <script src="assets/js/owl.js"></script>
    <script src="assets/js/slick.js"></script>
    <script src="assets/js/isotope.js"></script>
    <script src="assets/js/accordions.js"></script>

   