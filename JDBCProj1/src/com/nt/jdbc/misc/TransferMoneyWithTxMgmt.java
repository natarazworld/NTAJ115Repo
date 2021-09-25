//TransferMoneyWithTxMgmt.java
package com.nt.jdbc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferMoneyWithTxMgmt {

	public static void main(String[] args) {
	  try(Scanner sc=new Scanner(System.in);
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			  Statement st=con.createStatement()
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
		   //add queries to the batch
		            //for withdraw opeation
		   st.addBatch("UPDATE  JDBC_ACCOUNT SET BALANCE=BALANCE-"+amount+ "WHERE ACNO="+srcAcno);
           //for deposite opeation
          st.addBatch("UPDATE  JDBC_ACCOUNT SET BALANCE=BALANCE+"+amount+ "WHERE ACNO="+destAcno);
           //execute the batch
           int result[]=st.executeBatch();
           //perform TxMgmt 
           boolean flag=true;
           
           for(int i=0;i<result.length;++i) {
        	    if(result[i]==0) {
        	    	  flag=false;
        	    	  break;
        	    }
           }
           
           if(flag) {
        	   con.commit();
        	   System.out.println("Money Transffred -Tx is comitted");
           }
           else {
        	   con.rollback();
        	   System.out.println("Money  not Transffred -Tx is Rolled back");
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
