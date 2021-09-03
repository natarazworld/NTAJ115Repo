package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/*REATE TABLE "SYSTEM"."CITIZEN_DETAILS" 
(	"CID" NUMBER(10,0) NOT NULL ENABLE, 
	"CNAME" VARCHAR2(20 BYTE), 
	"CADD" VARCHAR2(20 BYTE), 
	"DOB" DATE, 
	"DOM" DATE, 
	"DOJ" DATE, 
	 CONSTRAINT "CITIZEN_DETAILS_PK" PRIMARY KEY ("CID"));
*/
public class PsDateInsertTestMySQL {
  private  static   final String  INSERT_CITIZEN_DETAILS="INSERT INTO CITIZEN_DETAILS  VALUES(?,?,?,?,?,?)";  
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int cno=0;
			String name=null;
			String addrs=null;
			String sdob=null, sdom=null, sdoj=null;
			if(sc!=null) {
				System.out.println("Enter  Citizen Id::");
				cno=sc.nextInt();
				System.out.println("Enter Citizen name:: ");
				 name=sc.next();
				 System.out.println("Enter Citizen address::");
				 addrs=sc.next();
				 System.out.println("Enter DOB (dd-MM-yyyy");
				 sdob=sc.next();
				 System.out.println("Enter DOM (MM-dd-yyyy");
				 sdom=sc.next();
				 System.out.println("Enter DOJ (yyyy-MM-dd)");
				 sdoj=sc.next();
			}
		
			 // convert DOB((dd-MM-yyyy) to java.sql.Date class obj
			     SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			     java.util.Date udob=sdf1.parse(sdob);
			     long ms=udob.getTime();
			     java.sql.Date sqdob=new  java.sql.Date(ms);
           //covert DOM( (MM-dd-yyyy) to java.sql.Date class obj
			     SimpleDateFormat sdf2=new SimpleDateFormat("MM-dd-yyyy");
			     java.util.Date udom=sdf2.parse(sdom);
			     java.sql.Date sqdom=new  java.sql.Date(udom.getTime());
	           //covert DOJ(yyyy-MM-dd) to java.sql.Date class obj
			     java.sql.Date sqdoj=java.sql.Date.valueOf(sdoj);
			     
//			     //Load  JDBC driver class
//			     Class.forName("oracle.jdbc.driver.OracleDriver");
//			     //establish the connection
//			     con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			     
			     
			     //Load  JDBC driver class
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     //establish the connection
			     con=DriverManager.getConnection("jdbc:mysql:///NTAJ115DB1","root", "root");
			     
			     //create PreparedStement obj having INSERT SQL query as pre-compiled Query
			     if(con!=null)
			    	 ps=con.prepareStatement(INSERT_CITIZEN_DETAILS);
			     //set the query parameter values
			     if(ps!=null) {
			    	 ps.setInt(1,cno);
			    	 ps.setString(2, name);
			    	 ps.setString(3, addrs);
			    	 ps.setDate(4,sqdob);
			    	 ps.setDate(5,sqdom);
			    	 ps.setDate(6,sqdoj);
			     }
			     //execute the pre-compiled SQL query
			      int result=ps.executeUpdate();
			      //rprocess the 
			      if(result==0)
			    	  System.out.println("Action(record insertion) is wasted");
			      else
			      System.out.println("Action( is successfully done)");
		}
		catch(SQLException se) {
			se.printStackTrace();
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
		}//finally
			
	}//main
}//calss
