//EmployeeSearchServlet.java
package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeSearchServlet extends HttpServlet {
	private  static final  String  GET_EMP_INFO="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";
	@Override
	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// set response content type
		res.setContentType("text/html");
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		//read form data
		 int eno=Integer.parseInt(req.getParameter("eno"));
		 //write  jdbc code
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
			 // get ServletConfig object
			 ServletConfig  cg=getServletConfig();
			 String driver=cg.getInitParameter("driver");
			 String url=cg.getInitParameter("jdbcurl");
			 String dbuser=cg.getInitParameter("dbuser");
			 String dbpwd=cg.getInitParameter("dbpwd");
			  System.out.println("ServletConfig object class name::"+cg.getClass());
			  
			 
			 //Load jdbc driver class
			 Class.forName(driver);
			 //establish the connection
			  con=DriverManager.getConnection(url, dbuser,dbpwd);
			  //create Jdbc Statement object
			   ps=con.prepareStatement(GET_EMP_INFO);
			   //set IN param values
			   ps.setInt(1, eno);
			   //execute the SQL query
			   rs=ps.executeQuery();
			   //process the ResultSet
			   if(rs.next()) {
				     pw.println("<h1 style='color:red;text-align:center'>"+eno+" Employee Details are </h1>");
				     pw.println("<table border='1' align='center'>");
				     pw.println("<tr> <td>Empno::  </td> <td>"+rs.getInt(1)+" </td> </tr>");
				     pw.println("<tr> <td>Emp Name::  </td> <td>"+rs.getString(2)+" </td> </tr>");
				     pw.println("<tr> <td>Emp Desg::  </td> <td>"+rs.getString(3)+" </td> </tr>");
				     pw.println("<tr> <td>Emp Salary::  </td> <td>"+rs.getFloat(4)+" </td> </tr>");
				     pw.println("<tr> <td>Emp  DeptNo::  </td> <td>"+rs.getInt(5)+" </td> </tr>");
				     pw.println("</table>");
			   }//if
			   else {
				   pw.println("<h1 style='color:red;text-align:center'> Employee Details are not found </h1>");
			   }//else
			   
			   
		 }//try
		 catch(SQLException se) {
			 se.printStackTrace();
			 pw.println("<h1 style='color:red;text-align:center'> Problem in DB Interaction </h1>");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 pw.println("<h1 style='color:red;text-align:center'> Unknown Problem  </h1>");
		 }
		 finally {
			 //close jdbc objs
			 try {
				 if(rs!=null)
					 rs.close();
			 }//try
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 try {
				 if(ps!=null)
					 ps.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 try {
				 if(con!=null)
					 con.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
		 }//finally
		 //add  home  hyperlink
		 pw.println("<h1 style='text-align:center'> <a href='input.html'>home </a> </h1>");
		 
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          doGet(req,res);
	}//doPost(-,-)

}//class
