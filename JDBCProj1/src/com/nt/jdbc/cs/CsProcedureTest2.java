package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPDETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT FLOAT
) AS 
BEGIN
  
  SELECT ENAME,JOB,SAL  INTO NAME,DESG,SALARY  FROM EMP WHERE EMPNO=NO;
  
END P_GET_EMPDETAILS_BY_NO;
*/

public class CsProcedureTest2 {
     private static final String CALL_PROCEDURE= "{ CALL P_GET_EMPDETAILS_BY_NO(?,?,?,?)  }"; 
	public static void main(String[] args) {
		
		 int no=0;
		try(Scanner sc=new Scanner(System.in)){
			  if(sc!=null) {
				   System.out.println("Enter  employee number::");
				   no=sc.nextInt();
			  }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE); ){
		          //register  OUT parameters with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			//set values to IN params
			if(cs!=null) 
				cs.setInt(1, no);
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from out params
			 if(cs!= null) {
				 System.out.println("Emp name::"+cs.getString(2));
				 System.out.println("Emp Desg::"+cs.getString(3));
				 System.out.println("Emp salary::"+cs.getFloat(4));
			 }//if
			 
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			if(se.getErrorCode()==1403)
				System.out.println("Employee not found");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
