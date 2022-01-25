

<%   Class.forName("java.util.Date");  %>
 <b> class is loaded</b>
 
 
 <%!  
 
   public void jspInit(){
	 try{
        Class.forName("java.util.Date");
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
        System.out.println("class is loaded");
        }%>