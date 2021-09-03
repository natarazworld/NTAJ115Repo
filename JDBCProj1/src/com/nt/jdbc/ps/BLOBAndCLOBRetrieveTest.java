package com.nt.jdbc.ps;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;


public class BLOBAndCLOBRetrieveTest {
 private static final String  BLOB_RETRIEVE_QUERY="SELECT ACTORID,ACTORNAME,ACTORADDRS,PHOTO,RESUME FROM JDBC_ACTOR_INFO  WHERE ACTORID=?";
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
			  
			  try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj115db1","root","root");
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
				    	        		  Reader reader=rs.getCharacterStream(5);
				    	        		 OutputStream os=new FileOutputStream("retrieve_photo.jpg");
				    	        		  Writer writer =new FileWriter("retrieve_resume.txt");
				    	        		 ){
				    	        	 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
					    	          //perform file copy operation using streams
					    	          IOUtils.copy(is,os);
					    	          System.out.println("Photo retrieved and stored  to retrieve_photo.jpg file");
					    	          IOUtils.copy(reader,writer);
					    	          System.out.println("Resume retrieved and stored  to retrieve_resume.jpg file");
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


