
<b> start of jsp page </b>

<%
    float price=567.0f;
    float discount= price *0.3f;
    float finalPrice= price-discount;    		
%>

 <jsp:forward page="b.jsp">
     <jsp:param value="CRJ-by HS" name="bookName"/>
      <jsp:param value="<%=finalPrice%>" name="netPrice"/>
 </jsp:forward>
 <b> end of  jsp page</b>
 
 