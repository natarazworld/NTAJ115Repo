

 <b> from c.jsp (reading  pageContext attributes)</b>
<!--  read PageContext attributes -->
<b> attr1 (page scope) value :: <%=pageContext.findAttribute("attr1") %> </b> <br>
<b> attr2 (request scope) value :: <%=pageContext.findAttribute("attr2") %> </b> <br>
<b> attr3 (session scope) value :: <%=pageContext.findAttribute("attr3") %> </b> <br>
<b> attr4 (application scope) value :: <%=pageContext.findAttribute("attr4") %> </b> <br>

