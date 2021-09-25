//SelectTest.java
package com.nt.jdbc.misc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectTest {
   private static final String  GET_EMPS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		
		Properties props=null;
		try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties")){
			//Load properties file info to java.util.Properties class obj
			 props=new Properties();
			 props.load(is);
		}
		catch(IOException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try(Connection con=DriverManager.getConnection(props.getProperty("jdbc.url"),
				                                                                                            props.getProperty("jdbc.user"),
				                                                                                            props.getProperty("jdbc.pwd"));
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
