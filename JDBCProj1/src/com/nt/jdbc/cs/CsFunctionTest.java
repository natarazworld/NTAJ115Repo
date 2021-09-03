//CsFunctionTest.java
package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_EMP_DETAILS_BY_ENO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT NUMBER 
) RETURN NUMBER AS 
  DNO NUMBER(5);
BEGIN
  SELECT ENAME,JOB,SAL,DEPTNO INTO NAME,DESG,SALARY,DNO  FROM EMP WHERE EMPNO=NO;
  RETURN DNO;
END FX_GET_EMP_DETAILS_BY_ENO;
*/
public class CsFunctionTest {
   private static  final String  CALL_FUNCTION="{?= call FX_GET_EMP_DETAILS_BY_ENO(?,?,?,?)}";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				CallableStatement cs=con.prepareCall(CALL_FUNCTION);
				){
			   int no=0;
			    if(sc!=null) {
			    	System.out.println("Enter employee number::  ");
			    	no=sc.nextInt();
			    }
			    //register Return, OUT paramters  with JDBC types
			    if(cs!=null) {
			    	cs.registerOutParameter(1,Types.INTEGER); //return param
			    	cs.registerOutParameter(3, Types.VARCHAR);  // OUT param
			    	cs.registerOutParameter(4, Types.VARCHAR);  // OUT param
			    	cs.registerOutParameter(5, Types.FLOAT);  // OUT param
			    }
			    //set value to IN params
			    if(cs!=null)
			    	cs.setInt(2, no);
			    
			    // call PL/SQL function
			    if(cs!=null)
			    	cs.execute();
			    //gather results from return,OUT params
			    if(cs!=null) {
			    	System.out.println(" employee name::"+cs.getString(3));
			    	System.out.println(" employee desg::"+cs.getString(4));
			    	System.out.println(" employee salary::"+cs.getFloat(5));
			    	System.out.println(" employee deptNo::"+cs.getInt(1));
			    }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			if(se.getErrorCode()==1403)
				System.out.println("Data not found");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
