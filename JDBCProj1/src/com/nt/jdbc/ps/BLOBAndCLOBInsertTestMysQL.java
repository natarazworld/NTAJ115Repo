package com.nt.jdbc.ps;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;



public class BLOBAndCLOBInsertTestMysQL {
 private static final String  BLOB_INSERT_QUERY="INSERT INTO JDBC_ACTOR_INFO(ACTORNAME,ACTORADDRS,PHOTO,RESUME) VALUES(?,?,?,?)";
	public static void main(String[] args) {
      
		 String actorName=null,actorAddrs=null,photoLocation=null,resumeLocation=null;
		//read  inputs
		try(Scanner sc=new Scanner(System.in)){
			  if(sc!=null) {
				  System.out.println("Enter actor Name");
				  actorName=sc.next();
				  System.out.println("Enter actor Address");
				  actorAddrs=sc.next();
				  System.out.println("Enter actor Photo file Location");
				  photoLocation=sc.next().replace("?","");
				  System.out.println("Enter actor Resume file Location");
				  resumeLocation=sc.next().replace("?","");
			  }
			  
			  try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj115db1","root","root");
                      PreparedStatement ps=con.prepareStatement(BLOB_INSERT_QUERY);					  
					   InputStream is=new FileInputStream(photoLocation);
					  Reader reader=new FileReader(resumeLocation);
					  ){
				     //set values to Query parametes
				       if(ps!=null) {
				    	   ps.setString(1,actorName);
				    	   ps.setString(2,actorAddrs);
				    	   ps.setBinaryStream(3, is);
				    	   ps.setCharacterStream(4, reader);
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
