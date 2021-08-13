//PsUpdateTestOracle.java
package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsUpdateTestOracle {
   private static final String  STUDENT_UPDATE_QUERY="UPDATE  STUDENT SET SNAME=?,SADD=?,AVG=? WHERE SNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		PreparedStatement ps=null;
		Connection con=null;
		try {
			sc=new Scanner(System.in);
			//read inputs
		 String newName=null, newAddrs=null;
		 float  newAvg=0.0f;
		 int no=0;
			if(sc!=null) {
				System.out.println("Enter  student no::");
				no=sc.nextInt();
				System.out.println("Enter  new name for student::");
				newName=sc.next();  // gives raja
				System.out.println("Enter  new avg for student::");
				newAvg=sc.nextFloat();  //gives  67.87
				System.out.println("Enter  addres for student::");
				newAddrs=sc.next();  //gives hyd
			}
			   
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create Statement object
			if(con!=null)
				ps=con.prepareStatement(STUDENT_UPDATE_QUERY);
			//set values to query parameters(?)
			if(ps!=null) {
				ps.setString(1, newName);
				ps.setString(2, newAddrs);
				ps.setFloat(3, newAvg);
				ps.setInt(4, no);
			}
			
			//send and execute SQL query in Db s/w
			int count=0;
			 if(ps!=null) 
				  count=ps.executeUpdate();
			  //process the results
			 if(count==0)
				 System.out.println("Records not found to update");
			 else
				 System.out.println(count+ "no.of records are updated");
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
