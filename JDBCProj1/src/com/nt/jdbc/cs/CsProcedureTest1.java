package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_SUM 
(
  X IN NUMBER 
, Y IN NUMBER 
, Z OUT NUMBER 
) AS 
BEGIN
  Z:=X+Y;
END P_SUM;
*/

public class CsProcedureTest1 {
  private static final String CALL_PROCEDURE="{CALL P_SUM(?,?,?) }";
	public static void main(String[] args) {
		
		 int first=0,second=0;
		try(Scanner sc=new Scanner(System.in)) {
			  if(sc!=null) {
				  System.out.println("enter first value::");
				  first=sc.nextInt();
				  System.out.println("etner second vaue::");
				  second=sc.nextInt();
			  }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				 CallableStatement cs=con.prepareCall(CALL_PROCEDURE);	){
			   //register OUT parameters with JDBc types
			 if(cs!=null)
				 cs.registerOutParameter(3, Types.INTEGER);
			 //set values to IN parameters
			  if(cs!=null) {
				  cs.setInt(1, first);
				  cs.setInt(2,second);
			  }
			  //execute PL/SQL procedure
			  if(cs!=null)
				  cs.execute();
			  //gather results from OUT parameters
			  if(cs!=null) {
				  int result=0;
				  result=cs.getInt(3);
				  System.out.println("sum :: "+result);
			  }
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
