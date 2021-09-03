//CsProcedureTest3.java
package com.nt.jdbc.cs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace  procedure P_AUTH(username IN varchar2,
        password  IN varchar2,
        result  OUT varchar2)
as
cnt number(1);
begin
SELECT COUNT(*) INTO cnt  FROM USERSLIST WHERE UNAME=username AND PWD=password;
if(cnt<>0)then
result:='VALID CREDENTIALS';
else 
result:='INVALID CREDENTIALS';
end if;
end;
/
*/

public class CsProcedureTest3 {
     private static final String CALL_PROCEDURE= "{ CALL P_AUTH(?,?,?) }"; 
	public static void main(String[] args) {
		
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE); ){
			
			String user=null, pwd=null;
			if(sc!=null) {
				//read inputs
				System.out.println("enter username::");
			   user=sc.nextLine();
			   System.out.println("enter password::");
			   pwd=sc.nextLine();
			}
			
		          //register  OUT parameters with JDBC types
			if(cs!=null) 
				cs.registerOutParameter(3, Types.VARCHAR);
			
			//set values to IN params
			if(cs!=null) {
				cs.setString(1, user);
				cs.setString(2,pwd);
			}
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from out params
			 if(cs!= null) 
				 System.out.println("Result is::"+cs.getString(3));
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			if(se.getErrorCode()==1403)
				System.out.println("Employee not found");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
