//ScrollableRSTest.java
package com.nt.jdbc.rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRSTest {
   private static final String  GET_EMPS_QUERY="SELECT  EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				//Statement st=con.createStatement();
				//Statement st=con.createStatement(1004, 1008);
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 ResultSet rs=st.executeQuery(GET_EMPS_QUERY)){
			   //process the RS
			   System.out.println("records (top to bottom)");
			    if(rs!=null) {
			    	while(rs.next()) {
			    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    	}
			    	rs.afterLast();
			    }//if
			    System.out.println("..........................................");
			    System.out.println("records (botton to top)");
			    if(rs!=null) {
			    	while(rs.previous()) {
			    		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    	}
			    }//if
			    //moving randomly
			    System.out.println("-----------------------------");
			    if(rs!=null) {
			    	rs.first();
			    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			    	
			    		rs.last();
				    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    
				    	rs.relative(-4);
				    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    	
				    	rs.relative(2);
				    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    	
				    	rs.absolute(4);
				    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    	 
				    	rs.absolute(-8);
				    	System.out.println(rs.getRow()+"------>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
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
