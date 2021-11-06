//MarriageElgibilityCheckServlet.java
package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MarriageElgibilityCheckServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MarriageElgibilityCheckServlet.::doGet(-,-)");
           //get PrintWriter 
		   PrintWriter pw=res.getWriter();
		   //set response content type
		   res.setContentType("text/html");
		   //read form data
		   String name=req.getParameter("pname");
		   String tage=req.getParameter("page");
		   String gen=req.getParameter("gender");
		   String  vstatus=req.getParameter("vflag");
		  //  enable Server side Form validation logics only when client side form validations are not done
		   int age=0;
	   if(vstatus.equalsIgnoreCase("no")) {
		   List<String> errorsList=new ArrayList();
		   System.out.println("Server side form validations ");
		    if(name==null || name.length()==0 || name.equals(""))  //required rule
		    	errorsList.add("Person name is required");
		    else if(name.length()<5)
		    	errorsList.add("Person name must have minimum of 5 characters");   //min length rule
		    
		    if(tage==null || tage.length()==0 || tage.equals("")) //required rule
		    	 errorsList.add("Person age is required");
		    else {
		    	   try {
		    		   age=Integer.parseInt(tage);
		    		   if(age<=0 || age>125) {   // range rule
		    			   errorsList.add("Person age must be in the range of 1 through 125");
		    		   }
		    	   }//try
		    	   catch(NumberFormatException nfe) {
		    		   errorsList.add("Person  age must be numeric value");  // must be numeric value rule
		    	   }
		    }
		   
		    if(gen.equalsIgnoreCase("") || gen==null || gen.length()==0)  //required
		    	errorsList.add("Person Gendor must be selected");
		    
		    //display form validation error messages
		    if(errorsList.size()!=0) {
		    	 pw.println("<ul>");
		    	for(String msg:errorsList) {
		    		pw.println("<li style='color:red'>"+msg+"</li>");
		    	}//for
		    	 pw.println("</ul>");
		    	 return;   //return stmt with out value returns the control from curent method definitation to caller.
		    }//if 
	   }//if
	   else {
		   age=Integer.parseInt(tage);
	   }
		   
		   //write  b.logic 
		   if(gen.equalsIgnoreCase("M")) {
			    if(age>=21) {
			    	pw.println("<h1 style='color:blue;text-align:center'>Mr."+name+" u r eglible for marriage but think twice </h1>");
			    }
			    else {
			    	pw.println("<h1 style='color:red;text-align:center'>Mr."+name+" u r not eglible for marriage ..Be happy </h1>");
			    }
		   }
		   else {
			   if(age>=18) {
			    	pw.println("<h1 style='color:green;text-align:center'>Miss."+name+" u r eglible for marriage but think twice </h1>");
			    }
			    else {
			    	pw.println("<h1 style='color:red;text-align:center'>Miss."+name+" u r not eglible for marriage ..Be happy </h1>");
			    }//else
		   }//else
		   //add home hyperlink as the graphical link
		   pw.println("<br><br><a href='input.html'><img src='images/home.png'></a>");
		   
		   //clsoe stream
		   pw.close();
	}//method
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MarriageElgibilityCheckServlet.doGet()");
		//get PrintWriter 
		   PrintWriter pw=res.getWriter();
		   //set response content type
		   res.setContentType("text/html");
		   //write logic
		   Date d=new Date();
		   pw.println("<h1 style='color:red;text-align:center' >"+d.toString()+"</h1>");
		   //cose stream
		   pw.close();
	}

}//class
