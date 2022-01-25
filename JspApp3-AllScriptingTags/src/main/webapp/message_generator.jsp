
<%!  public  String  generateWishMessage(String user){
	     //get System Date and time
	       java.time.LocalDateTime ldt=java.time.LocalDateTime.now();
	     //get current hour of the day
	      int hour=ldt.getHour();  //24 hours format
	      //generate wish message
//  	        int a=10; 
	       if(hour<12)
	    	   return "Good Morning::"+user;
	       else if(hour<16)
	    	   return "Good AfterNoon::"+user;
	       else if(hour<20)
	    	   return "Good Evening::"+user;
	       else
	    	   return "Good Night::"+user;
    }
	%>

<%--  <h1 style="color:red;text-align:center"> welcome  java server pages </h1>   --%>
<h2 style="color:red;text-align:center"> Date and time :: <%=new  java.util.Date() %> </h2>
<%
   String user=request.getParameter("uname");  //username as request param value
%>

  <h2> The Wish Message is  :: <!--  <%=generateWishMessage(user) %> --> </h2>    
 
    


