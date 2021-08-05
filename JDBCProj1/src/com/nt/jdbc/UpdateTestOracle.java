//UpdateTestOracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTestOracle {

	public static void main(String[] args) {
		Scanner sc=null;
		Statement st=null;
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
			//convert input values as required for the SQL qyery
			   newName="'"+newName+"'"; //gives 'raja'
			   newAddrs="'"+newAddrs+"'"; //gives 'hyd'
			   
			//Load jdbc driver class (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			   // update student  set sadd='hyd',sname='ramesh',avg=67.99 where sno=1001;
			String query="UPDATE  STUDENT  SET SADD="+newAddrs+",SNAME="+newName+",AVG="+newAvg+" WHERE SNO="+no;
			System.out.println(query);
			//send and execute SQL query in Db s/w
			int count=0;
			 if(st!=null) 
				  count=st.executeUpdate(query);
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
}//class
