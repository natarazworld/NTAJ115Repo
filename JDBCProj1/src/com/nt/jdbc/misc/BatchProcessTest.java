//BatchProcessTes.java
package com.nt.jdbc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",    "system","manager");		                                                                                                  
				 Statement st=con.createStatement();
                ){
			 //add Queries to batch
			 st.addBatch("INSERT INTO STUDENT VALUES(1120,'ramesh','hyd',67.88)");
			 st.addBatch("UPDATE STUDENT SET AVG=AVG+5 WHERE SADD='delhi' ");
			 st.addBatch("DELETE FROM STUDENT WHERE AVG<=110");
			 st.addBatch("INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(4567,'rakesh','CLERK',90000)");
			 //execute the batch
			 int result[]=st.executeBatch();
			 //process the batch
			 int sum=0;
			 for(int i=1;i<result.length;++i)
				 sum=sum+result[i];
			 
			 System.out.println("no.of records that are effeted::"+sum);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
