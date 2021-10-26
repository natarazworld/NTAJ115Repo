package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LinksServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        //get PrintWriter
		  PrintWriter pw=res.getWriter();
		  //set response content type
		  res.setContentType("text/html");
		  //read the value of addtional req param 
		  String pval=req.getParameter("p1");
		  Locale locales[]=Locale.getAvailableLocales();
		  //differentiate logic for different requests
		   if(pval.equalsIgnoreCase("link1")) {
			   pw.println("<h1> All Languages are </h1>");
			   for(Locale l:locales) {
				   pw.println(l.getDisplayLanguage()+",");
			   }
		   }
		   else if(pval.equalsIgnoreCase("link2")){
			   pw.println("<h1> All Countries are </h1>");
			   for(Locale l:locales) {
				   pw.println(l.getDisplayCountry()+",");
			   }
		   }
		   else {
			   pw.println("<h1>System Properties are </h1>");
			   Properties props=System.getProperties();
			  for(Object k:props.keySet()) {
		           pw.println(k+"="+props.getProperty((String) k)+"<br>");
		     }//for
		   }//else
		   	//add home hyperlink
		   pw.println("<a href='links.html'>home </a>");
		   
		  //close steam
		   pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}

}
