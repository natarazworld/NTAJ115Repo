
<% //read form data
  String ss=request.getParameter("ss");
  //perform sendRedirect with google
    response.sendRedirect("https://www.google.com/search?q="+ss);
  %>
