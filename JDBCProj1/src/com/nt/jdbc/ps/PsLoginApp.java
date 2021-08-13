package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
    private static final String  LOGIN_QUERY="SELECT COUNT(*) FROM USERSLIST WHERE UNAME=? AND PWD=? ";
	public static void main(String[] args) {
		
		Scanner sc=null;
		String user=null, pass=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter username ::");
				user=sc.nextLine();  //gives raja
				System.out.println("Enter password::");
				pass=sc.nextLine(); //gives rani
			}
			//  Load jdbc dirver classs (optional)
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			 //create JDBC PreparedStatement obj
			 if(con!=null)
				   ps=con.prepareStatement(LOGIN_QUERY);
			 // set values to query params
			 if(ps!=null) {
				 ps.setString(1,user);
				 ps.setString(2, pass);
			 }
			 //execute the pre-compiled SQL query
			 if(ps!=null)
				 rs=ps.executeQuery();
			 //process the Result
			  if(rs!=null) {
				  rs.next();
				  int  count=rs.getInt(1);
				  if(count==0)
					  System.out.println("Invalid Credentials");
				  else
					  System.out.println("Valid Credentials");
			  }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
