package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3Oracle {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int no=0;
			if(sc!=null) {
				System.out.println("Enter employee number::");
				no=sc.nextInt();  //gives 7899 
			}
			
			 //Load JDBC driver class  (optional)
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 
			 // create JDBC Statment obj
			 if(con!=null)
				 st=con.createStatement();
			 //prepare SQL query
			    //select empno,ename,job,sal from emp where empno=7499;
			 String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO="+no;
			 //send and execute SQL query in Db s/w
			 if(st!=null)
				 rs=st.executeQuery(query);
			 
			 //process the ResultSet
			 if(rs!=null) {
				 if(rs.next()) {
					 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				 }//if
				 else{
					 System.out.println("Employee not found");
				 }//else
					 
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
			   }//try
			   catch(SQLException se) {
				   se.printStackTrace();
			   }
			   try {
				   if(st!=null)
					   st.close();
			   }//try
			   catch(SQLException se) {
				   se.printStackTrace();
			   }
			   
			   try {
				   if(con!=null)
					   con.close();
			   }//try
			   catch(SQLException se) {
				   se.printStackTrace();
			   }
			   
			   try {
				   if(sc!=null)
					   sc.close();
			   }//try
			   catch(Exception e) {
				   e.printStackTrace();
			   }
			   
		}//finally

	}//main
}//class

