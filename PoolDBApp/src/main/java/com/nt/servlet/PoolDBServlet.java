package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PoolDBServlet extends HttpServlet {
	private static final String  INSERT_CUSTOMER_QUERY="INSERT INTO SERVLET_CUSTOMER1 VALUES(CNO1_SEQ.NEXTVAL,?,?,?)";
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter object
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data
		String name=req.getParameter("cname");
		String addrs=req.getParameter("cadd");
		float billAmt=Float.parseFloat(req.getParameter("billAmt"));
		//get JDBC con object
		try(Connection con=getPooledJDBCConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
				){
			  //set query param values
			  ps.setString(1,name);
			  ps.setString(2, addrs);
			  ps.setFloat(3,billAmt);
			  //execute the query
			  int result=ps.executeUpdate();
			  //process the Result
			  if(result==0)
				  pw.println("<h1 style='color:red;text-align:center'> Problem in Customer  registration </h1>");
			  else
				  pw.println("<h1 style='color:red;text-align:center'>  Customer  registration completed </h1>");
			  
			  pw.println("<br> <a href='customer_register.html'> regisetr customer </a>");
			  //close stream
			  pw.close();
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1 style='color:red;text-align:center'> Intenal Problem  -Try again </h1>");
		}
				
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
	}
	
	
	//helper method to use only in the current class
	private  Connection getPooledJDBCConnection() throws Exception{
		  //create InitialContext object to establish the connection with  jndi registry
		  InitialContext ic=new InitialContext();
		  //perform jndi lookup operation and get DataSource obj ref from jndi registry
		 // DataSource ds=(DataSource) ic.lookup("DsJndi");  //other than  tomcat server
		  DataSource ds=(DataSource) ic.lookup("java:/OracleDS");  //only for  wildfly  server

		 // DataSource ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");  //only for  tomcat server

		  
		  //get  Pooled jdbc con object
		  Connection con=ds.getConnection();
		return con;
	}

}
