package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s2url")
public class Servlet2  extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          //GEt PrintWriter
		  PrintWriter pw=res.getWriter();
		  //set response content type
		  res.setContentType("text/html");
		  //read and display the request attribute values
		  pw.println("<h1 style='color:red;text-align:center'>Servlet2::: attr1(req) value ::"+req.getAttribute("attr1")+"</h1>");
		//read and display the session attribute values
		  HttpSession ses=req.getSession();
		  pw.println("<h1 style='color:red;text-align:center'>Servlet2::: attr2(session) value :"+ses.getAttribute("attr2")+"</h1>");
		  
			//read and display the session attribute values
		  ServletContext sc=getServletContext();
		  pw.println("<h1 style='color:red;text-align:center'>Servlet2::: attr3(ServletContext) value :"+sc.getAttribute("attr3")+"</h1>");


		   //forward the request to Servlet3
		  RequestDispatcher rd=req.getRequestDispatcher("/s3url");
		  rd.forward(req,res);
		  
		  //	  close stream
		  pw.close();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
             doGet(req,res);
	}

}
