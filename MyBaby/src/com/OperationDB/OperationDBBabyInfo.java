package com.OperationDB;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBBabyInfo {
	 Connection con;
	    Statement stm;
	    ResultSet re;
	    public OperationDBBabyInfo() {
	    	try
	         {
	             con=ConnectDB.connect();
	             stm=con.createStatement();
	         }
	         catch(Exception e)
	         {
	             System.out.println("Exception 2 "+e);
	         }
		}
	    public BabyBean babyInfo(BabyBean bean)
	    {
	 	   try
	 	   	{
		 	    Calendar cal=Calendar.getInstance();
		 	    String date=cal.get(Calendar.MONDAY)+"-"+cal.get(Calendar.DATE)+"-"+cal.get(Calendar.YEAR);
		 	    String time= cal.get(Calendar.HOUR)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND)+""+cal.get(Calendar.MILLISECOND);
		 		String p=bean.getPath();
		 	    byte[] imageByteArray = DatatypeConverter.parseBase64Binary(p);
		   		FileOutputStream imageOutFile = new FileOutputStream(
		                 "/usr/local/apache-tomcat-7.0.53/webapps/MyBaby/image/"+date+time+"image.jpg");
		   		//FileOutputStream imageOutFile = new FileOutputStream("C://Users/sahu/Desktop/"+time+"image.jpg");
		   		 imageOutFile.write(imageByteArray);
		         imageOutFile.close();
		 	     PreparedStatement ps=con.prepareStatement("insert into babyinfo( name, age, dob, relation, image, email) values(?,?,?,?,?,?)");
		   		 ps.setString(1, bean.getBname());
		   	     ps.setString(2, bean.getAge());
		   		 ps.setString(3, bean.getDob());
		   		 ps.setString(4, bean.getRelation());
		         ps.setString(5, date+time+"image.jpg");
		         ps.setString(6, bean.getEmail());
		         int k=ps.executeUpdate();
		         if(k>0)
		         {
		             bean.setStatus(k);
		         }	
	 	   	}
	 	   catch(Exception e)
	 	   {
	 		   System.out.print("Exception here in babyinfo"+e);
	 	   }
	 	   return bean;
	    }
}
