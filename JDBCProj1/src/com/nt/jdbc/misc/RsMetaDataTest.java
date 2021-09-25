package com.nt.jdbc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RsMetaDataTest {
  private static final String GET_EMPS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                                                                                                  "system","manager");		                                                                                                  
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(GET_EMPS_QUERY)){
			if(rs!=null) {
                //create ResultSetMetaData object
				ResultSetMetaData rsmd=rs.getMetaData();
				//get column count
				int colCount=rsmd.getColumnCount();
				//display colum nams
				for(int i=1;i<=colCount;++i)
					System.out.print(rsmd.getColumnLabel(i)+"      ");
				System.out.println();
				
				for(int i=1;i<=colCount;++i)
					System.out.print(rsmd.getColumnTypeName(i)+"      ");
				System.out.println();

				
				//print col value
				//process the RS
				while(rs.next()) {
					for(int i=1;i<=colCount;++i) {
						System.out.print(rs.getString(i)+"     ");
						}
					System.out.println();
					
				}
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
