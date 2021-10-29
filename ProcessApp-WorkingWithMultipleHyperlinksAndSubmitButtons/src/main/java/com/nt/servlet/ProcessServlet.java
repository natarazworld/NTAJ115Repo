package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProcessServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		//set response cotent type
		res.setContentType("text/html");
		//read addtional param value
		String pval=req.getParameter("s1");
		//read form data and convert  that in to numeric values only when hyperlinks are not generating requests
		float val1=0.0f, val2=0.0f;
		if(!pval.equalsIgnoreCase("link1") && !pval.equalsIgnoreCase("link2")) {
			val1=Float.parseFloat(req.getParameter("t1"));
			val2=Float.parseFloat(req.getParameter("t2"));
		}
		//write request processing logic for submit Buttons and hyerplinks
		if(pval.equalsIgnoreCase("add")) {
			float result=val1+val2;
			pw.println("<h1 style='color:red;text-align:center'>Add ::"+result+"</h1>");
		}
		else if(pval.equalsIgnoreCase("sub")) {
			float result=val1-val2;
			pw.println("<h1 style='color:red;text-align:center'>sub::"+result+"</h1>");
		}
		else if(pval.equalsIgnoreCase("mul")) {
			float result=val1*val2;
			pw.println("<h1 style='color:red;text-align:center'>Mul::"+result+"</h1>");
		}
		else if(pval.equalsIgnoreCase("div")) {
			float result=val1/val2;
			pw.println("<h1 style='color:red;text-align:center'>DIV::"+result+"</h1>");
		}
		else if(pval.equalsIgnoreCase("link1")) {
			pw.println("<b>System properites ::</b>"+System.getProperties());
		}
		else {
			pw.println("<h1 style='color:red;text-align:center'> Date And time :: "+new java.util.Date()+"</h1>");
		}
			
		//add hyperlink
		pw.println("<br> <h1 style='color:red;text-align:center'> <a href='form.html'>home </a> </h1>");
		
		//close PrintWriter
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
              doGet(req,res);
	}//doPost(-,-)

}//class
