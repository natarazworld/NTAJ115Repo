//SelectTest4Oracle .java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest4Oracle {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			String query="SELECT COUNT(*) FROM EMP";
			//send and execute the SQL query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj
			   if(rs!=null) {
				   rs.next();
				   //System.out.println("count of records ::"+rs.getInt(1));
				   System.out.println("count of records ::"+rs.getInt("COUNT(*)"));
			   }
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


		}//finally

	}//main
}//class
