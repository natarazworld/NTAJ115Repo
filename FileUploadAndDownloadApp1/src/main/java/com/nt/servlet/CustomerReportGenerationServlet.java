package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/report")
public class CustomerReportGenerationServlet extends HttpServlet {
	private  static final String GET_ALL_CUSTOMERS="SELECT CNO,CNAME,CADD,PHOTO_PATH, RESUME_PATH FROM SERVLET_UPLOAD_CUSTOMER";
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
           //get PrintWriter 
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                                                                                                "system","manager");
				  PreparedStatement ps=con.prepareStatement(GET_ALL_CUSTOMERS);
				ResultSet rs=ps.executeQuery();
				){
			   //process the ResultSet
			   pw.println("<table boder='1' align='center' bgcolor='cyan'>");
			   pw.println("<tr><th>CNO</th><th>CNAME</th><th>CADD</th> <th>PHOTO</th><th>Resume</th></tr>");
			   while(rs.next()) {
				   pw.println("<tr>");
				   pw.println("<td>"+rs.getInt(1)+"</td>");
				   pw.println("<td>"+rs.getString(2)+"</td>");
				   pw.println("<td>"+rs.getString(3)+"</td>");
				   pw.println("<td><a href='download?pid="+rs.getInt(1)+"'>Download Photo </a></td>");
				   pw.println("<td><a href='download?rid="+rs.getInt(1)+"'>Download Resume </a></td>");
				   pw.println("</tr>");
			   }
			   pw.println("</table>");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//close stream
		pw.close();
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
