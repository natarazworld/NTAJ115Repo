//MarriageElgibilityCheckServlet.java
package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
		   int age=Integer.parseInt(req.getParameter("page"));
		   String gen=req.getParameter("gender");
		   
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
