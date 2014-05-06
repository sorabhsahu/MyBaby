package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBRegistration {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBRegistration() {
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
    public BabyBean getRegister(BabyBean bean)
    {
    	try
    	{
    		int i=0;
    		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?)");
    		ps.setString(1, bean.getName());
    		ps.setString(2, bean.getEmail());
    		ps.setString(3, bean.getPassword());
    		ps.setString(4, bean.getCpassword());
    		ps.setString(5, bean.getName()+bean.getPassword());
    		i=ps.executeUpdate();
    		if(i>0)
    		{
    			bean.setStatus(1);
    			return bean;
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
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
