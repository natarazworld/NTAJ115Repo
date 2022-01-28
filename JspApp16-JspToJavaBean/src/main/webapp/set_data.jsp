


<!--  create or locates java bean calss object -->
<jsp:useBean id="st" class="com.nt.beans.StudentInfo" scope="session"/>

<!-- set values to  bean properties -->
<%-- <jsp:setProperty property="sno" name="st" value="1001"/>
<jsp:setProperty property="sname" name="st" value="raja"/>
<jsp:setProperty property="sadd" name="st" value="hyd"/>
<jsp:setProperty property="avg" name="st" value="89.66"/> --%>

<%-- <!-- set req param  values to  bean properties -->
 <jsp:setProperty property="sno" name="st" param="stno"/>
<jsp:setProperty property="sname" name="st" param="stname"/>
<jsp:setProperty property="sadd" name="st" param="staddrs"/>
<jsp:setProperty property="avg" name="st" param="stavg"/>
 --%>
 <jsp:setProperty name="st" property="*" />
<br>
<b> Values are set to bean properties</b>