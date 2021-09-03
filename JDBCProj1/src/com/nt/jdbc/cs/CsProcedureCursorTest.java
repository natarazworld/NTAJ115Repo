// CsProcedureCursorTest.java
package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_DESGS 
(
  DESG1 IN VARCHAR2 
, DESG2 IN VARCHAR2 
, DESG3 IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR
       SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN(DESG1,DESG2,DESG3) ORDER BY JOB;
  
END P_GET_EMP_DETAILS_BY_DESGS;
*/

public class CsProcedureCursorTest {
  private static final String  CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_DESGS (?,?,?,?) }";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);		){
			  //read inputs
			String desg1=null,desg2=null,desg3=null;
			if(sc!=null) {
				 System.out.println("Enter desg1::");
				  desg1=sc.next();
				  System.out.println("Enter desg2::");
				  desg2=sc.next();
				  System.out.println("Enter desg3::");
				  desg3=sc.next();
			}
			//register OUT parameter with JDBC type
			if(cs!=null)
				cs.registerOutParameter(4, OracleTypes.CURSOR);
			//set values to IN parmaters
			if(cs!=null) {
				cs.setString(1,desg1);
				cs.setString(2,desg2);
				cs.setString(3, desg3);
			}
			// call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT parameters
			 if(cs!=null) {
				 ResultSet rs=(ResultSet)cs.getObject(4);
				 //process the Resutls
				 boolean flag=false;
				 while(rs.next()) {
					 flag=true;
					 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getString(5));
	 				 }//while
				 if(flag==false)
					 System.out.println("No records found");
			 }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
