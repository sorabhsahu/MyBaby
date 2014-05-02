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

public class OperationDBUploadImage {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBUploadImage() {
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
    public BabyBean uploadImg(BabyBean bean)
    {
    	BabyBean bean1=new BabyBean();
 	   try
 	   	{       
 		        int i=0;
 	       	    Calendar cal=Calendar.getInstance();
 	     	 	String date=cal.get(Calendar.MONDAY)+"-"+cal.get(Calendar.DATE)+"-"+cal.get(Calendar.YEAR);
 	            String time= cal.get(Calendar.HOUR)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND)+""+cal.get(Calendar.MILLISECOND);
 		        PreparedStatement pstm=con.prepareStatement("insert into imagestore values(?,?,?,?,?)");
 	    	    String p=bean.getImgpath();
 	    	    if(bean.getType().equalsIgnoreCase("video"))
 		 		{
	 		        byte[] imageByteArray = DatatypeConverter.parseBase64Binary(p);
	 		 		FileOutputStream imageOutFile = new FileOutputStream("/usr/local/apache-tomcat-7.0.53/webapps/MyBaby/image/"+date+time+"video.mp4");
	 		 		imageOutFile.write(imageByteArray);
	 		        imageOutFile.close();
	 		        
	 	            pstm.setString(1,date+time+"video.mp4");
 		 		}
 		 		else if(bean.getType().equalsIgnoreCase("image"))
 		 		{
 		 			
 		 			byte[] imageByteArray = DatatypeConverter.parseBase64Binary(p);
 		 			
 			 		FileOutputStream imageOutFile = new FileOutputStream( "/usr/local/apache-tomcat-7.0.53/webapps/MyBaby/image/"+date+time+"image.jpg");
 			 	//	FileOutputStream imageOutFile = new FileOutputStream("C://Users/sahu/Desktop/arc/"+time+"image.jpg");
 			 		imageOutFile.write(imageByteArray);
 			        imageOutFile.close();
 			        pstm.setString(1, date+time+"image.jpg");
 		 		}
 		 		
 		 		else if(bean.getType().equalsIgnoreCase("audio"))
 		 		{
 		 			byte[] imageByteArray = DatatypeConverter.parseBase64Binary(p);
 			 		FileOutputStream imageOutFile = new FileOutputStream("/usr/local/apache-tomcat-7.0.53/webapps/MyBaby/image/"+date+time+"audio.mp3");
 			 		imageOutFile.write(imageByteArray);
 			        imageOutFile.close();
 			        pstm.setString(1, date+time+"audio.mp3");
 		 		}
 		 		else if(bean.getType().equalsIgnoreCase("text"))
 		 		{
 		 			pstm.setString(1, "no image");
 		 		}
		         pstm.setString(2, bean.getDesc());
		         pstm.setString(3, bean.getType());
		         pstm.setString(4,bean.getDate());
		         pstm.setString(5,bean.getEmail());
		        i= pstm.executeUpdate();
		        if(i>=1)
		        {
		             bean1.setStatus(1);
		        }
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("exception in uploadfile"+e);
 	   }
 	   return bean1;
    }
}
