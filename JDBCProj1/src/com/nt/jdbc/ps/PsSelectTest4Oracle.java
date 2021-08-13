//SelectTest4Oracle .java
package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest4Oracle {
  private static final String EMPS_COUNT_QUERY="SELECT COUNT(*) FROM EMP";
  public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(EMPS_COUNT_QUERY);
		
			//send and execute the SQL query in DB s/w
			if(ps!=null)
				rs=ps.executeQuery();
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

	}//main
}//class
