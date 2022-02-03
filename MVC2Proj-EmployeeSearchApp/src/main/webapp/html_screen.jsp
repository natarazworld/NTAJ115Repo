<%@ page import="java.util.*,com.nt.beans.*" %>

<%
    //read request scope data
    List<Employee> list=(List<Employee>)request.getAttribute("empsList");
    // read request param value
     String  desg=request.getParameter("desg");
%>

<h1 style="color:green;text-align:center">Employees Belonging to DESG::  <%=desg %></h1>

<%  
    if(list!=null  && list.size()>0){  %>
      <table border="1"  align="center"  bgcolor="pink">
          <tr> 
              <th> empno </th> <th> ename </th> <th> desg </th> <th> salary </th> <th> gross salary </th> <th> net salary </th>
           </tr>
      <%
         for(Employee emp:list){  %>
        	   <tr>
        	      <td><%=emp.getEmpno() %>  </td>
        	      <td><%=emp.getEname() %>  </td>
        	      <td><%=emp.getJob() %>  </td>
        	      <td><%=emp.getSal() %>  </td>
        	      <td><%=emp.getGrossSal() %>  </td>
        	      <td><%=emp.getNetSal() %>  </td>
        	   </tr>
<%         	 
         } %>
      </table>
      <%}//if 
      else{      
            %>
            <h1 style="color:red;text-align:center">Records not found </h1>
   <%   } %>
   
   