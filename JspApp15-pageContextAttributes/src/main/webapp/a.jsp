

 <b> from a.jsp (creating pageContext attributes)</b>
<!--  create PageContext attributes -->
<%
    pageContext.setAttribute("attr1","val1",pageContext.PAGE_SCOPE); //page scope
    pageContext.setAttribute("attr2","val2",pageContext.REQUEST_SCOPE); //request scope
    pageContext.setAttribute("attr3","val3",pageContext.SESSION_SCOPE); //session scope
    pageContext.setAttribute("attr4","val4",pageContext.APPLICATION_SCOPE); //application scope
%>

<jsp:forward page="b.jsp"/>