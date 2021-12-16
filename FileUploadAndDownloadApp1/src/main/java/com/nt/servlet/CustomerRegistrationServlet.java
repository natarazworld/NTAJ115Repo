package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;


@WebServlet("/register")
public class CustomerRegistrationServlet extends HttpServlet {
	
  
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
           //get PrintWriter 
		   PrintWriter pw=res.getWriter();
		   //set response content type
		    res.setContentType("text/html");
		    	
		    	try {
		    //create Special Request object  as wrapper around req object
		    MultipartFormDataRequest nreq=new MultipartFormDataRequest(req);
		    
		  //read form data (from noramal text boxes)
	    	String name=nreq.getParameter("cname");
	    	String addrs=nreq.getParameter("cadd");
		    // specify file upload settings
		     UploadBean bean=new UploadBean();
		     bean.setFolderstore("E:\\store");
		     //performs uploading
		     bean.store(nreq);  //collects files content from nreq obj and writes to  destination folder 
		     
		     // get List of the files that are uploaded
		      Hashtable<String,UploadFile> ht=nreq.getFiles();
		      String filename1=ht.get("cphoto").getFileName();
		      String filename2=ht.get("cresume").getFileName();
		      
		      pw.println("<br><br> <h1>  "+filename1+"  and  "+filename2+" are uploaded succesfully </h1>");
		      
		      //write the jdbc code
		         Class.forName("oracle.jdbc.driver.OracleDriver");
		         
		        try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
		        		                                                                                                "system", "manager");
		        		PreparedStatement ps=con.prepareStatement("INSERT INTO SERVLET_UPLOAD_CUSTOMER VALUES(CNO1_SEQ.NEXTVAL,?,?,?,?)");
		        		){
		        	
		        
		         //set query param value
		        	ps.setString(1, name);
		        	ps.setString(2,addrs);
		        	ps.setString(3, "E:\\store\\"+filename1);
		        	ps.setString(4, "E:\\store\\"+filename2);
		        	
		        	//execute the query
		        	int result=ps.executeUpdate();
		      
		          //process the result
		        	if(result==0) 
		        		pw.println("<br><h1 style='color:red;text-align:center'>Customer Registration failed </h1>");
		        	
		        	else 
		        		pw.println("<br><h1 style='color:red;text-align:center'>Customer Registration succeded </h1>");
		        	
		    }//try2
		   	}//try1
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    	
		    	// home hyperlink
		    	pw.println("<br> <a href='index.html'>home </a>");
		    
		     //close stream
		      pw.close();
		
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
     doGet(req,res);
	}

}
