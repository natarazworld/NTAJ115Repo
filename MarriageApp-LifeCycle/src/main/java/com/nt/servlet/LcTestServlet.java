package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class LcTestServlet extends HttpServlet {
	static {
		System.out.println("LcTestServlet:: static block");
	}
	
	public LcTestServlet() {
		System.out.println("LcTestServlet:: 0-param constructor");
	}
	
	@Override
	public void init(ServletConfig cg) throws ServletException {
          System.out.println("LcTestServlet: init(-) method");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("LcTestServlet.service(req,res)");
	  //get PrintWriter
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		//write content to response object
		pw.println("<h1 style='color:red;text-align:center'> Date and time ::"+new java.util.Date()+"</h1>");		
		//close stream 
		pw.close();
	}
	
	@Override
	public void destroy() {
		System.out.println("LcTestServlet.destroy()");
	}

}
