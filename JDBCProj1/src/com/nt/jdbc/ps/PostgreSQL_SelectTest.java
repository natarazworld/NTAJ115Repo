package com.nt.jdbc.ps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQL_SelectTest {
  private  static final String  GET_PRODUCTS_QUERY="SELECT PID,PNAME,PRICE,QTY FROM PRODUCT";
	public static void main(String[] args) {
		try( //Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ115DB", "postgres", "tiger");
				Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ115DB", "postgres", "tiger");
				PreparedStatement ps=con.prepareStatement(GET_PRODUCTS_QUERY);
				ResultSet rs=ps.executeQuery();){
			     //Process the ResultSet
			  if(rs!=null) {
				  while(rs.next()) {
					  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getFloat(4));
				  }//while
			  }//if
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
