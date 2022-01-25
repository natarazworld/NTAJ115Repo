<%@ page import="java.sql.*"   errorPage="error.jsp"  %>

<%!
  private Connection con;
   private  PreparedStatement ps1,ps2;
public  void jspInit(){
	try{
	//get accesss to ServletConfig obj
	  ServletConfig cg=getServletConfig();
	//read init param values
	String driver=cg.getInitParameter("driver");
	String url=cg.getInitParameter("url");
	String dbuser=cg.getInitParameter("user");
	String dbpwd=cg.getInitParameter("pwd");
	
	//Load jdbc driver class
	Class.forName(driver);
	//create jdbc con object
	con=DriverManager.getConnection(url,dbuser,dbpwd);
	ps1=con.prepareStatement("INSERT INTO JSP_STUDENT1 VALUES(STUD_SEQ.NEXTVAL,?,?,?)");
	ps2=con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM JSP_STUDENT1 ");
	}
	catch(Exception se){
		se.printStackTrace();
	}
}
%>

<%
   String s1val= request.getParameter("s1");
    if(s1val.equalsIgnoreCase("register")){   //for  Submit Button
    	//read form data
    	String name=request.getParameter("name");
    	String addrs=request.getParameter("addrs");
    	float avg=Float.parseFloat(request.getParameter("avg"));
    	//set values to query param
    	ps1.setString(1,name);
    	ps1.setString(2,addrs);
    	ps1.setFloat(3,avg);
    	//execute the query
    	int result=ps1.executeUpdate();
    	//process the results
    	if(result==0){  %>
    	    <h1 style="color:green;text-align:center">Problem in Student registration </h1>
    	    <% }
    	   else{ %> 
    	    <h1 style="color:red;text-align:center">Student registration succeded </h1>
    	    <% }
    }
    else{    //for hyperlink
    	  //execute the select query
    	ResultSet rs=ps2.executeQuery(); %>
    	
    	<h2 style="color:red;text-align:center">  Student details are  </h2>
    	
   		<table border="1"  align="center"  bgcolor="cyan">
	    <tr>
	      <th>sno</th> <th>sname</th> <th>sadd</th> <th>avg</th> 
	    </tr>
	    <tr>

<%	  
    		//Process the ResultSet
    		while(rs.next()){  %>
    	          <tr>
    	              <td><%=rs.getInt(1)%></td>
    	              <td><%=rs.getString(2)%></td>
    	              <td><%=rs.getString(3)%></td>
    	              <td><%=rs.getFloat(4)%></td>
    	          </tr>	    
    	<%	} //while %>
     </table>    	
<%    	
    } //else
%>

<h3 style="text-align:center"> <a href="register.html"> home </a>  </h3>



<%!
   public void jspDestroy(){
	    try{
	    	 if(ps1!=null)
	    		 ps1.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    try{
	    	 if(ps2!=null)
	    		 ps2.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    try{
	    	 if(con!=null)
	    		 con.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	
}//jspDestroy()
%>

