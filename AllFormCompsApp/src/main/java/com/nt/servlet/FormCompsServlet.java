package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormCompsServlet extends HttpServlet {
     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	 // get PrintWriter 
    	 PrintWriter pw=res.getWriter();
    	 //set response cotent type
    	 res.setContentType("text/html");
    	 //read form data
    	 String name=req.getParameter("pname");
    	 int age=Integer.parseInt(req.getParameter("page"));
    	 String addrs=req.getParameter("paddrs");
    	 String ms=req.getParameter("ms");
    	 String gender=req.getParameter("gender");
    	 String  qlfy=req.getParameter("qlfy");
    	 String courses[]=req.getParameterValues("crs");
    	 String mail=req.getParameter("email");
    	 String dob=req.getParameter("dob");
    	 String tob=req.getParameter("tob");
    	 String  wob=req.getParameter("wb");
    	 String fbUrl=req.getParameter("fbUrl");
    	 long mobileNo=Long.parseLong(req.getParameter("mobileNo"));
    	 int favNo=Integer.parseInt(req.getParameter("favNo"));
    	 String favColor=req.getParameter("favColor");
    	 float salary=Float.parseFloat(req.getParameter("salary"));
    	 String item=req.getParameter("itemSearch");
    	 
    	 //provide non-select state for  cheboxes and list boxes
    	  ms=(ms==null)?"single":ms;
    	  courses=(courses==null)?new String[] {"No courses are seelted"}:courses;
    	 
    	 //write b.logic
    	  if(gender.equalsIgnoreCase("M")) {
    		    if(age<5) {
    		    	pw.println("<h1  style='color:red;text-align:center'>Master."+name+"  u  r  baby boy </h1>");
    		    }
    		    else if(age<12) {
    		    	pw.println("<h1  style='color:red;text-align:center'>Master."+name+"  u  r small boy </h1>");
    		    }
    		    else if(age<19) {
    		    	pw.println("<h1  style='color:red;text-align:center'>Mr."+name+"  u  r teenage boy </h1>");
    		    }
    		    else if(age<35) {
    		    	pw.println("<h1  style='color:red;text-align:center'>Mr."+name+"  u  r young man </h1>");
    		    }
    		    else if(age<50) {
    		    	pw.println("<h1  style='color:red;text-align:center'>Mr."+name+"  u  r middle-aged man </h1>");
    		    }
    		    else {
    		    	pw.println("<h1  style='color:red;text-align:center'>Mr."+name+"  u  r old man </h1>");
    		    }
    	   }
    	  else {
    		  if(age<5) {
  		    	pw.println("<h1  style='color:red;text-align:center'>Master."+name+"  u  r  baby girl </h1>");
  		    }
  		    else if(age<12) {
  		    	pw.println("<h1  style='color:red;text-align:center'>Master."+name+"  u  r small girl </h1>");
  		    }
  		    else if(age<19) {
  		    	if(ms.equalsIgnoreCase("married"))
  		    	    pw.println("<h1  style='color:red;text-align:center'>Mrs."+name+"  u  r teenage married girl </h1>");
  		    	else
  		    		pw.println("<h1  style='color:red;text-align:center'>Miss."+name+"  u  r  teenage  girl </h1>");
  		    }
  		    else if(age<35) {
  		    	if(ms.equalsIgnoreCase("married"))
  		    	    pw.println("<h1  style='color:red;text-align:center'>Mrs."+name+"  u  r young  married  woman </h1>");
  		    	else
  		    		pw.println("<h1  style='color:red;text-align:center'>Miss."+name+"  u  r young    woman </h1>");
  		    }
  		    else if(age<50) {
    		  	if(ms.equalsIgnoreCase("married"))
      		    	pw.println("<h1  style='color:red;text-align:center'>Mrs."+name+"  u  r middle-aged  married woman </h1>");
    		  	else
    		  		pw.println("<h1  style='color:red;text-align:center'>Miss."+name+"  u  r middle-aged   woman </h1>");
  		    }
  		    else {
  		    	if(ms.equalsIgnoreCase("married"))
  		    	   pw.println("<h1  style='color:red;text-align:center'>Mrs."+name+"  u  r married dadi </h1>");
  		    	else
  		    		pw.println("<h1  style='color:red;text-align:center'>Miss."+name+"  u  r buddi </h1>");
  		    }
    	  }//else
    		  //display form data
    		  pw.println("<h1 style='color:red;text-align:center'> Form data is  </h1>");
    		  pw.println("<b> name :: "+name+"</b><br>");
    		  pw.println("<b> age :: "+age+"</b><br>");
    		  pw.println("<b> addrs :: "+addrs+"</b><br>");
    		  pw.println("<b> qualification :: "+qlfy+"</b><br>");
    		  pw.println("<b> maritial status :: "+ms+"</b><br>");
    		  pw.println("<b> courses :: "+Arrays.toString(courses)+"</b><br>");
    		  pw.println("<b> email :: "+mail+"</b><br>");
    		  pw.println("<b> DOB :: "+dob+"</b><br>");
    		  pw.println("<b> TOB :: "+tob+"</b><br>");
    		  pw.println("<b> WOB :: "+wob+"</b><br>");
    		  pw.println("<b> mobileNo :: "+mobileNo+"</b><br>");
    		  pw.println("<b> faviourite No :: "+favNo+"</b><br>");
    		  pw.println("<b> faviourite Color :: "+favColor+"</b><br>");
    		  pw.println("<b> Salary :: "+salary+"</b><br>");
    		  pw.println("<b> Search Item :: "+item+"</b><br>");
    		  
    		  //add home hyperlink
    		  pw.println("<a href='person_details.html'>home</a>");
    		  
    
    }
     
     @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                  doGet(req,resp);
    }
}
