package com.nt.jdbc.ps;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

/*CREATE TABLE "SYSTEM"."JDBC_ACTOR_INFO" 
(	"ACTORID" NUMBER NOT NULL ENABLE, 
	"ACTORNAME" VARCHAR2(20 BYTE), 
	"ACTORADDRS" VARCHAR2(20 BYTE), 
	"PHOTO" BLOB, 
	 CONSTRAINT "JDBC_ACTOR_INFO_PK" PRIMARY KEY ("ACTORID"));
	 
	 CREATE SEQUENCE  "SYSTEM"."ACTOR_ID_SEQ"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
	 
*/

public class BLOBRetrieveTest {
 private static final String  BLOB_RETRIEVE_QUERY="SELECT ACTORID,ACTORNAME,ACTORADDRS,PHOTO FROM JDBC_ACTOR_INFO  WHERE ACTORID=?";
	public static void main(String[] args) {
      
		 int actorId=0;
		//read  inputs
		try(Scanner sc=new Scanner(System.in)){
			  if(sc!=null) {
				  System.out.println("Enter actor Id");
				  actorId=sc.nextInt();
			  }
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
			  
			  try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                      PreparedStatement ps=con.prepareStatement(BLOB_RETRIEVE_QUERY);					  
					  ){
				     //set values to Query parametes
				       if(ps!=null) 
				    	   ps.setInt(1,actorId);
				       
				       //execute the Query
				       try(ResultSet rs=ps.executeQuery()){
				    	   if(rs!=null) {
				    	     if(rs.next()) {
				    	         try( InputStream is=rs.getBinaryStream(4);
				    	        		 OutputStream os=new FileOutputStream("retrieve_photo.jpg");
				    	        		 ){
				    	        	 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
					    	          //perform file copy operation using streams
					    	          IOUtils.copy(is,os);
					    	          System.out.println("Photo retrieved and stored  to retrieve_photo.jpg file");
				    	         }//try3
				    	     }//if
				    	     else {
				    	    	 System.out.println("record not found");
				    	    	 return;
				    	     }//else
				    	   }//if
				       }//try2
			  }//try1
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			
		}//main
	}//class


