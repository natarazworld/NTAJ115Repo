 //UpdatableRSTest.java
package com.nt.jdbc.rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableRSTest {
   private static final String  GET_EMPS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				//Statement st=con.createStatement();
				//Statement st=con.createStatement(1004, 1008);
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                                                      ResultSet.CONCUR_UPDATABLE);
				 ResultSet rs=st.executeQuery(GET_EMPS_QUERY)){
			   //process the RS
			    if(rs!=null) {
			    	while(rs.next()) {
			    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    	}
			    	System.out.println("-------------------");
						// insert operation
						rs.moveToInsertRow();
						rs.updateInt(1,677);
						rs.updateString(2,"ramesh");
						rs.updateString(3,"hyd");
						rs.updateFloat(4, 78.99f);
						rs.insertRow();
						System.out.println("record inserted");
			    	
						// update operation
						rs.absolute(4);
						rs.updateString(3, "vizag3");
						rs.updateRow();
						System.out.println("record updated");
						
			    	//delete operation
			    	 rs.absolute(8);
			    	 rs.deleteRow();
			    	 System.out.println("record deleted");
			    	
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
