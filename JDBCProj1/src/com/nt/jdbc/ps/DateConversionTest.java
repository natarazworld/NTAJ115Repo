package com.nt.jdbc.ps;

import java.text.SimpleDateFormat;

public class DateConversionTest {

	public static void main(String[] args) throws Exception{
		//converting  String date value to java.util.Date class obj
		 String s1="34-18-1990";  //dd-MM-yyyy
		 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		 java.util.Date ud1=sdf.parse(s1);
		 System.out.println("STring date value ::"+s1);
		 System.out.println("util date::"+ud1);
		 
		 //coverting  java.uti.Date class obj to java.sql.Date class obj
		 long ms=ud1.getTime();
		 java.sql.Date sqd1=new java.sql.Date(ms);
		 System.out.println("sql date ::"+sqd1);
		 
		 //coverting String date value to Directly java.sql.Date obj
         String s2="1908-12-31"; //yyyy-MM-dd
         java.sql.Date sqd2=java.sql.Date.valueOf(s2);
         System.out.println("sql date::"+sqd2);

		 //coverting java.sql.Date class obj  to  java.util.Date obj
         java.util.Date ud2=sqd2;
         System.out.println("util date "+ud2);
         
       //coverting java.util.Date class obj  to  String date value
         SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
         String sd2=sdf2.format(ud2);
         System.out.println("String data value ::"+sd2);
		 
	

	}

}
