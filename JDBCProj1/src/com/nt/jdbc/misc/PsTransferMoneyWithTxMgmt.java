//TransferMoneyWithTxMgmt.java
package com.nt.jdbc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PsTransferMoneyWithTxMgmt {

	public static void main(String[] args) {
	  try(Scanner sc=new Scanner(System.in);
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			  PreparedStatement ps1=con.prepareStatement("UPDATE  JDBC_ACCOUNT SET BALANCE=BALANCE-?  WHERE ACNO=?");
			  PreparedStatement ps2=con.prepareStatement("UPDATE  JDBC_ACCOUNT SET BALANCE=BALANCE+?  WHERE ACNO=?");
			  ) {
		  //read inputs
		  int srcAcno=0,destAcno=0;
		  float amount=0.0f;
		  if(sc!=null) {
			  System.out.println("Enter source account no::");
			    srcAcno=sc.nextInt();
			    System.out.println("Enter dest account no::");
			    destAcno=sc.nextInt();
			    System.out.println("Enter Amount::");
			    amount=sc.nextFloat();
		  }
		  
		     //Begin Tx by disabliging auto Commit mode on Db s/w
		   if(con!=null)
			   con.setAutoCommit(false);
		   
		     //set values to query params 
		    if(ps1!=null) {
		    	ps1.setFloat(1,amount);
		    	ps1.setInt(2,srcAcno);
           }
		    
		    //set values to query params 
		    if(ps2!=null) {
		    	ps2.setFloat(1,amount);
		    	ps2.setInt(2,destAcno);
           }
		    
		    //eecute the queries
		    int result1=0,result2=0;
		    if(ps1!=null && ps2!=null) {
		    	   result1=ps1.executeUpdate();
		    	   result2=ps2.executeUpdate();
		    }
		    //perform Tx Mgmt
		    if(result1==0 || result2==0) {
		    	con.rollback();
		    	System.out.println("Moneny not transftered--Tx is rolledback ");
		    }
		    else {
		    	con.commit();
		    	System.out.println("Moneny  transftered--Tx is committed ");
		    }
           
	  }
	  catch(SQLException se) {
		  se.printStackTrace();
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }

	}

}
