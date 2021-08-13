// PsInsertTest.java
package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
   private static final String  INSERT_STUDENT_QUERY= "INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
	  try {
		  //read inputs
		  sc=new Scanner(System.in);
		   int count=0;
		   if(sc!=null) {
			   System.out.println("etner students count::");
			   count=sc.nextInt();
		   }
		    //register  JDBC driver class
		    //Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		   //create Preparedstaement object having pre-compiled SQL INSERT Query
		   if(con!=null)
			   ps=con.prepareStatement(INSERT_STUDENT_QUERY);
		    //gather students details from enduser
		    for(int i=1;i<=count;++i) {
		    	//read  each student details
		    	System.out.println("Enter "+i+" student details");
		    	System.out.println("Etner  number::");
		    	int no=sc.nextInt();  // gives 1001
		    	System.out.println("Enter name ::");
		    	String name=sc.next(); //gives  raja
		    	System.out.println("Enter address ::");
		    	String addrs=sc.next(); //gives hyd
		    	System.out.println("Enter avg::");
		    	float avg=sc.nextFloat(); // gives 90.66
		    	//set each  student details to query parameter values
		    	  ps.setInt(1,no);
		    	  ps.setString(2, name);
		    	  ps.setString(3, addrs);
		    	  ps.setFloat(4, avg);
		    	  //exeute  the Query
		    	  int result=ps.executeUpdate();
		    	  //process the result
		    	  if(result==0)
		    		  System.out.println(i+" Student Record not inserted");
		    	  else
		    		  System.out.println(i+" Student Record is inserted");
		       }//for
		    }//try
		    catch(SQLException se) {
		    	se.printStackTrace();
		    }
	  
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    finally {
		    	//close  jdbc objs
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
