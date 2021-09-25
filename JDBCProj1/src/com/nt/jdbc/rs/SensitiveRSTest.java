 //SensitiveRSTest.java
package com.nt.jdbc.rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveRSTest {
   private static final String  GET_EMPS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				//Statement st=con.createStatement();
				//Statement st=con.createStatement(1004, 1008);
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                                      ResultSet.CONCUR_UPDATABLE);
				 ResultSet rs=st.executeQuery(GET_EMPS_QUERY)){
			   //process the RS
			    if(rs!=null) {
			    	 int count=0;
			    	while(rs.next()) {
			    		//rs.refreshRow();
			    		  if(count==0)
			    			  Thread.sleep(30000);  //during this sleep time modify db table records using SQL developer or  SQL prompt
			    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    		count++;
			    	}
			    }//if
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
   }
}
