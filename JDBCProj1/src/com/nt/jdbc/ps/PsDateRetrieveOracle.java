package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetrieveOracle {
  private static final String GET_DATE_VALUES="SELECT  CID,CNAME,CADD,DOB,DOM,DOJ FROM CITIZEN_DETAILS";
public static void main(String[] args) {
   Connection con=null;		
   PreparedStatement ps=null;
   ResultSet rs=null;
		try {
		     //Load  JDBC driver class
		     Class.forName("oracle.jdbc.driver.OracleDriver");
		     //establish the connection
		     con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
		     //create PrpearedStaement object having  pre-compiled SQL query
		     if(con!=null)
		    	 ps=con.prepareStatement(GET_DATE_VALUES);
		     //execute the pre-compiled  SQL Query
		     if(ps!=null)
		    	 rs=ps.executeQuery();
		     //process the Result
		     if(rs!=null) {
		       while(rs.next()) {
		    	    int cno=rs.getInt(1);
		    	    String name=rs.getString(2);
		    	    String addrs=rs.getString(3);
		    	    java.sql.Date sqdob=rs.getDate(4);
		    	    java.sql.Date sqdom=rs.getDate(5);
		    	    java.sql.Date sqdoj=rs.getDate(6);
		    	    
		    	    //convert java.sql.Date class objs to String data value in the required pattern
		    	    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		    	    String sdob=sdf.format(sqdob);
		    	    String sdom=sdf.format(sqdom);
		    	    String sdoj=sdf.format(sqdoj);
		    	    System.out.println(cno+"  "+name+"  "+addrs+"   "+sdob+"  "+sdom+"  "+sdoj);
		       }//while
		     }//of
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			
		}//finally

	}//main
}//class
