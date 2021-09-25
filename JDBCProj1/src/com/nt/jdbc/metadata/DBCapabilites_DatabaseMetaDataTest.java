//DBCapabilites_DatabaseMetaDataTest.java
package com.nt.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBCapabilites_DatabaseMetaDataTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                                                                                               "system", "manager")){
			DatabaseMetaData dbmd=null;
			if(con!=null)
				dbmd=con.getMetaData();
			if(dbmd!=null) {
				System.out.println("DB Version::"+dbmd.getDatabaseProductVersion());
				System.out.println("DB Version::"+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion());
				System.out.println("DB s/w name::"+dbmd.getDatabaseProductName());
				System.out.println("JDBC driver version::"+dbmd.getJDBCMajorVersion()+"."+dbmd.getJDBCMinorVersion());
				System.out.println("SQL keywords::"+dbmd.getSQLKeywords());
				System.out.println("numeric functions::"+dbmd.getNumericFunctions());
				System.out.println("system functions::"+dbmd.getSystemFunctions());
				System.out.println(" Table name max lenght ::"+dbmd.getMaxTableNameLength());
				System.out.println(" max row size ::"+dbmd.getMaxRowSize());
				System.out.println(" max db tables in select SQL query ::"+dbmd.getMaxTablesInSelect());
				System.out.println("supports PL/SQL procedue::"+dbmd.supportsStoredProcedures());
				ResultSet rs=dbmd.getTables(null, null, null, new String[] {"TABLE","VIEW"});
				while(rs.next()) {
					System.out.println(rs.getString(3));
				}
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}

	}//main

}//class
