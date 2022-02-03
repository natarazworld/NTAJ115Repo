package com.nt.dao;

import java.util.List;

import com.nt.beans.Employee;

public interface IEmployeeDAO {
     public  List<Employee>  getEmployeesByDesg(String desg)throws  Exception;
}
