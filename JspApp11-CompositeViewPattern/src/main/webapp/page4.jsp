<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  %>

<table widht="100%" height="100%">
    <tr height="20%">
      <td colspan="3"><jsp:include page="headurl"/>   </td>
    </tr>
    <tr height="70%">
       <td  width="20%"> 
           <%@include file="left_content.html" %>
       </td>
       <td  width="50%"> 
           <jsp:include page="entertainment.jsp"/>
       </td>
       
       <td  width="30%"> 
           <table>
             <tr>
                <td><jsp:include page="horoscope.jsp"/>  </td>
             </tr>
             <tr>
              <td><jsp:include page="weather.jsp"/>  </td>
             </tr>
           </table>
       </td>
    </tr>
    <tr height="10%">
       <td colspan="3"><%@include file="footer.html" %>    </td>
    </tr>
</table>    