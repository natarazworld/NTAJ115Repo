package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nt.beans.Employee;

public class EmployeeDAOImpl implements IEmployeeDAO {
  private static final String GET_EMPS_BY_DESG="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB=?";
	 //helper method
	private Connection  makeDBConnection() throws Exception{
		//Load jdbc driver class    
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		return con;
	}
	
	@Override
	public List<Employee> getEmployeesByDesg(String desg)throws Exception {
		//get  Connection
		 List<Employee> list=null;
		try(Connection con=makeDBConnection();
				 PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESG)){  //try with resource
			//set value query parameter
			ps.setString(1, desg);
			//execute Query and getResultSet obj
			
			try(ResultSet rs=ps.executeQuery()){
				//copy RS records to List<Employee> object
				list=new ArrayList();
              while(rs.next()) {
            	   //copy each  record to Java bean class obj
				   Employee e=new Employee();
				   e.setEmpno(rs.getInt(1));
				   e.setEname(rs.getString(2));
				   e.setJob(rs.getString(3));
				   e.setSal(rs.getFloat(4));
				   //add Java bean class obj List collection
				   list.add(e);
              }//while
			}//try2
		}//try1
		catch(Exception e) {
			e.printStackTrace();
			throw e;  //exception rethrowing for exception propagation
		}
		return list;
	}

}
