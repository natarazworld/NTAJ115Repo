package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowCapitalsServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  String capitals[]=new String[] {"New Delhi","Islamabad","WashingtonDC","Beging","Columbo"};
	          //general settings
		    PrintWriter pw=res.getWriter();
		    //set response content type
		    res.setContentType("text/html");
		    //read form data
		    int countryCode=Integer.parseInt(req.getParameter("country"));
		    //display capital
		    pw.println("<h1 style='color:red;text-align:center'>Capital city name is  ::"+capitals[countryCode]+" </h1>");
		    //add home hyperlink
		    pw.println("<a href='input.html'> home </a>");
		    //close stream
		    pw.close();
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}

}
