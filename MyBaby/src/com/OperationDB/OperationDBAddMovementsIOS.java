package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBAddMovementsIOS 
{
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBAddMovementsIOS()
    {
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
 		        PreparedStatement pstm=con.prepareStatement("insert into newstorageDB values(?,?,?,?,?,?,?,?)");
 	    	     pstm.setString(1, bean.getBname());
 		         pstm.setString(2, bean.getMoments());
		         pstm.setString(3, bean.getImagename());
		         pstm.setString(4,bean.getDesc());
		         pstm.setString(5,bean.getType());
		         pstm.setString(6,bean.getDate());
		         pstm.setString(7,bean.getEmail());
		         pstm.setInt(8, bean.getBaby_id());
		         int k=pstm.executeUpdate();
		         if(k>0)
		         {
		        	 bean1.setStatus(1);
		         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("exception in upload movements ios "+e);
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
 	   return bean1;
    }

}
