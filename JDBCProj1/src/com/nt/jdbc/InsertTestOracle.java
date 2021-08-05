//InsertTestOracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestOracle {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int no=0;
			String name=null, addrs=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("enter student number::");
				no=sc.nextInt();  //gives  5742
				System.out.println("enter student name ::");
				name=sc.next();  //gives  anil
				System.out.println("Enter  studnet address::");
				addrs=sc.next(); //gives  viizag
				System.out.println("enter student  avg::");
				avg=sc.nextFloat(); //gives 65.88
			}
		   //convert input values as required form the SQL query
			   name="'"+name+"'"; //gives 'anil'
			   addrs="'"+addrs+"'"; //gives  'vizag'
			  //Load jdbc driver class
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   //establish the connection
			   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			   //create Statement object
			   if(con!=null)
				   st=con.createStatement();
			   //prepare SQL query
			         //insert into student values(5472,'anil','vizag',65.88)
			     String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			     System.out.println(query);
			   //send and execute SQL query in Db s/w
			     int count=0;
			     if(st!=null)
			    	 count=st.executeUpdate(query);
			     //process the result
			     if(count==0)
			    	 System.out.println("Record not inserted");
			     else 
			    	 System.out.println("Record  inserted");
			
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
