


<!--  create or locates java bean calss object -->
<jsp:useBean id="st" class="com.nt.beans.StudentInfo" scope="session"/>

<p> <b>
sno  =  <jsp:getProperty property="sno" name="st"/> <br>
sname  =  <jsp:getProperty property="sname" name="st"/> <br>
sadd  =  <jsp:getProperty property="sadd" name="st"/> <br>
avg  =  <jsp:getProperty property="avg" name="st"/> <br>
</b>
</p>
 <br>
<b>Java  bean class object' data is retrieved and displayed </b>