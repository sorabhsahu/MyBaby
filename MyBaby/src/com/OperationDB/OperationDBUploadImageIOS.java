package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBUploadImageIOS {
	
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBUploadImageIOS() {
		// TODO Auto-generated constructor stub
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
	 		        PreparedStatement pstm=con.prepareStatement("insert into imagestore values(?,?,?,?,?)");
	 	    	     pstm.setString(1, bean.getImagename());
	 		         pstm.setString(2, bean.getDesc());
			         pstm.setString(3, bean.getType());
			         pstm.setString(4,bean.getDate());
			         pstm.setString(5,bean.getEmail());
			         int k=pstm.executeUpdate();
			         if(k>0)
			         {
			        	 bean1.setStatus(1);
			         }	
	 	   	}
	 	   catch(Exception e)
	 	   {
	 		   System.out.print("exception in uploadimg"+e);
	 	   }
	 	   return bean1;
	    }
}
