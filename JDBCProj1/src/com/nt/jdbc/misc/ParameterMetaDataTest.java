package com.nt.jdbc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class ParameterMetaDataTest {
  private static final String GET_STUDENTS_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				                                                                                                  "system","manager");		                                                                                                  
				PreparedStatement ps=con.prepareStatement(GET_STUDENTS_QUERY)){
			
		         if(ps!=null) {
			        	 ParameterMetaData pmd=ps.getParameterMetaData();
			        	 
			        	int paramCount=pmd.getParameterCount();
			        	
			        	for(int i=1;i<=paramCount;++i) {
			                 System.out.println("parameter number::"+i);
			                 System.out.println("parameter mode ::"+pmd.getParameterMode(i));
			                 System.out.println("parameter type name::"+pmd.getParameterTypeName(i));
			                 System.out.println("parmaters sacale"+pmd.getScale(i));
			                 System.out.println("parmaters sacale"+pmd.getPrecision(i));
			                 System.out.println("Is paramter signed?"+pmd.isSigned(i));
			        	}//for
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
