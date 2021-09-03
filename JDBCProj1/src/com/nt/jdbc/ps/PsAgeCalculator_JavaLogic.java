package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsAgeCalculator_JavaLogic {
  private static final String GET_DOB_BY_ID="SELECT DOB FROM CITIZEN_DETAILS WHERE CID=? ";
public static void main(String[] args) {
   Connection con=null;		
   PreparedStatement ps=null;
   ResultSet rs=null;
   Scanner sc=null;
   int no=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter citizen number::");
				no=sc.nextInt();
			}
			
			/*  //Load  JDBC driver class
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:mysql:///NTAJ115db1","root", "root");*/
			
			 //Load  JDBC driver class
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		     
		     //create PrpearedStaement object having  pre-compiled SQL query
		     if(con!=null)
		    	 ps=con.prepareStatement(GET_DOB_BY_ID);
		     //set values to query params
		     if(ps!=null)
		    	 ps.setInt(1,no);
		     //execute the pre-compiled  SQL Query
		     if(ps!=null)
		    	 rs=ps.executeQuery();
		     //process the Result
		     if(rs!=null) {
		        if(rs.next()) {
		            java.sql.Date sqdob=rs.getDate(1);
		            long dobMs=sqdob.getTime();
		            long sysDateMs=System.currentTimeMillis(); // or long sysDateMs=new Date().getTime();
		            float age=(sysDateMs-dobMs)/(1000.0f*60.0f*60.0f*24.0f*365.25f);
		            System.out.println("Person age ::"+age);
		        	
		        }
		        else {
		        	System.out.println("Person not found");
		        }
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
