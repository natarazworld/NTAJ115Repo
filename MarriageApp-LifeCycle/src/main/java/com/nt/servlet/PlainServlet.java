//PlainServlet.java
package com.nt.servlet;

import jakarta.servlet.*;   //servlet api
import jakarta.servlet.http.*;   //servlet api
import java.io.*;

public class PlainServlet extends  HttpServlet
{
	static {
		System.out.println("PlainServlet: static block");
	}
	public PlainServlet() {
		System.out.println("PlainServlet:: 0-param constructor");
	}
	@Override
	public void init(ServletConfig cg) throws ServletException {
		System.out.println("PlainServlet::init(cg)");
	}
	
	protected  void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("PlainServlet.service()");
	    //get PrintWriter
		 PrintWriter pw=res.getWriter();
		 //set response content type
		 res.setContentType("text/plain");
		 //write messages to res obj
		 pw.println("<table  border='1' bgcolor='cyan' align='center'>");
		 pw.println("<tr><th> Game  </th> <th> Player  </th>   <th> Medal </th> </tr>");
 		 pw.println("<tr><td> Javelin Throw  </td> <td> Neeraj chopra  </td>   <td> Gold </td> </tr>");
  		 pw.println("<tr><td>  Batminton  </td> <td> PV sindhu  </td>   <td> Bronze </td> </tr>");
   		 pw.println("<tr><td> weight Lifting  </td> <td> MiraBai  </td>   <td> Siver </td> </tr>");
   		 pw.println("<tr><td> Wrestling  </td> <td> Ravi kumar  </td>   <td> Siver </td> </tr>");
  		 pw.println("<tr><td> Wrestling  </td> <td> bajarang punia </td>   <td> bronze </td> </tr>");
		 pw.println("</table>");

		 //close stream
		 pw.close();
	}//service(-,-)
}//class
