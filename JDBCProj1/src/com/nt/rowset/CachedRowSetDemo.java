//CachedRowSetDemo.java
package com.nt.rowset;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		try(OracleCachedRowSet jrowset=new OracleCachedRowSet()){
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			jrowset.setCommand("SELECT * FROM DEPT");
			jrowset.execute();  //To execute the SQL query 
			while(jrowset.next()) {
				System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
			}//while
			System.out.println("Stop Db s/w during this sleep time");
			Thread.sleep(40000);
			// modification in offline mode/ disconnected mode
			jrowset.absolute(2);
			jrowset.updateString(3,"delhi123");
			jrowset.updateRow();
			System.out.println("Start the Db s/w during the sleep time");
			Thread.sleep(70000);
			jrowset.acceptChanges();
			while(jrowset.next()) {
				System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
			}//while
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
