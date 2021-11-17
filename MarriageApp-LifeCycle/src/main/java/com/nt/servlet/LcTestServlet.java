package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LcTestServlet extends HttpServlet {
	static {
		System.out.println("LcTestServlet:: static block");
	}
	
	public LcTestServlet() {
		System.out.println("LcTestServlet:: 0-param constructor");
	}
	
	@Override
	public void init() throws ServletException {
	  System.out.println("LcTestServlet.init()");
	  ServletConfig cg=getServletConfig();
	  System.out.println("init params===>"+cg.getInitParameter("driverClass")+"  "+cg.getInitParameter("dbuser"));
	  
	}
	
	/*@Override
		public void init(ServletConfig cg) throws ServletException {
	      System.out.println("LcTestServlet: init(-) method");
	      String driver=cg.getInitParameter("driverClass");
	      String user=cg.getInitParameter("dbuser");
	      System.out.println(driver+"    "+user);
		}*/
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LcTestServlet.service(req,res) doPost(-,-)");


	  //get PrintWriter
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		//write content to response object
		pw.println("<h1 style='color:red;text-align:center'> Date and time ::"+new java.util.Date()+"</h1>");
		
		ServletConfig cg=getServletConfig();
		  System.out.println("init params===>"+cg.getInitParameter("driverClass")+"  "+cg.getInitParameter("dbuser"));
		//close stream 
		pw.close();
	}
	
	public static void main(String[] args) {
		System.out.println("main(-) method");
	}
	
	@Override
	public void destroy() {
		System.out.println("LcTestServlet.destroy()");
	}

}
