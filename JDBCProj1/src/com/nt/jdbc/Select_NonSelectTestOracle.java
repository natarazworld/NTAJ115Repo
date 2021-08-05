// Select_NonSelectTestOracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select_NonSelectTestOracle {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			///read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter STUDENT db table SELECT or NON-SELECT SQL query");
				query=sc.nextLine();
			}//if
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL query in Db s/w
			if(st!=null) {
				 boolean flag=st.execute(query);
				 if(flag) { //(flag==true)
					 System.out.println("SELECT SQL Query executed");
					 //get ResultSEt object
					  rs=st.getResultSet();
					 if(rs!=null) {
						 while(rs.next()) {
							 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						 }//while
					 }//if
				 }//if
				 else {
					 System.out.println("NON-SELECT SQL Query executed");
					 int count=st.getUpdateCount();
					 System.out.println(count+" no.of records are effected");
				 }//else
			}//if
				
			}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.err.println("Duplicate or empty value can not inserted to PK column SNO");
			else if(se.getErrorCode()==12899)
				System.err.println("given value is larger than col size");
			else if (se.getErrorCode()>=900 && se.getErrorCode()<=1000)
				System.err.println("Query syntax problem");
			else {
				System.err.println(" unknown problem");
				se.printStackTrace();
			}
		}//catch
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
				if(st!=null)
					st.close();
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
}//lcass
