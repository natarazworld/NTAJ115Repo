package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  //get PrintWriter
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		 //read form data
		String ss=req.getParameter("ss");
		String engine=req.getParameter("engine");
		//send hyperlink to browser supporting the sendRedirection
		String url=null;
		if(engine.equalsIgnoreCase("google"))
			url="https://www.google.com/search?q="+ss;
		else if(engine.equalsIgnoreCase("bing"))
			url="https://www.bing.com/search?q="+ss;
		else 
			url="https://search.yahoo.com/search?p="+ss;
		
		// perform sendRedirection
		res.sendRedirect(url);
		RequestDispatcher rd=req.getRequestDispatcher("/abc.html");
		rd.forward(req, res);
		
		//close stream
		pw.close();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         doGet(req,res);
	}

}
