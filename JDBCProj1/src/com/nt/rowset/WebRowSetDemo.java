//WebRowSetDemo.java
package com.nt.rowset;

import java.io.FileWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {

	public static void main(String[] args) {
		try(OracleWebRowSet wrowset=new OracleWebRowSet()){
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			wrowset.setUsername("system");
			wrowset.setPassword("manager");
			wrowset.setCommand("SELECT * FROM DEPT");
			wrowset.execute();  //To execute the SQL query 
			while(wrowset.next()) {
				System.out.println(wrowset.getInt(1)+"  "+wrowset.getString(2)+"  "+wrowset.getString(3));
			}//while
			
			//write db table recrods to  xml file
			FileWriter writer=new FileWriter("dept.xml");
			wrowset.writeXml(writer);
			System.out.println("Db table records are written dept.xml file ");
			
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
