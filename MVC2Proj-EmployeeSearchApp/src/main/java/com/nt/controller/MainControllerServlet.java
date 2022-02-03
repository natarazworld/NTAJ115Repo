package com.nt.controller;

import java.io.IOException;
import java.util.List;

import com.nt.beans.Employee;
import com.nt.service.EmployeeMgmtServiceImpl;
import com.nt.service.IEmployeeMgmtService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {
	private  IEmployeeMgmtService service;
	
	@Override
	public void init() throws ServletException {
           service=new EmployeeMgmtServiceImpl();
	}
	
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// read form data
		 String desg=req.getParameter("desg");
		 //find  out  submit button that is used send the request
		 String action=req.getParameter("s1");
		 try {
		 //use service class
		 List<Employee> list=service.fetchEmpsByDesg(desg);
		 //keep the results in request scope
		 req.setAttribute("empsList", list);
		 //  Based on the Submit button that is used to send request  decide the  view comp to  for request
		 String target=null;
		 if(action.equalsIgnoreCase("html output"))
			  target="/html_screen.jsp";
		 else
			 target="/excel_screen.jsp";
		 //forward the request to result page
		 RequestDispatcher rd=req.getRequestDispatcher(target);
		 rd.forward(req, res);
		 }//try
		 catch(Exception e) {
			 RequestDispatcher rd=req.getRequestDispatcher("/error.jsp");
			 rd.forward(req, res);
		 }//catch
		
	}//

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
