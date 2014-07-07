package com.OperationDB;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import com.Contexts.AllPath;
import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBBabyUpdate {
	Connection con;
    Statement stm;
    ResultSet re;
    static int images=0;
    public OperationDBBabyUpdate() {
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
	 	    int i=0;
	 	    Calendar cal=Calendar.getInstance();
	 	    String date=cal.get(Calendar.MONDAY)+"-"+cal.get(Calendar.DATE)+"-"+cal.get(Calendar.YEAR);
	 	    String time= cal.get(Calendar.HOUR)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND)+""+cal.get(Calendar.MILLISECOND);
	 	    String p=bean.getPath();
	   		byte[] imageByteArray = DatatypeConverter.parseBase64Binary(p);
	   		FileOutputStream imageOutFile = new FileOutputStream(AllPath.getFinalPath()+date+time+"image.jpg");
	   	 //  FileOutputStream imageOutFile = new FileOutputStream("C://Users/sahu/Desktop/"+date+time+"image.jpg");
	   		 imageOutFile.write(imageByteArray);
	         imageOutFile.close();
	 	     PreparedStatement ps=con.prepareStatement("update babyinfo set name=?,age=?,dob=?,"
													   + "relation=?, image=? where baby_id=?");
	 	    ps.setString(1, bean.getBname());
	   		 ps.setString(2, bean.getAge());
	   		 ps.setString(3, bean.getDob());
	   		 ps.setString(4, bean.getRelation());
	         ps.setString(5, date+time+"image.jpg");
	         ps.setString(6, bean.getBaby_id()+"");
	         int k=ps.executeUpdate();
	         if(k>0)
	         {
	        	 i++;
	             bean.setStatus(i);
	         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("Exception here in babyupdate"+e);
 	   }
 	  finally
  	  {
          if(con!=null)
          {
            try 
            {
              con.close();
            } catch (SQLException e)
            {
              e.printStackTrace();
            }
          }
  	  }
 	   return bean;
    }
    public BabyBean babyupdate(BabyBean bean)
    {
 	   try
 	   	{
	 	     PreparedStatement ps=con.prepareStatement("update babyinfo set name=?, age=?, dob=?, relation=? where baby_id=?");
	   		 ps.setString(1, bean.getBname());
	   	     ps.setString(2, bean.getAge());
	   		 ps.setString(3, bean.getDob());
	   		 ps.setString(4, bean.getRelation());
	         ps.setInt(5, bean.getBaby_id());
	         int k=0;
	         k=ps.executeUpdate();
	         if(k>0)
	         {
	             bean.setStatus(k);
	         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("Exception here in babyupdate"+e);
 	   }
 	  finally
  	{
          if(con!=null)
          {
            try 
            {
              con.close();
            } catch (SQLException e)
            {
              e.printStackTrace();
            }
          }
  	}
 	   return bean;
    }


}
