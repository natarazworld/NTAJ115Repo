package com.nt.jdbc.ps;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE TABLE "SYSTEM"."JDBC_ACTOR_INFO" 
(	"ACTORID" NUMBER NOT NULL ENABLE, 
	"ACTORNAME" VARCHAR2(20 BYTE), 
	"ACTORADDRS" VARCHAR2(20 BYTE), 
	"PHOTO" BLOB, 
	 CONSTRAINT "JDBC_ACTOR_INFO_PK" PRIMARY KEY ("ACTORID"));
	 
	 CREATE SEQUENCE  "SYSTEM"."ACTOR_ID_SEQ"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
	 
*/

public class BLOBInsertTest {
 private static final String  BLOB_INSERT_QUERY="INSERT INTO JDBC_ACTOR_INFO VALUES(ACTOR_ID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
      
		 String actorName=null,actorAddrs=null,photoLocation=null;
		//read  inputs
		try(Scanner sc=new Scanner(System.in)){
			  if(sc!=null) {
				  System.out.println("Enter actor Name");
				  actorName=sc.next();
				  System.out.println("Enter actor Address");
				  actorAddrs=sc.next();
				  System.out.println("Enter actor PhotoLocation");
				  photoLocation=sc.next().replace("?","");
			  }
			  
			  try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                      PreparedStatement ps=con.prepareStatement(BLOB_INSERT_QUERY);					  
					   InputStream is=new FileInputStream(photoLocation);
					  ){
				     //set values to Query parametes
				       if(ps!=null) {
				    	   ps.setString(1,actorName);
				    	   ps.setString(2,actorAddrs);
				    	   ps.setBinaryStream(3, is);
				       }
				       //execute the query
				       int result=0;
				       if(ps!=null)
				    	   result=ps.executeUpdate();
				       //process the reuslt
				       if(result==0)
				    	   System.out.println("Record not insergted");
				       else
				    	   System.out.println("Record inserted");
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			
		}
		

	}

}
