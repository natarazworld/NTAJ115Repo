package com.nt.service;

import java.util.List;

import com.nt.beans.Employee;
import com.nt.dao.EmployeeDAOImpl;
import com.nt.dao.IEmployeeDAO;

public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	 private IEmployeeDAO  dao;
	public EmployeeMgmtServiceImpl() {
		  dao=new EmployeeDAOImpl();
	}

	@Override
	public List<Employee> fetchEmpsByDesg(String desg)throws Exception {
		//use  DAO
		List<Employee> list=dao.getEmployeesByDesg(desg);
		//calculate gross and netsalary for each employee
		list.forEach(e->{
			e.setGrossSal(e.getSal()+ e.getSal()*0.3f);
			e.setNetSal(e.getGrossSal()-e.getGrossSal()*0.1f);
		});
		return list;
	}

}
